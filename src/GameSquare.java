import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSquare extends Rectangle {
    private int row;
    private int col;
    private SinglePlayerGameboard parentGameboard;
    private String type;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;
    boolean hit;

    public GameSquare(SinglePlayerGameboard parentGameboard, int width, int height, int row, int col) {
        super(width, height);
        type = "empty";
        this.row = row;
        this.col = col;
        this.parentGameboard = parentGameboard;
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
        if (parentGameboard.getBoardType().equals("ENEMY")) {
            setFill(Color.AQUA);
        }
    }

    public void handleMouseEntry() {
        if (parentGameboard.getBoardType().equals("ENEMY")) {
            setFill(Color.YELLOW);
        }
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

    public boolean isHit() {
        return hit;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
