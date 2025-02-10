import javafx.geometry.Pos;

public class SinglePlayerGameboard extends GameBoard {
    private GameSquare[][] gameSquares;
    private int[][] shipPlacements;

    public SinglePlayerGameboard(int[][] shipPlacements) {
        this.shipPlacements = shipPlacements;
        gameSquares = new GameSquare[10][10];
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j] = new GameSquare(this, InitializerGameSquare.WIDTH, InitializerGameSquare.HEIGHT, i, j);
                if (shipPlacements[i][j] > 0) {
                    gameSquares[i][j].setType(GameBoard.getTypeFromBoardNumber(shipPlacements[i][j]));
                }
                add(gameSquares[i][j], j, i);
            }
        }
        renderColors();
    }

    public GameSquare getGameSquare(int row, int col) {
        return gameSquares[row][col];
    }

    @Override
    public void renderColors() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j].setFill(gameSquares[i][j].getColorFromType());
            }
        }
    }
}
