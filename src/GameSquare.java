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
    private boolean hit;

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
        parentGameboard.getSinglePlayerService().playerHitRequest(this);
    }

    public void handleMouseExit() {
        if (parentGameboard.getBoardType().equals("ENEMY") && !hit && !parentGameboard.getSinglePlayerService().isGameOver()) {
            setFill(Color.AQUA);
        }
    }

    public void handleMouseEntry() {
        if (parentGameboard.getBoardType().equals("ENEMY") && !hit && !parentGameboard.getSinglePlayerService().isGameOver()) {
            setFill(Color.YELLOW);
        }
    }

    public Color getColorFromType() {
        if (type.equals("destroyer") || type.equals("submarine") ||
                type.equals("cruiser") || type.equals("battleship") || type.equals("carrier")) {
            if (hit) return GameBoard.HIT_SHIP;
            if (parentGameboard.getBoardType().equals("USER")) {
                return GameBoard.HIDDEN_PLAYER_SHIP;
            }
            return Color.BLACK;
        }
        return GameBoard.WATER;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getCoordString() {
        return "(" + row + "," + col + ")";
    }

    public SinglePlayerGameboard getParentGameboard() {
        return parentGameboard;
    }
}
