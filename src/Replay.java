import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Replay extends Scene {
    private VBox mainPane;
    private MatchOutcome outcome;

    public Replay(MatchOutcome outcome) {
        this(new VBox());
        this.outcome = outcome;
    }

    public Replay(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
    }
}
