package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {
    /**
     * The preferred music volume {@link String} used to get the music volume variable using GetPrefs().
     */
    private static final String PREF_MUSIC_VOLUME = "volume";
    /**
     * Toggle {@link String} used to get the boolean value whether music is enabled or not.
     */
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    /**
     * Toggle {@link String} used to get the sound/effects boolean value whether sound/effects is enabled or not.
     */
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    /**
     * The preferred sound/effects volume {@link String} used to get the sound volume variable using GetPrefs().
     */
    private static final String PREF_SOUND_VOLUME = "sound";
    /**
     * The preferences name {@link String}, Allows for different preferences using different names, Used to get the preferences using Gdx.app.getPreferences().
     */
    private static final String PREFS_NAME = "mainSettings";

    /**
     * Returns the preferences, Used to set the sound/effects and music volume.
     * @return {@link Preferences}
     */
    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    /**
     * Function used for getting the sound effects boolean.
     * Defaults to true.
     * @return Boolean value dependant on whether the sound is enabled or not.
     */
    public boolean isSoundEffectsEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    /**
     * Sets the {@link AppPreferences#PREF_SOUND_VOLUME} to the passed boolean value.
     * @param soundEffectsEnabled boolean value used to set the {@link AppPreferences#PREF_SOUND_VOLUME} to the new value.
     */
    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }

    /**
     * Getter to get the boolean value of {@link AppPreferences#PREF_SOUND_ENABLED}.
     * Defaults to true.
     * @return Boolean value dependant on whether the music is enabled or not.
     */
    public boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    /**
     * Sets the {@link AppPreferences#PREF_SOUND_ENABLED} to the passed boolean value.
     * @param musicEnabled boolean value used to set the {@link AppPreferences#PREF_SOUND_ENABLED} to the new value.
     */
    public void setMusicEnabled(boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }

    /**
     * Getter to get the music volume float value.
     * The float value goes from 0.0F to 1.0F.
     * @return float value, The music volume.
     */
    public float getMusicVolume() {
        return getPrefs().getFloat(PREF_MUSIC_VOLUME, 0.5f);
    }

    /**
     * Sets the {@link AppPreferences#PREF_SOUND_VOLUME} to the passed float value.
     * Float value should be between 0.0F and 1.0F.
     * @param volume float value used to set the {@link AppPreferences#PREF_MUSIC_VOLUME} to the new value.
     */
    public void setMusicVolume(float volume) {
        getPrefs().putFloat(PREF_MUSIC_VOLUME, volume);
        getPrefs().flush();
    }

    /**
     * Gets the  preferred sound/effects volume using the  {@link String} identifier.
     * Uses {@link AppPreferences#PREF_SOUND_VOLUME}
     */
    public float getSoundVolume() {
        return getPrefs().getFloat(PREF_SOUND_VOLUME, 0.5f);
    }

    /**
     * Sets the {@link AppPreferences#PREF_SOUND_VOLUME} to the passed float value.
     * Float value should be between 0.0F and 1.0F.
     * @param volume float value used to set the {@link AppPreferences#PREF_SOUND_VOLUME} to the new value.
     */
    public void setSoundVolume(float volume) {
        getPrefs().putFloat(PREF_SOUND_VOLUME, volume);
        getPrefs().flush();
    }
}
