import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;

public class Replay extends Scene {
    private VBox mainPane;
    private MatchOutcome outcome;
    private ReplayBoard playerReplayBoard;
    private ReplayBoard enemyReplayBoard;

    public Replay(MatchOutcome outcome) {
        this(new VBox());
        this.outcome = outcome;
        playerReplayBoard = new ReplayBoard();
        enemyReplayBoard = new ReplayBoard();
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(enemyReplayBoard, playerReplayBoard);
        showReplay();
    }

    public Replay(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;
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
                            updateHits(enemyReplayBoard, i[0]);
                            i[0]++;
                            turn[0] = "enemy";
                        } else if (turn[0].equals("enemy")) {
                            updateHits(playerReplayBoard, j[0]);
                            j[0]++;
                            turn[0] = "user";
                        }
                    }
                )
        );
        timeline.setCycleCount(cycles);
        timeline.play();
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
