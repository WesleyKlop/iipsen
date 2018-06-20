package client.ui;

public class MessagesControllerProvider {

    private static MessagesController messagesController;

    public static MessagesController getMessageController() {
        return messagesController;
    }

    public static void setMessageController(MessagesController mesCon) {
        if (messagesController != null) {
            throw new IllegalStateException("Messages Controller may only be set one");
        }
        messagesController = mesCon;
    }
}
