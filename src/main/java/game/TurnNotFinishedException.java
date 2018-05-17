package game;

/**
 * @author wesley
 */
public class TurnNotFinishedException extends Exception {
    public TurnNotFinishedException(String message) {
        super(message);
    }

    public TurnNotFinishedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TurnNotFinishedException(Throwable cause) {
        super(cause);
    }
}
