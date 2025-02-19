import javafx.scene.layout.GridPane;

public class ReplayBoard extends GridPane {
    private ReplayGameSquare[][] gameSquares;

    public ReplayBoard() {
        gameSquares = new ReplayGameSquare[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j] = new ReplayGameSquare(InitializerGameSquare.WIDTH, InitializerGameSquare.HEIGHT, i, j);
                add(gameSquares[i][j], j, i);
            }
        }
    }

    public ReplayGameSquare getReplayGameSquare(int i, int j) {
        return gameSquares[i][j];
    }
}
