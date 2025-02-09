import javafx.scene.layout.GridPane;

abstract public class GameBoard extends GridPane {
    private GameSquare[][] gameSquares;
    public static final String[] types = {
            "carrier",
            "battleship",
            "cruiser",
            "submarine",
            "destroyer"
    };
    public static final int[] sizes = {2, 3, 3, 4, 5};
    protected int[][] shipPlacements;

    public abstract void renderColors();

    public abstract GameSquare getGameSquare(int row, int col);
}
