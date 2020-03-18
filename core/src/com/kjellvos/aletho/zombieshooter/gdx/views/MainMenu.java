package com.kjellvos.aletho.zombieshooter.gdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.enums.ScreenEnum;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class MainMenu implements Screen {
    private ZombieShooterGame parent;
    private Stage stage;
    private OrthographicCamera camera;

    public MainMenu(ZombieShooterGame zombieShooterGame) {
        parent = zombieShooterGame;
    }

    /**
     * See {@link GameScreen#show()}
     */
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);

        FitViewport viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT, camera);
        viewport.apply();

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Table menuTable = new Table(skin);
        menuTable.background("window");
        menuTable.setFillParent(true);

        if (Constants.DEBUG) {
            menuTable.setDebug(true);
        }

        TextButton newGame = new TextButton("New Game", skin);
        newGame.align(Align.left);
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenEnum.GAME);
            }
        });

        TextButton preferences = new TextButton("Preferences", skin);
        preferences.align(Align.left);
        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenEnum.PREFERENCES);
            }
        });

        TextButton exit = new TextButton("Exit", skin);
        exit.align(Align.left);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        menuTable.add(newGame).fillX().uniformX();
        menuTable.row().pad(10, 0, 10, 0);
        menuTable.add(preferences).fillX().uniformX();
        menuTable.row().pad(25, 0, 10, 0);
        menuTable.add(exit).fillX().uniformX();

        stage.addActor(menuTable);
    }

    /**
     * See {@link GameScreen#render(float)}
     * @param delta time since this function was last ran
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * See {@link GameScreen#resize(int, int)}
     * @param width the new width of the container
     * @param height the new height of the container
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        camera.update();
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
     * See {@link GameScreen#dispose()}
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
