import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MatchOutcome implements Serializable {
    private String winner;
    private int turnCount;
    private LocalDate matchTime;
    private ArrayList<Character[][]> playerGuesses;
    private ArrayList<Character[][]> enemyGuesses;

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
