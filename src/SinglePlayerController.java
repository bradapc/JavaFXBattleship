import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SinglePlayerController extends Scene {
    private InitializerService initializerService;
    private VBox mainPane;

    public SinglePlayerController() {
        this(new VBox());
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
        mainPane.setMinHeight(675);
        mainPane.setMinWidth(700);
        initializerService = new InitializerService(this);
        initializerService.initializeShipPlacement();
    }

    public void startGame(int[][] shipPlacements) {
        SinglePlayerService singlePlayerService = new SinglePlayerService(shipPlacements, this);
        SinglePlayerGameboard userGameBoard = new SinglePlayerGameboard(shipPlacements, "USER");
        SinglePlayerGameboard enemyGameBoard = new SinglePlayerGameboard(initializerService.getEnemyBoard(), "ENEMY");
        singlePlayerService.setEnemyGameBoard(enemyGameBoard);
        singlePlayerService.setUserGameBoard(userGameBoard);
        Label playerLabel = new Label("Player");
        Label enemyLabel = new Label("Enemy");
        Font boardLabelFont = Font.font("Times New Roman", FontWeight.BOLD, 20);
        playerLabel.setFont(boardLabelFont);
        enemyLabel.setFont(boardLabelFont);
        VBox gameBoardPane = new VBox();
        HBox enemyBoardChatBoxPane = new HBox();
        ChatBox chatBox = new ChatBox();
        enemyBoardChatBoxPane.getChildren().addAll(enemyGameBoard, chatBox);
        gameBoardPane.setPadding(new Insets(0, 0, 0, 10));
        Region spacer = new Region();
        spacer.setPrefHeight(40);
        gameBoardPane.getChildren().addAll(enemyLabel, enemyBoardChatBoxPane, spacer, userGameBoard, playerLabel);
        mainPane.getChildren().add(gameBoardPane);
    }
}
