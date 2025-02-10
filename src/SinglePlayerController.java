import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SinglePlayerController extends Scene {
    private InitializerService initializerService;
    private VBox mainPane;

    public SinglePlayerController() {
        this(new VBox());
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
        mainPane.setSpacing(30);
        mainPane.setMinHeight(700);
        mainPane.setMinWidth(500);
        initializerService = new InitializerService(this);
        initializerService.initializeShipPlacement();
    }

    public void startGame(int[][] shipPlacements) {
        SinglePlayerService singlePlayerService = new SinglePlayerService(shipPlacements, this);
        SinglePlayerGameboard userGameBoard = new SinglePlayerGameboard(shipPlacements, "USER");
        SinglePlayerGameboard enemyGameBoard = new SinglePlayerGameboard(singlePlayerService.generateShipPlacements(), "ENEMY");
        mainPane.getChildren().addAll(enemyGameBoard, userGameBoard);
    }
}
