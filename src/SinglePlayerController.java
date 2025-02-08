import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SinglePlayerController extends Scene {
    private VBox mainPane;
    private GameBoard userGameBoard;
    private GameBoard enemyGameBoard;

    public SinglePlayerController() {
        this(new VBox());
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
        mainPane.setMinHeight(500);
        mainPane.setMinWidth(500);
        userGameBoard = new GameBoard("user");
        mainPane.getChildren().add(userGameBoard);
    }
}
