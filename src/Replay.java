import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class Replay extends Scene {
    private VBox mainPane;
    private MatchOutcome outcome;
    private ReplayBoard playerReplayBoard;
    private ReplayBoard enemyReplayBoard;

    public Replay(MatchOutcome outcome) {
        this(new VBox());
        this.outcome = outcome;
        playerReplayBoard = new ReplayBoard();
        enemyReplayBoard = new ReplayBoard();
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(playerReplayBoard, enemyReplayBoard);
    }

    public Replay(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
    }
}
