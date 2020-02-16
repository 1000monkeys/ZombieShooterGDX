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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.TextureEnum;
import com.kjellvos.aletho.zombieshooter.gdx.TilesetToSprite;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.b2d.Box2dContactListener;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.entities.PlayerEntity;
import com.kjellvos.aletho.zombieshooter.gdx.systems.PlayerMovementSystem;
import com.kjellvos.aletho.zombieshooter.gdx.systems.RenderSystem;

public class GameScreen implements Screen, InputProcessor {
    private ZombieShooterGame parent;
    private OrthographicCamera camera;
    private Stage stage;
    private TiledMapRenderer tiledMapRenderer;
    private World world;
    private FitViewport viewport;
    private SpriteBatch batch;
    private Engine engine;
    private Box2DDebugRenderer debugRenderer;

    private TiledMap map;
    private Texture tileset;

    public boolean leftPressed = false, rightPressed = false, upPressed = false, downPressed = false;

    public GameScreen(ZombieShooterGame zombieShooterGame){
        parent = zombieShooterGame;
    }

    @Override
    public void show() {
        parent.getAssetManager().getAssetManager().finishLoading();
        map = parent.getAssetManager().getAssetManager().get("testmap.tmx", TiledMap.class);
        tileset = parent.getAssetManager().getAssetManager().get("0x72_16x16DungeonTilesetTogether.png", Texture.class);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false,25 * 16, 25 * 16);

        viewport = new FitViewport(ZombieShooterGame.WIDTH, ZombieShooterGame.HEIGHT, camera);
        viewport.apply();

        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new Box2dContactListener());

        batch = new SpriteBatch();
        stage = new Stage();

        engine = new Engine();
        engine.addSystem(new RenderSystem(this, batch));
        engine.addSystem(new PlayerMovementSystem(this));

        Gdx.input.setInputProcessor(this);

        debugRenderer = new Box2DDebugRenderer();

        createPlayerEntity();
    }

    public void createPlayerEntity(){
        PlayerEntity entity = new PlayerEntity();
        TextureRegion playerTextureRegion = TilesetToSprite.getTextureRegionById(tileset, TextureEnum.PLAYER.getId());

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(49 * ZombieShooterGame.PPM, 49 * ZombieShooterGame.PPM);

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(playerTextureRegion.getTexture().getWidth() / 2 / ZombieShooterGame.PPM / 2 - 0.05f, playerTextureRegion.getTexture().getHeight() / 2 / ZombieShooterGame.PPM  / 2- 0.05f);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("player");

        entity.add(new BodyComponent(body)).add(new TextureRegionComponent(TilesetToSprite.getTextureRegionById(tileset, TextureEnum.PLAYER.getId()))).add(new PlayerSteerableComponent(49 * ZombieShooterGame.PPM, 49 * ZombieShooterGame.PPM));
        engine.addEntity(entity);
        parent.setPlayer(entity);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BodyComponent bodyComp = parent.getPlayer().getComponent(BodyComponent.class);

        world.step(1f/30f, 3, 3);
        camera.position.set(bodyComp.body.getPosition().x, bodyComp.body.getPosition().y, 0);
        camera.update();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stage.act(delta);
        stage.draw();

        batch.begin();
        engine.update(delta);
        batch.end();

        if (ZombieShooterGame.DEBUG) {
            debugRenderer.render(world, camera.combined);
        }
    }

    @Override
    public void resize(int width, int height) {

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
        /*
        if(keycode == Input.Keys.A || keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.D || keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.W || keycode == Input.Keys.UP)
            camera.translate(0,32);
        if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN)
            camera.translate(0,-32);
         */
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
