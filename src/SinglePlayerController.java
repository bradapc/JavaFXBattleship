import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SinglePlayerController extends Scene {
    private VBox mainPane;
    private InitializerGameBoard userInitializerGameBoard;
    private InitializerGameBoard enemyInitializerGameBoard;
    private InitializerService initializerService;

    public SinglePlayerController() {
        this(new VBox());
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
        initializerService = new InitializerService(this);
        initializerService.initializeShipPlacement();
        mainPane.setMinHeight(500);
        mainPane.setMinWidth(500);
    }

    public void startGame(int[][] shipPlacements) {

    }
}
