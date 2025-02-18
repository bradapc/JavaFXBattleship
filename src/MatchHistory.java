import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MatchHistory extends Scene {
    VBox mainPane;

    public MatchHistory() {
        this(new VBox());
    }

    public MatchHistory(VBox mainPane) {
        super(mainPane, 400, 400);
        this.mainPane = mainPane;
    }

}
