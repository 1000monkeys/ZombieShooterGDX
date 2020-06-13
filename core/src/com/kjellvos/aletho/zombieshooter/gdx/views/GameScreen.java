package com.kjellvos.aletho.zombieshooter.gdx.views;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.PathSmoother;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.Mapper;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.entities.PlayerEntity;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.systems.SteeringSystem;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.Box2dLocation;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.MapBodyBuilder;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.MobBuilder;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.systems.ItemPickUpSystem;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.systems.PlayerMovementSystem;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.systems.RenderSystem;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.*;

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

    private TileWorld tileWorld;
    private IndexedAStarPathFinder<Tile> pathFinder;
    private Array<Tile> tiles;
    private int mapWidth, mapHeight;

    private TiledMap map;
    private PlayerEntity player;

    private boolean leftPressed = false, rightPressed = false, upPressed = false, downPressed = false;
    private float stateTime = 0;

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


        mapWidth = map.getProperties().get("width", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class);
        tiles = new Array<>(mapWidth * mapHeight);
        TiledMapTileLayer currLayer = (TiledMapTileLayer) map.getLayers().get("Background");

        for (int x = 0; x < mapWidth; x++){
            for (int y = 0; y < mapHeight; y++) {
                int id = currLayer.getCell(x, y).getTile().getId() - 1;
                boolean walkable = false;
                if (id == 98) {
                    walkable = true;
                }

                tiles.add(new Tile(x, y, walkable, x * mapWidth + y));
            }
        }

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                Array<Connection<Tile>> connections = new Array<Connection<Tile>>(4);

                if (x > 0 && tiles.get((x - 1) * mapWidth + y).isWalkable()) connections.add(new TileConnection(tiles.get(x * mapWidth + y), tiles.get((x - 1) * mapWidth + y))); //-1, 0
                if (y > 0 && tiles.get(x * mapWidth + (y - 1)).isWalkable()) connections.add(new TileConnection(tiles.get(x * mapWidth + y), tiles.get(x * mapWidth + (y - 1)))); //0, -1
                if (x < mapWidth - 1 && tiles.get((x  + 1) * mapWidth + y).isWalkable()) connections.add(new TileConnection(tiles.get(x * mapWidth + y), tiles.get((x + 1) * mapWidth + y))); //1, 0
                if (y < mapHeight - 1 && tiles.get(x * mapWidth + (y + 1)).isWalkable()) connections.add(new TileConnection(tiles.get(x * mapWidth + y), tiles.get(x * mapWidth + (y + 1)))); //0, 1

                tiles.get(x * mapWidth + y).setConnections(connections);
            }
        }

        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                Tile tile = tiles.get(x * mapWidth + y);
                String kloptfniet = "NEE KLOPT NIET!";
                if (tile.getIndex() == x * mapWidth + y) {
                    kloptfniet = "JA KLOPT!";
                }
                System.out.println(tile.getX() + ":X_&_Y:" + tile.getY() + " | " + tile.getIndex() + " " + kloptfniet);
                System.out.println("CONNECTIONS: ");
                for (int i = 0; i < tile.getConnections().size; i++) {
                    System.out.println(tile.getConnections().get(i).getToNode().getX() + ":X_&_Y:" + tile.getConnections().get(i).getToNode().getY());
                }
            }
        }

        tileWorld = new TileWorld(tiles, mapWidth, mapHeight);
        pathFinder = new IndexedAStarPathFinder<Tile>(tileWorld);

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

        if (Constants.DEBUG) {
            debugRenderer = new Box2DDebugRenderer();
        }

        engine = new Engine();
        MapBodyBuilder.buildShapes(map, world);
        Texture spriteSheet = parent.getReadJsonGameFiles().getSpriteSheetGsons().get(parent.getReadJsonGameFiles().getGameDataGson().getMainSpriteSheet()).getSpriteSheet();
        MobBuilder.buildObjects(parent, map, spriteSheet, parent.getReadJsonGameFiles(), world, engine, rayHandler);
        engine.addSystem(new RenderSystem(batch));
        engine.addSystem(new PlayerMovementSystem(this));
        engine.addSystem(new ItemPickUpSystem(parent));
        engine.addSystem(new SteeringSystem(parent));

        Gdx.input.setInputProcessor(this);


        player = new PlayerEntity(parent);

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

    public IndexedAStarPathFinder<Tile> getPathFinder() {
        return pathFinder;
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
     * The render method called every time a new frame gets made.
     * @param delta time since last frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BodyComponent bodyComp = Mapper.bodyCom.get(parent.getGameScreen().getPlayer());

        world.step(1f / 30f, 3, 3);
        camera.position.set(bodyComp.body.getPosition().x, bodyComp.body.getPosition().y, 0);
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render(new int[]{Constants.BACKGROUND_LAYER});

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        engine.update(delta);
        batch.end();

        tiledMapRenderer.render(new int[]{Constants.FOREGROUND_LAYER});
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();

        if (player.getInventory().itemToPickUp()){
            BitmapFont font = new BitmapFont();
            font.getData().setScale(0.5F);

            String pickUpString = player.getInventory().getPickUpString();
            layout.setText(font, pickUpString);

            Body body = Mapper.bodyCom.get(player).body;

            batch.begin();
            font.draw(batch, pickUpString, body.getPosition().x - layout.width / 2, body.getPosition().y - 20);
            batch.end();
        }

        if (Constants.DEBUG) {
            debugRenderer.render(world, camera.combined);
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

                player.getInventory().pickUpClosestItem();
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

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public Tile getTile(int x, int y) {
        return tiles.get(x * mapWidth + y);
    }
}
