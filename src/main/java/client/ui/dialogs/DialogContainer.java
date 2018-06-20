package client.ui.dialogs;

import javafx.scene.Node;

/**
 * @author Wesley Klop
 */
public interface DialogContainer {

    void showDialog(Node dialog);

    void clearDialog();
}
