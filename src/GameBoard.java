import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameBoard extends GridPane {
    private String type;
    private int shipsToPlace = 0;
    private GameSquare[][] gameSquares;
    String[] types = {
            "carrier",
            "battleship",
            "cruiser",
            "submarine",
            "destroyer"
    };
    int[] sizes = {2, 3, 3, 4, 5};

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

    public void tryToInitialize(int row, int col) {
        if(shipsToPlace > 0) {
            if (isValidInitializerChoice(row, col)) {
                for (int i = row; i <= sizes[shipsToPlace - 1]; i++) {
                    gameSquares[i][col].setType(types[shipsToPlace - 1]);
                    gameSquares[i][col].updateColor();
                }
                shipsToPlace--;
            }
        }
    }

    public boolean isValidInitializerChoice(int row, int col) {
        for (int i = row; i < shipsToPlace; i++) {
            if (!gameSquares[i][col].getType().equals("empty")) {
                return false;
            }
        }
        return true;
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
