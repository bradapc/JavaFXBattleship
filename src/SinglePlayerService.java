import javafx.scene.Scene;
import javafx.stage.Stage;

public class SinglePlayerService {
    private int[][] shipLocations;

    public SinglePlayerService() {
        shipLocations = new int[10][10];
    }

    public void initializeShipPlacement() {
        Stage initializerStage = new Stage();
        GameBoard initializerGameBoard = new GameBoard("initializer");
        Scene initializerScene = new Scene(initializerGameBoard);
        initializerStage.setScene(initializerScene);
        initializerStage.setTitle("Choose Ship Placements");
        initializerStage.show();

        while (initializerGameBoard.getShipsToPlace() > 0) {
            break;
        }
    }
}
