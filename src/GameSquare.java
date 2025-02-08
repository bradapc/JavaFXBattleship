import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSquare extends Rectangle {
    private int row;
    private int col;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 30;

    public GameSquare(int width, int height, int row, int col) {
        super(width, height);
        this.row = row;
        this.col = col;
        setStroke(Color.BLACK);
        setFill(Color.AQUA);
    }
}
