import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SinglePlayerService {
    private int[][] shipLocations;
    private Stage initializerStage;
    private Label chooseShipLBL;
    private GameBoard initializerGameBoard;

    public SinglePlayerService() {
        shipLocations = new int[10][10];
    }

    public void initializeShipPlacement() {
        Stage initializerStage = new Stage();
        this.initializerStage = initializerStage;

        GameBoard initializerGameBoard = new GameBoard("initializer");
        this.initializerGameBoard = initializerGameBoard;
        initializerGameBoard.setSinglePlayerService(this);

        Label chooseShipLBL = new Label("Place your " + initializerGameBoard.getCurrentShipType());
        this.chooseShipLBL = chooseShipLBL;
        chooseShipLBL.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        VBox initializerPane = new VBox();
        initializerPane.getChildren().addAll(chooseShipLBL, initializerGameBoard);
        initializerPane.setAlignment(Pos.CENTER);

        Scene initializerScene = new Scene(initializerPane);
        initializerStage.setScene(initializerScene);
        initializerStage.setTitle("Choose Ship Placements");
        initializerStage.show();
    }

    public void updateChooseShipLBL() {
        chooseShipLBL.setText("Place your " + initializerGameBoard.getCurrentShipType());
    }

    public void startGame(int[][] shipPlacements) {
        shipLocations = shipPlacements;
        initializerStage.close();
    }
}
