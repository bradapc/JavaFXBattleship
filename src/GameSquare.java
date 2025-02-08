import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSquare extends Rectangle {
    private int row;
    private int col;
    private GameBoard parentGameBoard;
    private String type;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;

    public GameSquare(GameBoard parentGameBoard, int width, int height, int row, int col) {
        super(width, height);
        type = "empty";
        this.row = row;
        this.col = col;
        this.parentGameBoard = parentGameBoard;
        setStroke(Color.BLACK);
        setFill(Color.AQUA);
        setOnMouseEntered(e -> handleMouseEntry(parentGameBoard.getType()));
        setOnMouseExited(e -> handleMouseExit(parentGameBoard.getType()));
        setOnMouseClicked(e -> handleMouseClick(parentGameBoard.getType()));
    }

    public void handleMouseClick(String type) {
        parentGameBoard.tryToInitialize(row, col);
    }

    public void handleMouseExit(String type) {
        if (type.equals("user") || type.equals("enemy")) {
            setFill(Color.AQUA);
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    GameSquare current = parentGameBoard.getGameSquare(i, j);
                    if (current.getType().equals("empty")) {
                        current.setFill(Color.AQUA);
                    }
                }
            }
        }
    }

    public void handleMouseEntry(String type) {
        if (type.equals("user") || type.equals("enemy")) {
            setFill(Color.AQUAMARINE);
        } else {
            for (int i = 0; i <= parentGameBoard.getShipsToPlace(); i++) {
                if (row + parentGameBoard.getShipsToPlace() < 10) {
                    if (row + i < 10) {
                        GameSquare current = parentGameBoard.getGameSquare(row + i, col);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.YELLOW);
                        }
                    }
                } else {
                    if (row + i < 10) {
                        GameSquare current = parentGameBoard.getGameSquare(row + i, col);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.RED);
                        }
                    }
                }
            }
        }
    }

    public void updateColor() {
        if (!type.equals("empty")) {
            setFill(Color.BLACK);
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
