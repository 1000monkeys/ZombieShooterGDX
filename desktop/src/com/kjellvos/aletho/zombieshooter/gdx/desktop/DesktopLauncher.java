package com.kjellvos.aletho.zombieshooter.gdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 786;
		config.vSyncEnabled = true;
		config.backgroundFPS = 15;
		config.foregroundFPS = 60;
		new LwjglApplication(new ZombieShooterGame(), config);
	}
}
