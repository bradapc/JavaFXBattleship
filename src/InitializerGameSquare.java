import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InitializerGameSquare extends Rectangle {
    private int row;
    private int col;
    private InitializerGameBoard parentInitializerGameBoard;
    private String type;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;

    public InitializerGameSquare(InitializerGameBoard parentInitializerGameBoard, int width, int height, int row, int col) {
        super(width, height);
        type = "empty";
        this.row = row;
        this.col = col;
        this.parentInitializerGameBoard = parentInitializerGameBoard;
        setStroke(Color.BLACK);
        setFill(Color.AQUA);
        setOnMouseEntered(e -> handleMouseEntry());
        setOnMouseExited(e -> handleMouseExit());
        setOnMouseClicked(this::handleMouseClick);
    }

    public void handleMouseClick(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            parentInitializerGameBoard.tryToInitialize(row, col);
        } else if (e.getButton() == MouseButton.SECONDARY) {
            parentInitializerGameBoard.flipOrientation();
            //Redraw old orientation colors
            handleMouseExit();
            setHoverColor(row, col, parentInitializerGameBoard.getOrientation());
        }
    }

    public void handleMouseExit() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                InitializerGameSquare current = parentInitializerGameBoard.getGameSquare(i, j);
                if (current.getType().equals("empty")) {
                    current.setFill(Color.AQUA);
                }
            }
        }
    }

    public void handleMouseEntry() {
        setHoverColor(row, col, parentInitializerGameBoard.getOrientation());
    }

    public void setHoverColor(int row, int col, String orientation) {
        for (int i = 0; i < parentInitializerGameBoard.getCurrentShipSize(); i++) {
            if (orientation.equals("HORIZONTAL")) {
                if (col + parentInitializerGameBoard.getCurrentShipSize() - 1 < 10) {
                    if (col + i < 10) {
                        InitializerGameSquare current = parentInitializerGameBoard.getGameSquare(row, col + i);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.YELLOW);
                        }
                    }
                } else {
                    if (col + i < 10) {
                        InitializerGameSquare current = parentInitializerGameBoard.getGameSquare(row, col + i);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.RED);
                        }
                    }
                }
            } else if (orientation.equals("VERTICAL")) {
                if (row + parentInitializerGameBoard.getCurrentShipSize() - 1 < 10) {
                    if (row + i < 10) {
                        InitializerGameSquare current = parentInitializerGameBoard.getGameSquare(row + i, col);
                        if (current.getType().equals("empty")) {
                            current.setFill(Color.YELLOW);
                        }
                    }
                } else {
                    if (row + i < 10) {
                        InitializerGameSquare current = parentInitializerGameBoard.getGameSquare(row + i, col);
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
