import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSquare extends Rectangle {
    private int row;
    private int col;
    private InitializerGameBoard parentInitializerGameBoard;
    private String type;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;

    public GameSquare(InitializerGameBoard parentInitializerGameBoard, int width, int height, int row, int col) {
        super(width, height);
        type = "empty";
        this.row = row;
        this.col = col;
        this.parentInitializerGameBoard = parentInitializerGameBoard;
        setStroke(Color.BLACK);
        setFill(Color.AQUA);
        setOnMouseEntered(e -> handleMouseEntry());
        setOnMouseExited(e -> handleMouseExit());
        setOnMouseClicked(e -> handleMouseClick());
    }

    public void handleMouseClick() {
        parentInitializerGameBoard.tryToInitialize(row, col);
    }

    public void handleMouseExit() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameSquare current = parentInitializerGameBoard.getGameSquare(i, j);
                if (current.getType().equals("empty")) {
                    current.setFill(Color.AQUA);
                }
            }
        }
    }

    public void handleMouseEntry() {
        for (int i = 0; i < parentInitializerGameBoard.getCurrentShipSize(); i++) {
            if (col + parentInitializerGameBoard.getCurrentShipSize() - 1 < 10) {
                if (col + i < 10) {
                    GameSquare current = parentInitializerGameBoard.getGameSquare(row, col + i);
                    if (current.getType().equals("empty")) {
                        current.setFill(Color.YELLOW);
                    }
                }
            } else {
                if (col + i < 10) {
                    GameSquare current = parentInitializerGameBoard.getGameSquare(row, col + i);
                    if (current.getType().equals("empty")) {
                        current.setFill(Color.RED);
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
