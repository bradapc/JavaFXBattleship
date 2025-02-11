import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class InitializerService {
    private Stage initializerStage;
    private Label chooseShipLBL;
    private InitializerGameBoard initializerGameBoard;
    private SinglePlayerController singlePlayerController;

    public InitializerService(SinglePlayerController singlePlayerController) {
        this.singlePlayerController = singlePlayerController;
    }

    public void initializeShipPlacement() {
        Stage initializerStage = new Stage();
        this.initializerStage = initializerStage;

        InitializerGameBoard initializerGameBoard = new InitializerGameBoard();
        this.initializerGameBoard = initializerGameBoard;
        initializerGameBoard.setSinglePlayerService(this);

        Label chooseShipLBL = new Label("Place your " + initializerGameBoard.getCurrentShipType());
        this.chooseShipLBL = chooseShipLBL;
        chooseShipLBL.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        Label chooseOrientationLBL = new Label("Change orientation with Right Mouse Button.");
        chooseOrientationLBL.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 16));

        VBox initializerPane = new VBox();
        initializerPane.getChildren().addAll(chooseShipLBL, chooseOrientationLBL, initializerGameBoard);
        initializerPane.setAlignment(Pos.CENTER);

        Scene initializerScene = new Scene(initializerPane);
        initializerStage.setScene(initializerScene);
        initializerStage.setTitle("Choose Ship Placements");
        initializerStage.setResizable(false);
        initializerStage.show();
    }

    public int[][] getEnemyBoard() {
        int shipsToPlace = 5;
        int[][] enemyGameBoard = new int[10][10];
        while (shipsToPlace > 0) {
            int posRow = (int)(Math.random() * 10);
            int posCol = (int)(Math.random() * 10);
            int currentSize = GameBoard.getCurrentShipSize(shipsToPlace);
            String orientation = (int)(Math.random() * 2) == 0 ? "HORIZONTAL" : "VERTICAL";
            if (orientation.equals("HORIZONTAL")) {
                if (!(posCol + currentSize > 9)) {
                    boolean validPlacement = true;
                    for (int i = posCol; i < posCol + currentSize; i++) {
                        if (enemyGameBoard[posRow][i] > 0) {
                            validPlacement = false;
                        }
                    }
                    if (validPlacement) {
                        for (int i = posCol; i < posCol + currentSize; i++) {
                            enemyGameBoard[posRow][i] = currentSize;
                        }
                        shipsToPlace--;
                    }
                }
            } else {
                if (!(posRow + currentSize > 9)) {
                    boolean validPlacement = true;
                    for (int i = posRow; i < posRow + currentSize; i++) {
                        if (enemyGameBoard[i][posCol] > 0) {
                            validPlacement = false;
                        }
                    }
                    if (validPlacement) {
                        for (int i = posRow; i < posRow + currentSize; i++) {
                            enemyGameBoard[i][posCol] = currentSize;
                        }
                        shipsToPlace--;
                    }
                }
            }
        }
        return enemyGameBoard;
    }

    public void updateChooseShipLBL() {
        chooseShipLBL.setText("Place your " + initializerGameBoard.getCurrentShipType());
    }

    public void startGame(int[][] shipPlacements) {
        initializerStage.close();
        singlePlayerController.startGame(shipPlacements);
    }
}
