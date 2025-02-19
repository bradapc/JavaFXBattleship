import javafx.scene.layout.GridPane;

public class ReplayBoard extends GridPane {
    private ReplayGameSquare[][] playerGameSquares;

    public ReplayBoard() {
        playerGameSquares = new ReplayGameSquare[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerGameSquares[i][j] = new ReplayGameSquare(InitializerGameSquare.WIDTH, InitializerGameSquare.HEIGHT, i, j);
                add(playerGameSquares[i][j], j, i);
            }
        }
    }
}
