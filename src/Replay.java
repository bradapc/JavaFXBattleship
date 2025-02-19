import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class Replay extends Scene {
    private VBox mainPane;
    private MatchOutcome outcome;
    private ReplayBoard playerReplayBoard;
    private ReplayBoard enemyReplayBoard;
    private Text turnPlayer;
    private Text turnLabel;
    private final String userTextColor = "#039e03";
    private final String enemyTextColor = "#d92e2e";

    public Replay(MatchOutcome outcome) {
        this(new VBox());
        this.outcome = outcome;
        playerReplayBoard = new ReplayBoard();
        enemyReplayBoard = new ReplayBoard();
        Text turnText = new Text("Turn: ");
        turnText.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        turnLabel = turnText;
        Text turnPlayer = new Text("User");
        this.turnPlayer = turnPlayer;
        turnPlayer.setFont(Font.font("Elephant", FontWeight.BOLD, 18));
        turnPlayer.setStyle("-fx-fill: " + userTextColor + ";");
        HBox turnTextContainer = new HBox();
        turnTextContainer.getChildren().addAll(turnText, turnPlayer);
        turnTextContainer.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(enemyReplayBoard, turnTextContainer, playerReplayBoard);
        showReplay();
    }

    public Replay(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
    }

    private void setTurnPlayerText(String player) {
        if (player.equals("user")) {
            turnPlayer.setStyle("-fx-fill: " + userTextColor + ";");
            turnPlayer.setText("User");
        } else if (player.equals("enemy")) {
            turnPlayer.setStyle("-fx-fill: " + enemyTextColor + ";");
            turnPlayer.setText("Enemy");
        } else {
            turnPlayer.setStyle("-fx-fill: #0095ff;");
            turnPlayer.setText("WINNER: " + outcome.getWinner());
            turnLabel.setText("");
        }
    }

    public void showReplay() {
        final int[] i = {0};
        final int[] j = {0};
        final String[] turn = {"user"};
        int cycles = outcome.getPlayerGuesses().size() + outcome.getEnemyGuesses().size();
        Timeline timeline = new Timeline(
                new KeyFrame(
                    Duration.seconds(0.5),
                    event -> {
                        if (turn[0].equals("user")) {
                            setTurnPlayerText("user");
                            updateHits(enemyReplayBoard, i[0]);
                            i[0]++;
                            turn[0] = "enemy";
                        } else if (turn[0].equals("enemy")) {
                            setTurnPlayerText("enemy");
                            updateHits(playerReplayBoard, j[0]);
                            j[0]++;
                            turn[0] = "user";
                        }
                    }
                )
        );
        timeline.setCycleCount(cycles);
        timeline.play();
        timeline.setOnFinished(e -> {
            setTurnPlayerText("winner");
            Dialog<String> replayFinishedDialog = new Dialog<String>();
            replayFinishedDialog.setTitle("Replay Finished");
            ButtonType OK = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            replayFinishedDialog.setContentText("The replay ended after " + outcome.getTurnCount() + " turns" +
                    "\nOutcome: " + outcome.getWinner() + " wins." +
                    "\n\nPlayer had " + outcome.getHitRate("user") + " shooting percent." +
                    "\nEnemy had " + outcome.getHitRate("enemy") + " shooting percent.");
            replayFinishedDialog.getDialogPane().getButtonTypes().add(OK);
            replayFinishedDialog.show();
        });
    }

    public void updateHits(ReplayBoard board, int index) {
        ArrayList<Character[][]> guesses;
        if (board.equals(playerReplayBoard)) {
            guesses = outcome.getPlayerGuesses();
        } else {
            guesses = outcome.getEnemyGuesses();
        }
        Character[][] charBoard = guesses.get(index);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ReplayGameSquare current = board.getReplayGameSquare(i, j);
                if ((charBoard[i][j] - '0') > 0 && (charBoard[i][j] - '0') < 6) {
                    current.setFill(GameBoard.SUNK_SHIP);
                } else if (charBoard[i][j] == 'X') {
                    current.setFill(GameBoard.HIT_SHIP);
                } else if (charBoard[i][j] == '0') {
                    current.setFill(GameBoard.MISSED_HIT);
                } else {
                    current.setFill(GameBoard.WATER);
                }
            }
        }
    }
}
