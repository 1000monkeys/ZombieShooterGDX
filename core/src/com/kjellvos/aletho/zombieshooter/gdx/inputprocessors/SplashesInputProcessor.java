package com.kjellvos.aletho.zombieshooter.gdx.inputprocessors;

import com.badlogic.gdx.InputProcessor;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;
import com.kjellvos.aletho.zombieshooter.gdx.views.MainMenu;

public class SplashesInputProcessor implements InputProcessor {
    /**
     * See {@link GameScreen#parent}
     */
    private ZombieShooterGame parent;

    public SplashesInputProcessor(ZombieShooterGame zombieShooterGame) {
        this.parent = zombieShooterGame;
    }

    @Override
    public boolean keyDown(int keycode) {
        parent.setScreen(new MainMenu(parent));
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
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
