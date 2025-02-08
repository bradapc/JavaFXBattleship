import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameBoard extends GridPane {
    private String type;
    private int shipsToPlace = 0;
    private GameSquare[][] gameSquares;

    public GameBoard(String type) {
        this.type = type;
        if (type.equals("initializer")) {
            shipsToPlace = 5;
        }
        gameSquares = new GameSquare[10][10];
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j] = new GameSquare(this, GameSquare.WIDTH, GameSquare.HEIGHT, i, j);
                add(gameSquares[i][j], i, j);
            }
        }
    }

    public GameSquare getGameSquare(int row, int col) {
        return gameSquares[row][col];
    }

    public String getType() {
        return type;
    }

    public int getShipsToPlace() {
        return shipsToPlace;
    }

    void setShipsToPlace(int shipsToPlace) {
        this.shipsToPlace = shipsToPlace;
    }
}
