import java.io.Serializable;
import java.time.LocalDate;

public class MatchOutcome implements Serializable {
    private String winner;
    private int turnCount;
    private LocalDate matchTime;

    //To-do: replay system?

    public MatchOutcome(String winner, int turnCount) {
        this.winner = winner;
        this.turnCount = turnCount;
        matchTime = LocalDate.now();
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
