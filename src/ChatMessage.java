import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChatMessage extends Label {
    public ChatMessage(String msg) {
        super(msg);
        setWrapText(true);
        setMinHeight(60);
        setFont(Font.font("Verdana", 18));
    }

    public ChatMessage(String msg, boolean bolded) {
        this(msg);
        setFont(Font.font("Verdana", FontWeight.BOLD, 18));
    }
}
