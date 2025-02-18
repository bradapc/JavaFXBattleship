public class MatchOutcome {
    private String winner;
    private int turnCount;

    //To-do: replay system?

    public MatchOutcome(String winner, int turnCount) {
        this.winner = winner;
        this.turnCount = turnCount;
    }

    public String getWinner() {
        return winner;
    }

    public int getTurnCount() {
        return turnCount;
    }
}
