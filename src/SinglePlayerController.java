import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SinglePlayerController extends Scene {
    private VBox mainPane;
    private InitializerGameBoard userInitializerGameBoard;
    private InitializerGameBoard enemyInitializerGameBoard;
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
        userInitializerGameBoard = new InitializerGameBoard();
        mainPane.getChildren().add(userInitializerGameBoard);
    }
}
