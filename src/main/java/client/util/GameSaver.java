package client.util;

import game.GameStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @author Wesley Klop
 */
public class GameSaver {
    private static final Logger Log = LogManager.getLogger(GameSaver.class);
    private static final String SAVE_PATH = "./TTRGameSave.dat";

    public static void saveGame(GameStore store) {
        try (var stream = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            stream.writeObject(store);
        } catch (IOException ex) {
            Log.error("Error saving game", ex);
        }
    }

    public static GameStore loadGame() {
        try (var stream = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            return (GameStore) stream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Log.error("Error loading game", ex);
        }
        return null;
    }
}
