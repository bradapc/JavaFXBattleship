import javafx.scene.Scene;
import javafx.stage.Stage;

public class SinglePlayerService {
    private int[][] shipLocations;
    private Stage initializerStage;

    public SinglePlayerService() {
        shipLocations = new int[10][10];
    }

    public void initializeShipPlacement() {
        Stage initializerStage = new Stage();
        this.initializerStage = initializerStage;
        GameBoard initializerGameBoard = new GameBoard("initializer");
        initializerGameBoard.setSinglePlayerService(this);
        Scene initializerScene = new Scene(initializerGameBoard);
        initializerStage.setScene(initializerScene);
        initializerStage.setTitle("Choose Ship Placements");
        initializerStage.show();
    }

    public void startGame(int[][] shipPlacements) {
        initializerStage.close();
    }
}
