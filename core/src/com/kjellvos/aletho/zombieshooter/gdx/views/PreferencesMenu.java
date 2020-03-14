package com.kjellvos.aletho.zombieshooter.gdx.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.enums.ScreenEnum;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class PreferencesMenu implements Screen {
    /**
     * The Main class of the game.
     */
    private ZombieShooterGame parent;

    private Stage stage;
    private OrthographicCamera camera;

    public PreferencesMenu(ZombieShooterGame zombieShooterGame) {
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

        Table settingsTable = new Table(skin);
        settingsTable.background("window");
        settingsTable.setFillParent(true);

        // Music
        final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        volumeMusicSlider.setValue( parent.getPreferences().getMusicVolume() );
        volumeMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setMusicVolume( volumeMusicSlider.getValue() );
                return false;
            }
        });

        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked( parent.getPreferences().isMusicEnabled() );
        musicCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getPreferences().setMusicEnabled( enabled );
                return false;
            }
        });

        // Sound effects
        final Slider volumeSoundSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        volumeSoundSlider.setValue( parent.getPreferences().getSoundVolume() );
        volumeSoundSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getPreferences().setSoundVolume( volumeSoundSlider.getValue() );
                return false;
            }
        });

        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked( parent.getPreferences().isSoundEffectsEnabled() );
        soundEffectsCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getPreferences().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        // return to main screen button
        final TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(ScreenEnum.MAIN_MENU);
            }
        });

        // our new fields
        Label titleLabel = new Label("Preferences", skin);
        Label volumeMusicLabel = new Label("Music Volume", skin);
        Label musicOnOffLabel = new Label("Music on/off", skin);
        Label volumeSoundLabel = new Label("Sound Volume", skin);
        Label soundOnOffLabel = new Label("Sound on/off", skin);

        if (Constants.DEBUG) {
           settingsTable.setDebug(true);
        }

        settingsTable.add(titleLabel);
        settingsTable.row().pad(10, 0, 0, 10);
        settingsTable.add(volumeMusicLabel);
        settingsTable.add(volumeMusicSlider);
        settingsTable.row().pad(10, 0, 0, 10);
        settingsTable.add(musicOnOffLabel);
        settingsTable.add(musicCheckbox);
        settingsTable.row().pad(25, 0, 0, 10);
        settingsTable.add(volumeSoundLabel);
        settingsTable.add(volumeSoundSlider);
        settingsTable.row().pad(10, 0, 0, 10);
        settingsTable.add(soundOnOffLabel);
        settingsTable.add(soundEffectsCheckbox);
        settingsTable.row().pad(25, 0, 0, 10);
        settingsTable.add(backButton);

        stage.addActor(settingsTable);
    }

    /**
     * See {@link GameScreen#render(float)}
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

    @Override
    public void dispose() {
        stage.dispose();
    }
}
