package com.kjellvos.aletho.zombieshooter.gdx.views;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.MobBuilder;
import com.kjellvos.aletho.zombieshooter.gdx.TextureEnum;
import com.kjellvos.aletho.zombieshooter.gdx.TilesetTextureToTextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.Box2dContactListener;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.MapBodyBuilder;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.entities.PlayerEntity;
import com.kjellvos.aletho.zombieshooter.gdx.systems.PlayerMovementSystem;
import com.kjellvos.aletho.zombieshooter.gdx.systems.RenderSystem;

public class GameScreen implements Screen, InputProcessor {
    private ZombieShooterGame parent;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private World world;
    private FitViewport viewport;
    private SpriteBatch batch;
    private Engine engine;
    private Box2DDebugRenderer debugRenderer;

    private TiledMap map;
    private Texture tileset;
    private TextureRegion player;

    public boolean leftPressed = false, rightPressed = false, upPressed = false, downPressed = false;

    public GameScreen(ZombieShooterGame zombieShooterGame){
        parent = zombieShooterGame;
    }

    @Override
    public void show() {
        parent.getAssetManager().getAssetManager().finishLoading();
        map = parent.getAssetManager().getAssetManager().get("testmap.tmx", TiledMap.class);
        tileset = parent.getAssetManager().getAssetManager().get("0x72_16x16DungeonTilesetTogether.png", Texture.class);
        parent.getAssetManager().getAssetManager().finishLoading();

        player = TilesetTextureToTextureRegion.getTextureRegionById(tileset, TextureEnum.PLAYER.getId());

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, ZombieShooterGame.PPM * ZombieShooterGame.viewWidthInTiles, ZombieShooterGame.PPM * ZombieShooterGame.viewHeightInTiles);
        viewport = new FitViewport(ZombieShooterGame.viewWidthInTiles * ZombieShooterGame.PPM, ZombieShooterGame.viewHeightInTiles * ZombieShooterGame.PPM, camera);
        camera.update();
        tiledMapRenderer.setView(camera);

        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new Box2dContactListener());

        batch = new SpriteBatch();

        engine = new Engine();
        engine.addSystem(new RenderSystem(this, batch));
        engine.addSystem(new PlayerMovementSystem(this));

        Gdx.input.setInputProcessor(this);

        if (ZombieShooterGame.DEBUG) {
            debugRenderer = new Box2DDebugRenderer();
        }

        MapBodyBuilder.buildShapes(map, world);
        createPlayerEntity();
        MobBuilder.buildMobs(map, tileset, world, engine);
    }

    public void createPlayerEntity(){
        PlayerEntity entity = new PlayerEntity();
        TextureRegion playerTextureRegion = TilesetTextureToTextureRegion.getTextureRegionById(tileset, TextureEnum.PLAYER.getId());

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((50 * ZombieShooterGame.PPM + playerTextureRegion.getRegionWidth() / 2F) / ZombieShooterGame.PPM, (50 * ZombieShooterGame.PPM + playerTextureRegion.getRegionWidth() / 2F) / ZombieShooterGame.PPM);

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(playerTextureRegion.getRegionHeight() / 2F / ZombieShooterGame.PPM, playerTextureRegion.getRegionWidth() / 2F / ZombieShooterGame.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("player");

        entity.add(new BodyComponent(body)).add(new TextureRegionComponent(TilesetTextureToTextureRegion.getTextureRegionById(tileset, TextureEnum.PLAYER.getId()))).add(new PlayerSteerableComponent(50 * ZombieShooterGame.PPM, 50 * ZombieShooterGame.PPM));
        engine.addEntity(entity);
        parent.setPlayer(entity);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BodyComponent bodyComp = parent.getPlayer().getComponent(BodyComponent.class);

        world.step(1f/30f, 3, 3);
        camera.position.set(bodyComp.body.getPosition().x * ZombieShooterGame.PPM, bodyComp.body.getPosition().y * ZombieShooterGame.PPM, 0);
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        engine.update(delta);
        batch.draw(player, bodyComp.body.getPosition().x * ZombieShooterGame.PPM - player.getRegionWidth() / 2, bodyComp.body.getPosition().y * ZombieShooterGame.PPM - player.getRegionHeight() / 2);
        batch.end();

        if (ZombieShooterGame.DEBUG) {
            debugRenderer.render(world, camera.combined.cpy().scale(ZombieShooterGame.PPM, ZombieShooterGame.PPM, 0F));
        }
    }

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

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
        if (ZombieShooterGame.DEBUG) {
            debugRenderer.dispose();
        }
        map.dispose();
        tileset.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

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
}
