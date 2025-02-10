import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSquare extends Rectangle {
    private int row;
    private int col;
    private SinglePlayerGameboard singlePlayerGameboard;
    private String type;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;

    public GameSquare(SinglePlayerGameboard singlePlayerGameboard, int width, int height, int row, int col) {
        super(width, height);
        type = "empty";
        this.row = row;
        this.col = col;
        this.singlePlayerGameboard = singlePlayerGameboard;
        setStroke(Color.BLACK);
        setFill(Color.AQUA);
        setOnMouseEntered(e -> handleMouseEntry());
        setOnMouseExited(e -> handleMouseExit());
        setOnMouseClicked(this::handleMouseClick);
    }

    public void handleMouseClick(MouseEvent e) {
        //add mouse click event
    }

    public void handleMouseExit() {
        //handle mouse exit
    }

    public void handleMouseEntry() {
        //handle entry
    }

    public void setHoverColor(int row, int col, String orientation) {
        //handle color
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
