import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MatchOutcome implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private String winner;
    private int turnCount;
    private LocalDate matchTime;
    private ArrayList<Character[][]> playerGuesses;
    private ArrayList<Character[][]> enemyGuesses;

    public double getHitRate(String player) {
        Character[][] finalBoard;
        if (player.equals("user")) {
            finalBoard = enemyGuesses.getLast();
        } else {
            finalBoard = playerGuesses.getLast();
        }
        int shipsHit = 0;
        int hits = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (finalBoard[i][j] == '.') continue;
                hits++;
                if (finalBoard[i][j] == 'X' || (finalBoard[i][j] > '0' && finalBoard[i][j] < '6')) {
                    shipsHit++;
                }
            }
        }
        return Math.round(100 * ((double) shipsHit / hits));
    }

    public ArrayList<Character[][]> getPlayerGuesses() {
        return playerGuesses;
    }

    public ArrayList<Character[][]> getEnemyGuesses() {
        return enemyGuesses;
    }

    public void addPlayerGuess(Character[][] board) {
        playerGuesses.add(board);
    }

    public void addEnemyGuess(Character[][] board) {
        enemyGuesses.add(board);
    }

    public MatchOutcome() {
        matchTime = LocalDate.now();
        playerGuesses = new ArrayList<>();
        enemyGuesses = new ArrayList<>();
    }

    public void setOutcome(String winner, int turnCount) {
        this.winner = winner;
        this.turnCount = turnCount;
    }

    public String getWinner() {
        return winner;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public LocalDate getMatchTime() {
        return matchTime;
    }
}
