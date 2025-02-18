import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.stage.Stage;

public class SinglePlayerController extends Scene {
    private InitializerService initializerService;
    private VBox mainPane;
    private ChatBox enemyChatBox;
    private ChatBox playerChatBox;
    private SinglePlayerService singlePlayerService;
    private Label turnText;
    private Stage primaryStage;

    public SinglePlayerController(Stage primaryStage) {
        this(new VBox());
        this.primaryStage = primaryStage;
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
        mainPane.setMinHeight(700);
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
        this.singlePlayerService = singlePlayerService;
        Label playerLabel = new Label("Player");
        Label enemyLabel = new Label("Enemy");
        Font boardLabelFont = Font.font("Times New Roman", FontWeight.BOLD, 20);
        playerLabel.setFont(boardLabelFont);
        enemyLabel.setFont(boardLabelFont);
        VBox gameBoardPane = new VBox();
        HBox enemyBoardChatBoxPane = new HBox();
        ChatBox enemyChatBox = new ChatBox();
        this.enemyChatBox = enemyChatBox;
        enemyBoardChatBoxPane.getChildren().addAll(enemyGameBoard, enemyChatBox);
        ChatBox playerChatBox = new ChatBox();
        this.playerChatBox = playerChatBox;
        HBox playerBoardChatBoxPane = new HBox();
        playerBoardChatBoxPane.getChildren().addAll(userGameBoard, playerChatBox);
        gameBoardPane.setPadding(new Insets(0, 0, 0, 10));
        Label turnText = new Label("Turn: USER (0)");
        turnText.setMinHeight(30);
        turnText.setFont(Font.font("Lucida Console", 18));
        this.turnText = turnText;
        gameBoardPane.getChildren().addAll(enemyLabel, enemyBoardChatBoxPane, turnText, playerBoardChatBoxPane, playerLabel);
        mainPane.getChildren().add(gameBoardPane);
        toggleRestartButton();
    }

    public void doWait() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            singlePlayerService.enemyHitRequest();
        });
        pause.play();
    }

    public void swapTurnText(String turn, int turnCount) {
        String turnStrCount = "(" + turnCount + ")";
        String turnStr = String.format("Turn: %-5s%s", turn, turnStrCount);
        turnText.setText(turnStr);
    }

    public ChatBox getEnemyChatBox() {
        return enemyChatBox;
    }

    public ChatBox getPlayerChatBox() {
        return playerChatBox;
    }

    public void toggleRestartButton() {
        playerChatBox.addRestartButton(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
