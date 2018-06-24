package client.ui.dialogs;

public class MessagesControllerProvider {

    private static MessagesController messagesController;

    public static MessagesController getMessageController() {
        return messagesController;
    }

    public static void setMessageController(MessagesController mesCon) {
        if (isMessageControllerSet()) {
            throw new IllegalStateException("Messages Controller may only be set one");
        }
        messagesController = mesCon;
    }

    private static boolean isMessageControllerSet() {
        return messagesController != null;
    }
}
