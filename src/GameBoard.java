import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameBoard extends GridPane {
    private String type;
    private int shipsToPlace = 0;
    private GameSquare[][] gameSquares;
    public static final String[] types = {
            "carrier",
            "battleship",
            "cruiser",
            "submarine",
            "destroyer"
    };
    public static final int[] sizes = {2, 3, 3, 4, 5};

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
                add(gameSquares[i][j], j, i);
            }
        }
    }

    public void renderColors() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j].setFill(gameSquares[i][j].getColorFromType());
            }
        }
    }

    public void tryToInitialize(int row, int col) {
        if(shipsToPlace > 0) {
            if (isValidInitializerChoice(row, col)) {
                for (int i = col; i < col + getCurrentShipSize(); i++) {
                    gameSquares[row][i].setType(types[shipsToPlace - 1]);
                }
                shipsToPlace--;
                renderColors();
            }
        }
    }

    public boolean isValidInitializerChoice(int row, int col) {
        if (shipsToPlace <= 0) {
            return false;
        }
        for (int i = col; i < col + getCurrentShipSize(); i++) {
            if (i >= 10) {
                return false;
            }
            GameSquare current = gameSquares[row][i];
            if (!current.getType().equals("empty")) {
                return false;
            }
        }
        return true;
    }

    public int getCurrentShipSize() {
        if (shipsToPlace == 0) {
            return 0;
        }
        return sizes[shipsToPlace - 1];
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
