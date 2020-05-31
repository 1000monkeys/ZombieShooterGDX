package com.kjellvos.aletho.zombieshooter.gdx.views;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.*;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.MapBodyBuilder;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.gson.Animation;
import com.kjellvos.aletho.zombieshooter.gdx.gson.GameData;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteObj;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteSheet;
import com.kjellvos.aletho.zombieshooter.gdx.systems.ItemPickUpSystem;
import com.kjellvos.aletho.zombieshooter.gdx.systems.PlayerMovementSystem;
import com.kjellvos.aletho.zombieshooter.gdx.systems.RenderSystem;

import java.util.List;
import java.util.Random;

public class GameScreen implements Screen, InputProcessor {
    /**
     * The Main class of the game.
     */
    private ZombieShooterGame parent;

    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private World world;
    private FitViewport viewport;

    private SpriteBatch batch;

    private Engine engine;
    private Box2DDebugRenderer debugRenderer;
    private RayHandler rayHandler;
    private Music[] music;
    private GlyphLayout layout;

    private TiledMap map;
    private PlayerEntity player;

    private String gamedataJSON, spriteSheetJSON, spritesJSON, animationsJSON;
    private GameData gameData;
    private List<SpriteSheet> spriteSheets;
    private List<SpriteObj> spriteObjs;
    private List<Animation> animations;

    public boolean leftPressed = false, rightPressed = false, upPressed = false, downPressed = false;

    public Entity closestItem = null;
    private String itemText = null;

    /**
     * The constructor of this class, Passes the main parent as a argument.
     * @param zombieShooterGame {@link ZombieShooterGame}
     */
    public GameScreen(ZombieShooterGame zombieShooterGame){
        parent = zombieShooterGame;
    }

    /**
     * The show function, Called by the LibGDX library once the screen gets shown.
     */
    @Override
    public void show() {
        layout = new GlyphLayout();

        parent.getAssetManager().getAssetManager().finishLoading();
        map = parent.getAssetManager().getAssetManager().get("testmap.tmx", TiledMap.class);

        gameData = parent.getReadJsonGameFiles().getGameData();
        spriteSheets = parent.getReadJsonGameFiles().getSpriteSheets();
        spriteObjs = parent.getReadJsonGameFiles().getSprites();
        animations = parent.getReadJsonGameFiles().getAnimations();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.PPT * Constants.viewWidthInTiles, Constants.PPT * Constants.viewHeightInTiles);
        viewport = new FitViewport(Constants.viewWidthInTiles * Constants.PPT, Constants.viewHeightInTiles * Constants.PPT, camera);
        camera.update();
        viewport.apply();
        tiledMapRenderer.setView(camera);

        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        //world.setContactListener(new Box2dContactListener());

