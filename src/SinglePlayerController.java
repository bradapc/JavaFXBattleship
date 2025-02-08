import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SinglePlayerController extends Scene {
    private VBox mainPane;

    public SinglePlayerController() {
        this(new VBox());
    }

    public SinglePlayerController(VBox mainPane) {
        super(mainPane);
        this.mainPane = mainPane;

    }
}
