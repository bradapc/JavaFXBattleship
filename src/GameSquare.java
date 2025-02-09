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
        setOnMouseClicked(e -> handleMouseClick());
    }

    public void handleMouseClick() {
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
        if (type.equals("destroyer")) {
            return;
        } else {
            for (int i = 0; i < parentGameBoard.getCurrentShipSize(); i++) {
                if (col + parentGameBoard.getCurrentShipSize() - 1 < 10) {
                    if (col + i < 10) {
                        GameSquare current = parentGameBoard.getGameSquare(row, col + i);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.YELLOW);
                        }
                    }
                } else {
                    if (col + i < 10) {
                        GameSquare current = parentGameBoard.getGameSquare(row, col + i);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.RED);
                        }
                    }
                }
            }
        }
    }

    public Color getColorFromType() {
        if (type.equals("destroyer") || type.equals("submarine") ||
        type.equals("cruiser") || type.equals("battleship") || type.equals("carrier")) {
            return Color.BLACK;
        }
        return Color.AQUA;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
