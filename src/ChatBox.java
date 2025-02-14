import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class ChatBox extends GridPane {
    ArrayList<ChatMessage> chatMessages;

    public ChatBox() {
        chatMessages = new ArrayList<>();
        setMinWidth(350);
        setMaxWidth(350);
        setMinHeight(300);
        getRowConstraints().add(new RowConstraints(60));
        setStyle("-fx-background-color: lightgray");
        setPadding(new Insets(0, 0, 0, 8));
        chatMessages.add(new ChatMessage("Enemy has sunk your battleship. That's too bad lol."));
        chatMessages.add(new ChatMessage("You shoot at 1,1. Miss!"));
        chatMessages.add(new ChatMessage("Enemy shoots at 1,5. Miss!"));
        chatMessages.add(new ChatMessage("You have hit the enemy's destroyer! Are you secretly a pro?"));
        updateChatMessages();
    }

    public void addChatMessage(ChatMessage c) {
        if (chatMessages.size() == 5) {
            chatMessages.removeFirst();
        }
        chatMessages.add(c);
        updateChatMessages();
    }

    public void updateChatMessages() {
        getChildren().clear();
        for (int i = 0; i < chatMessages.size(); i++) {
            add(chatMessages.get(i), 0, i);
        }
    }
}
