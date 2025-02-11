import javafx.scene.layout.GridPane;

abstract public class GameBoard extends GridPane {
    private InitializerGameSquare[][] initializerGameSquares;
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

    public static String getTypeFromBoardNumber(int i) {
        return types[i - 1];
    }

    public static int getCurrentShipSize(int shipsToPlace) {
        if (shipsToPlace == 0) {
            return 0;
        }
        return sizes[shipsToPlace - 1];
    }
}
