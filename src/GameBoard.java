import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class GameBoard extends GridPane {
    public String type;

    public GameBoard(String type) {
        this.type = type;
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                add(new GameSquare(GameSquare.WIDTH, GameSquare.HEIGHT, i, j), i, j);
            }
        }
    }
}
