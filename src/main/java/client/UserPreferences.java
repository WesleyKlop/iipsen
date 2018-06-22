package client;

import util.Observer;

import java.util.HashSet;
import java.util.Set;
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
    private static final Preferences prefs = Preferences.userNodeForPackage(UserPreferences.class);
    private static final Set<Observer<PreferencesContainer>> observers = new HashSet<>();

    public static void addObserver(Observer<PreferencesContainer> observer) {
        observers.add(observer);
    }

    public static void removeObserver(Observer<PreferencesContainer> observer) {
        observers.remove(observer);
    }

    private static void notifyObservers() {
        for (Observer<PreferencesContainer> observer : observers) {
            observer.onUpdate(new PreferencesContainer(
                isSoundMuted(),
                isColorBlind(),
                getMusicVolume(),
                getFxVolume()
            ));
        }
    }

    /**
     * Sets the color blind settings in preferences
     *
     * @param colorblind true if the application should be in colorblind mode
     */
    public static void setColorblind(boolean colorblind) {
        prefs.putBoolean(IS_COLORBLIND, colorblind);
        notifyObservers();
    }

    /**
     * Check if sound is muted.
     *
     * @return true if the sound is muted, false if not or when the value is not set.
     */
    public static boolean isSoundMuted() {
        return prefs.getBoolean(SOUND_MUTED, false);
    }

    /**
     * Sets the sound muted setting in preferences.
     *
     * @param muted true if the application should mute sound.
     */
    public static void setSoundMuted(boolean muted) {
        prefs.putBoolean(SOUND_MUTED, muted);
        notifyObservers();
    }

    /**
     * Checks if the application is in colorblind mode.
     *
     * @return true if colorblind mode is on, false if not or when the value is not set.
     */
    public static boolean isColorBlind() {
        return prefs.getBoolean(IS_COLORBLIND, false);
    }

    /**
     * Returns the music volume
     *
     * @return the music volume, or 0.5 by default when the key is not set
     */
    public static double getMusicVolume() {
        return prefs.getDouble(MUSIC_VOLUME, 0.5);
    }

    /**
     * Sets the music volume
     * This value should be between 0.0 and 1.0
     *
     * @param volume the volume for music
     */
    public static void setMusicVolume(double volume) {
        prefs.putDouble(MUSIC_VOLUME, volume);
        notifyObservers();
    }

    /**
     * Returns the FX volume
     *
     * @return the fx volume, or 0.3 by default when the key is not set
     */
    public static double getFxVolume() {
        return prefs.getDouble(FX_VOLUME, 0.3);
    }

    /**
     * Sets the FX volume
     * This value should be between 0.0 and 0.6
     *
     * @param volume the volume for sound FX
     */
    public static void setFxVolume(double volume) {
        prefs.putDouble(FX_VOLUME, volume);
        notifyObservers();
    }

    static public class PreferencesContainer {
        private final boolean soundMuted, colorBlind;
        private final double musicVolume, fxVolume;

        private PreferencesContainer(boolean soundMuted, boolean colorBlind, double musicVolume, double fxVolume) {
            this.soundMuted = soundMuted;
            this.colorBlind = colorBlind;
            this.musicVolume = musicVolume;
            this.fxVolume = fxVolume;
        }

        public boolean isSoundMuted() {
            return soundMuted;
        }

        public boolean isColorBlind() {
            return colorBlind;
        }

        public double getMusicVolume() {
            return musicVolume;
        }

        public double getFxVolume() {
            return fxVolume;
        }
    }
}
