package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.kjellvos.aletho.zombieshooter.gdx.entities.PlayerEntity;
import com.kjellvos.aletho.zombieshooter.gdx.loader.B2dAssetManager;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;
import com.kjellvos.aletho.zombieshooter.gdx.views.MainMenu;
import com.kjellvos.aletho.zombieshooter.gdx.views.PreferencesMenu;
import com.kjellvos.aletho.zombieshooter.gdx.views.Splashes;

public class ZombieShooterGame extends Game{
	public static boolean DEBUG = true;

	public static int WIDTH = 1024, HEIGHT = 786;

	public static float commodoreBlueR = 0.25882354F, commodoreBlueG = 0.25882354F, commodoreBlueB = 0.90588236F;

	public static final int PPM = 16;

	private Splashes splashes;
	private MainMenu mainMenu;
	private PreferencesMenu preferences;
	private AppPreferences appPreferences;
	private GameScreen game;

	private B2dAssetManager assetManager;
	private TilesetToSprite tilesetToSprite;

	private PlayerEntity player;

	@Override
	public void create () {
		splashes = new Splashes(this);
		changeScreen(ScreenEnum.SPLASH);

		appPreferences = new AppPreferences();
		tilesetToSprite = new TilesetToSprite(new Texture("0x72_16x16DungeonTilesetTogether.png"));

		assetManager = new B2dAssetManager(this);
		assetManager.loadMap();
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public PlayerEntity getPlayer(){
		return player;
	}

	public B2dAssetManager getAssetManager(){
		return assetManager;
	}

	public AppPreferences getPreferences() {
		return appPreferences;
	}

	public GameScreen getGameScreen(){
		return game;
	}

	public TilesetToSprite getTilesetToSprite() {
		return tilesetToSprite;
	}

	public void changeScreen(ScreenEnum screen) {
		switch (screen) {
			case SPLASH:
				if (splashes == null) splashes = new Splashes(this);
				setScreen(splashes);
				break;

			case MAIN_MENU:
				if (mainMenu == null) mainMenu = new MainMenu(this);
				setScreen(mainMenu);
				break;

			case PREFERENCES:
				if (preferences == null) preferences = new PreferencesMenu(this);
				setScreen(preferences);
				break;

			case GAME:
				if (game == null) game = new GameScreen(this);
				setScreen(game);
				break;
		}
	}
}
