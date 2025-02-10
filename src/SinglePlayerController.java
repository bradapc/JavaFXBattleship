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
        initializerService = new InitializerService(this);
        initializerService.initializeShipPlacement();
        mainPane.setMinHeight(500);
        mainPane.setMinWidth(500);
    }

    public void startGame(int[][] shipPlacements) {
        SinglePlayerService singlePlayerService = new SinglePlayerService(shipPlacements, this);
        SinglePlayerGameboard singlePlayerGameboard = new SinglePlayerGameboard(shipPlacements);
        mainPane.getChildren().add(singlePlayerGameboard);

    }
}
