package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.kjellvos.aletho.zombieshooter.gdx.enums.ScreenEnum;
import com.kjellvos.aletho.zombieshooter.gdx.loader.B2dAssetManager;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;
import com.kjellvos.aletho.zombieshooter.gdx.views.MainMenu;
import com.kjellvos.aletho.zombieshooter.gdx.views.PreferencesMenu;
import com.kjellvos.aletho.zombieshooter.gdx.views.Splashes;

/**
 * This program is built with the LibGDX library, The idea is to combine a tower defense and RPG.
 *
 * @author Kjell Vos
 * @version 0.1
 * @since 2020-02-28
 */
public class ZombieShooterGame extends Game{
	/**
	 * The Splashes class, The splashes are what you see immediately after starting the game.
	 */
	private Splashes splashes;

	/**
	 * The main menu class
	 */
	private MainMenu mainMenu;

	/**
	 * The preferences menu screen, Hosts the input(i.e. players can change the sound volume and such for the AppPreferences class.
	 */
	private PreferencesMenu preferences;

	/**
	 * Applicaton preferences such as sound/effect volume.
	 */
	private AppPreferences appPreferences;

	/**
	 * The game screen implementation houses most of the game code.
	 */
	private GameScreen game;

	/**
	 * Assetmanager used for loading asset
	 */
	private B2dAssetManager assetManager;

	/**
	 * The player entity used in the Ashley entity system.
	 */
	private Entity player;

	/**
	 * The JSON parser
	 */
	private ReadJsonGameFiles readJsonGameFiles;

	private String gameDataJSON = null, spriteSheetsJSON = null, spritesJSON = null, animationsJSON = null;

	/**
	 * This is the method that runs once them game starts, Shows splashes and loads assets.
	 */
	@Override
	public void create () {
		splashes = new Splashes(this);
		changeScreen(ScreenEnum.SPLASH);

		appPreferences = new AppPreferences();

		gameDataJSON = Gdx.files.internal(Constants.GAMEDATA_JSON).readString();
		spriteSheetsJSON = Gdx.files.internal(Constants.SPRITESHEET_JSON).readString();
		spritesJSON = Gdx.files.internal(Constants.SPRITES_JSON).readString();
		animationsJSON = Gdx.files.internal(Constants.ANIMATIONS_JSON).readString();

		readJsonGameFiles = new ReadJsonGameFiles(this, gameDataJSON, spriteSheetsJSON, spritesJSON, animationsJSON);

		assetManager = new B2dAssetManager(this);
		assetManager.load();
		assetManager.getAssetManager().finishLoading();

		readJsonGameFiles.setupWithAssets();
	}

	public ReadJsonGameFiles getReadJsonGameFiles() {
		return readJsonGameFiles;
	}

	/**
	 * This is a function used to set the player variable. Is set in the GameScreen class.
	 * @param player PlayerEntity for updating inventory etc.
	 */
	public void setPlayer(Entity player) {
		this.player = player;
	}

	/**
	 * This is a function to get the player entity. Might be null if used before GameScreen class is started.
	 * @return PlayerEntity, For adding to inventory/updating
	 */
	public Entity getPlayer(){
		return player;
	}

	/**
	 * A getter for the asset manager variable used to load the game assets.
	 * @return B2dAssetManager containing the loaded assets.
	 */
	public B2dAssetManager getAssetManager(){
		return assetManager;
	}

	/**
	 * A getter for the AppPreferences class, used for storing the sound/volume and other preferences.
	 * @return AppPreferences containing settings for music/sound volume.
	 */
	public AppPreferences getPreferences() {
		return appPreferences;
	}

	/**
	 * Returns the gameScreen class, can be used to set the screen to this one.
	 * @return GameScreen The screen extended class used for the game
	 */
	public GameScreen getGameScreen(){
		return game;
	}


	/**
	 * This is a function to be used to change the current screen from one to another.
	 * @param screen ScreenEum used for the different screen classes.
	 */
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
