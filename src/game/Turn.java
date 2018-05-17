package game;

/**
 * @author wesley
 */
public interface Turn {
    void updateGameState(GameState state);

    boolean isFinished();
}
