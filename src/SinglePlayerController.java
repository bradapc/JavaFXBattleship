import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SinglePlayerController extends Scene {
    private VBox mainPane;
    private GameBoard userGameBoard;
    private GameBoard enemyGameBoard;
    private SinglePlayerService singlePlayerService;

    public SinglePlayerController() {
        this(new VBox());
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
        singlePlayerService = new SinglePlayerService();
        singlePlayerService.initializeShipPlacement();
        mainPane.setMinHeight(500);
        mainPane.setMinWidth(500);
        userGameBoard = new GameBoard("user");
        mainPane.getChildren().add(userGameBoard);
    }
}
