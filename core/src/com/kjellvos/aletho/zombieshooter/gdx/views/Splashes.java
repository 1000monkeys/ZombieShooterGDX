package com.kjellvos.aletho.zombieshooter.gdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.inputprocessors.SplashesInputProcessor;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class Splashes implements Screen {
    /**
     * See {@link GameScreen#parent}
     */
    private ZombieShooterGame parent;

    /**
     * The stage. Used for rendering of the splashes.
     */
    private Stage stage;
    /**
     * The splash texture variable.
     */
    private Texture texture;
    /**
     * The splash image variable, Made with {@link Splashes#texture}.
     */
    private Image image;

    /**
     * See {@link GameScreen#GameScreen(ZombieShooterGame)}
     */
    public Splashes(ZombieShooterGame zombieShooterGame) {
        this.parent = zombieShooterGame;
    }

    /**
     * See {@link GameScreen#show()}
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new SplashesInputProcessor(parent));

        texture = new Texture("SplashKjell.png");
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        image = new Image(texture);
        image.setSize(Constants.WIDTH, Constants.HEIGHT);

        stage = new Stage(new FitViewport(Constants.WIDTH, Constants.HEIGHT));
        stage.addActor(image);

        image.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(1.25F), Actions.delay(1F)));
    }

    /**
     * See {@link GameScreen#render(float)}
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    /**
     * See {@link GameScreen#resize(int, int)}
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        texture.dispose();
    }
}
