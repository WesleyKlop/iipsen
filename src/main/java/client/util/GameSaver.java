package client.util;

import game.GameState;
import game.GameStore;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * @author Wesley Klop
 */
public class GameSaver {
    private static final Logger Log = LogManager.getLogger(GameSaver.class);
    private static final String DEFAULT_FILE_NAME = "SavedGame.ttr";
    private static final ExtensionFilter FILE_FILTER = new ExtensionFilter("Ticket to Ride Game Save", "*.ttr");

    public static void saveGame(GameStore store, Window window) {
        FileChooser picker = new FileChooser();
        picker.setTitle("Save game");
        picker.getExtensionFilters().add(GameSaver.FILE_FILTER);
        picker.setInitialFileName(GameSaver.DEFAULT_FILE_NAME);
        try (var stream = new ObjectOutputStream(new FileOutputStream(picker.showSaveDialog(window)))) {
            stream.writeObject(store);
        } catch (IOException ex) {
            Log.error("Error saving game", ex);
        }
    }

    public static GameStore loadGame(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open save");
        chooser.setInitialFileName(DEFAULT_FILE_NAME);
        chooser.getExtensionFilters().add(FILE_FILTER);

        try (var stream = new ObjectInputStream(new FileInputStream(chooser.showOpenDialog(stage)))) {
            GameStore store = (GameStore) stream.readObject();
            store.setGameState(GameState.LOBBY);

            return store;
        } catch (IOException | ClassNotFoundException ex) {
            Log.error("Error loading game", ex);
        } catch (NullPointerException ex) {
            Log.info("Player did not pick a file");
        }
        return null;
    }
}
