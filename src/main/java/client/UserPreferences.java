package client;

import java.util.prefs.Preferences;

/**
 * @author Wesley Klop
 */
public class UserPreferences {
    private static final String SOUND_MUTED = "SOUND_MUTED";
    private static final String IS_COLORBLIND = "IS_COLORBLIND";
    private static final String MUSIC_VOLUME = "MUSIC_VOLUME";
    private static final String FX_VOLUME = "FX_VOLUME";

    private final Preferences prefs = Preferences.userNodeForPackage(UserPreferences.class);

    public void setColorblind(boolean colorblind) {
        prefs.putBoolean(IS_COLORBLIND, colorblind);
    }

    public boolean isSoundMuted() {
        return prefs.getBoolean(SOUND_MUTED, false);
    }

    public void setSoundMuted(boolean muted) {
        prefs.putBoolean(SOUND_MUTED, muted);
    }

    public boolean isColorBlind() {
        return prefs.getBoolean(IS_COLORBLIND, false);
    }

    public double getMusicVolume() {
        return prefs.getDouble(MUSIC_VOLUME, 0.5);
    }

    public void setMusicVolume(double volume) {
        prefs.putDouble(MUSIC_VOLUME, volume);
    }

    public double getFxVolume() {
        return prefs.getDouble(FX_VOLUME, 0.3);
    }

    public void setFxVolume(double volume) {
        prefs.putDouble(FX_VOLUME, volume);
    }

}
