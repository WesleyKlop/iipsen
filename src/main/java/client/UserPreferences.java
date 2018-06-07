package client;

import java.util.prefs.Preferences;

/**
 * UserPreferences is a layer over Preferences to get/set our application settings.
 *
 * @author Wesley Klop
 */
public class UserPreferences {
    /*
     * NOTE: When adding a new preference option, please add:
     * 1. A String key
     * 2. A getter
     * 3. A setter
     * 4. Some JavaDoc
     */
    private static final String SOUND_MUTED = "SOUND_MUTED";
    private static final String IS_COLORBLIND = "IS_COLORBLIND";
    private static final String MUSIC_VOLUME = "MUSIC_VOLUME";
    private static final String FX_VOLUME = "FX_VOLUME";

    /**
     * Store where we R/W our preferences
     */
    private final Preferences prefs = Preferences.userNodeForPackage(UserPreferences.class);

    /**
     * Sets the color blind settings in preferences
     *
     * @param colorblind true if the application should be in colorblind mode
     */
    public void setColorblind(boolean colorblind) {
        prefs.putBoolean(IS_COLORBLIND, colorblind);
    }

    /**
     * Check if sound is muted.
     * @return true if the sound is muted, false if not or when the value is not set.
     */
    public boolean isSoundMuted() {
        return prefs.getBoolean(SOUND_MUTED, false);
    }

    /**
     * Sets the sound muted setting in preferences.
     * @param muted true if the application should mute sound.
     */
    public void setSoundMuted(boolean muted) {
        prefs.putBoolean(SOUND_MUTED, muted);
    }

    /**
     * Checks if the application is in colorblind mode.
     * @return true if colorblind mode is on, false if not or when the value is not set.
     */
    public boolean isColorBlind() {
        return prefs.getBoolean(IS_COLORBLIND, false);
    }

    /**
     * Returns the music volume
     * @return the music volume, or 0.5 by default when the key is not set
     */
    public double getMusicVolume() {
        return prefs.getDouble(MUSIC_VOLUME, 0.5);
    }

    /**
     * Sets the music volume
     * This value should be between 0.0 and 1.0
     * @param volume the volume for music
     */
    public void setMusicVolume(double volume) {
        prefs.putDouble(MUSIC_VOLUME, volume);
    }

    /**
     * Returns the FX volume
     * @return the fx volume, or 0.3 by default when the key is not set
     */
    public double getFxVolume() {
        return prefs.getDouble(FX_VOLUME, 0.3);
    }

    /**
     * Sets the FX volume
     * This value should be between 0.0 and 0.6
     * @param volume the volume for sound FX
     */
    public void setFxVolume(double volume) {
        prefs.putDouble(FX_VOLUME, volume);
    }

}