        RayHandler.setGammaCorrection(true);
        RayHandler.useDiffuseLight(true);
        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0.5F, 0.5F, 0.5F, 0.75F);
        rayHandler.setBlurNum(16);
        rayHandler.setCulling(true);

        batch = new SpriteBatch();

        engine = new Engine();
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new PlayerMovementSystem(this));
        engine.addSystem(new ItemPickUpSystem(parent));

        Gdx.input.setInputProcessor(this);

        if (Constants.DEBUG) {
            debugRenderer = new Box2DDebugRenderer();
        }

        MapBodyBuilder.buildShapes(map, world);
        player = new PlayerEntity(parent, parent.getReadJsonGameFiles().getSpriteObj(gameData.getPlayerSpriteId()).getSprite());
        Texture spriteSheet = parent.getReadJsonGameFiles().getSpriteSheets().get(parent.getReadJsonGameFiles().getGameData().getMainSpriteSheet()).getSpriteSheet();
        MobBuilder.buildObjects(map, spriteSheet, parent.getReadJsonGameFiles(), world, engine, rayHandler);

        /*
         * Should go into some sort of music manager class
         */
        music = new Music[Constants.AMOUNT_MUSIC_FILES];
        music[0] = parent.getAssetManager().getAssetManager().get("music_zapsplat_night_stalker.mp3");
        music[0].setVolume(parent.getPreferences().getMusicVolume());
        music[1] = parent.getAssetManager().getAssetManager().get("music_scott_lawlor_strange_lullaby.mp3");
        music[1].setVolume(parent.getPreferences().getMusicVolume());
        music[2] = parent.getAssetManager().getAssetManager().get("music_zapsplat_hallowdream.mp3");
        music[2].setVolume(parent.getPreferences().getMusicVolume());

        music[0].setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music musicPlaying) {
                music[new Random().nextInt(Constants.AMOUNT_MUSIC_FILES)].play();
            }
        });
        music[0].setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music musicPlaying) {
                music[new Random().nextInt(Constants.AMOUNT_MUSIC_FILES)].play();
            }
        });
        music[0].setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music musicPlaying) {
                music[new Random().nextInt(Constants.AMOUNT_MUSIC_FILES)].play();
            }
        });
    }

    /**
     * returns the box2d world for use in other classes
     * @return World the box2d world
     */
    public World getWorld() {
        return world;
    }

    /**
     * returns the ashley entity system engine
     * @return Engine the engine used by the ashley entity system
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Returns the payer entity
     * @return PlayerEntity the main player
     */
    public PlayerEntity getPlayer() {
        return player;
    }

    /**
     * Creates the player entity on the current map.
     */
    public void createPlayerEntity(){
        Entity entity = new Entity();

        TextureRegion playerTextureRegion = spriteObjs.get(gameData.getPlayerSpriteId()).getSprite();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((50 * Constants.PPT + playerTextureRegion.getRegionWidth() / 2F), (50 * Constants.PPT + playerTextureRegion.getRegionWidth() / 2F));

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(playerTextureRegion.getRegionHeight() / 2F, playerTextureRegion.getRegionWidth() / 2F);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;
        body.createFixture(fixtureDef).setUserData("player");

        entity.add(new BodyComponent(body)).add(new TextureRegionComponent(playerTextureRegion)).add(new PlayerSteerableComponent(50 * Constants.PPT, 50 * Constants.PPT));
        engine.addEntity(entity);
        parent.setPlayer(entity);
    }

    /**
     * The render method called every time a new frame gets made.
     * @param delta time since last frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BodyComponent bodyComp = parent.getPlayer().getComponent(BodyComponent.class);

        world.step(1f / 30f, 3, 3);
        camera.position.set(bodyComp.body.getPosition().x, bodyComp.body.getPosition().y, 0);
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(new int[]{Constants.BACKGROUND_LAYER});

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        engine.update(delta);
        batch.draw(player.getPlayerTextureRegion(), bodyComp.body.getPosition().x - player.getPlayerTextureRegion().getRegionWidth() / 2, bodyComp.body.getPosition().y - player.getPlayerTextureRegion().getRegionHeight() / 2);
        batch.end();

        tiledMapRenderer.render(new int[]{Constants.FOREGROUND_LAYER});
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();

        if (Constants.DEBUG) {
            debugRenderer.render(world, camera.combined);
        }

        if (itemText != null && (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().x + 64) > closestItem.getComponent(BodyComponent.class).body.getPosition().x && (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().x - 64) < closestItem.getComponent(BodyComponent.class).body.getPosition().x &&
                                (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().y + 64) > closestItem.getComponent(BodyComponent.class).body.getPosition().y && (parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().y - 64) < closestItem.getComponent(BodyComponent.class).body.getPosition().y) {
            BitmapFont font = new BitmapFont();
            font.getData().setScale(0.7F);

            String pickUpString = "Press G to pick up: " + parent.getReadJsonGameFiles().getSpriteObj(closestItem.getComponent(ItemComponent.class).id).getDescription();
            layout.setText(font, pickUpString);

            batch.begin();
            font.draw(batch, pickUpString, parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().x - layout.width / 2, parent.getPlayer().getComponent(BodyComponent.class).body.getPosition().y - 20);
            batch.end();
        }

        if (music != null) {
            boolean musicBeingPlayed = false;
            for (int i = 0; i < Constants.AMOUNT_MUSIC_FILES; i++) {
                if (music[i].isPlaying()) {
                    musicBeingPlayed = true;
                    break;
                }
            }
            if (!musicBeingPlayed) {
                music[new Random().nextInt(Constants.AMOUNT_MUSIC_FILES)].play();
            }
        }
    }

    /**
     * The resize function gets called once the container is resized and should be used to resize the screen and it's assets.
     * @param width the new width of the container.
     * @param height the new height of the container.
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    /**
     * The dispose function runs once before the class becomes garbage.
     */
    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
        if (Constants.DEBUG) {
            debugRenderer.dispose();
        }
        map.dispose();
        rayHandler.dispose();
    }

    /**
     * This function is part of the LibGDX library, Gets called once the key is pressed down. (Only when the class this is in is set as the input processor)
     * @param keycode the keycode of the pressed key
     * @return boolean on whether the input is processed
     */
    @Override
    public boolean keyDown(int keycode) {
        boolean keyPressed = false;
        switch (keycode)
        {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                leftPressed = true;
                keyPressed = true;
                break;

            case Input.Keys.D:
            case Input.Keys.RIGHT:
                rightPressed = true;
                keyPressed = true;
                break;

            case Input.Keys.W:
            case Input.Keys.UP:
                upPressed = true;
                keyPressed = true;
                break;

            case Input.Keys.S:
            case Input.Keys.DOWN:
                downPressed = true;
                keyPressed = true;
                break;
        }
        return keyPressed;
    }

    /**
     * This function is part of the LibGDX library, Gets called once the key is released from being pressed. (Only when the class this is in is set as the input processor)
     * @param keycode the keycode of the released key
     * @return boolean on whether the input is processed
     */
    @Override
    public boolean keyUp(int keycode) {
        boolean keyPressed = false;
        switch (keycode)
        {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                leftPressed = false;
                keyPressed = true;
                break;

            case Input.Keys.D:
            case Input.Keys.RIGHT:
                rightPressed = false;
                keyPressed = true;
                break;

            case Input.Keys.W:
            case Input.Keys.UP:
                upPressed = false;
                keyPressed = true;
                break;

            case Input.Keys.S:
            case Input.Keys.DOWN:
                downPressed = false;
                keyPressed = true;
                break;

                /*
                Moet deze I en G even beter doen
                 */
            case Input.Keys.I:
                keyPressed = true;

                parent.getGameScreen().getPlayer().getInventory().print();
                break;

            case Input.Keys.G:
                keyPressed = true;

                if (closestItem != null) {
                    parent.getGameScreen().getPlayer().getInventory().addItem(closestItem.getComponent(ItemComponent.class));

                    parent.getGameScreen().getWorld().destroyBody(closestItem.getComponent(BodyComponent.class).body);
                    parent.getGameScreen().getEngine().removeEntity(closestItem);

                    closestItem = null;
                    itemText = null;
                }
                break;
        }
        return keyPressed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void setClosestItem(Entity closestItem) {
        this.closestItem = closestItem;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}
