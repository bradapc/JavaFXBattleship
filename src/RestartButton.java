import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RestartButton extends Button {
    public RestartButton(SinglePlayerController singlePlayerController) {
        super("Play Again");
        setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setStyle("-fx-border-color: black; -fx-border-radius: 8px; -fx-text-fill: green; -fx-focus-color: transparent;");
        setOnAction(e -> {
            Stage primaryStage = singlePlayerController.getPrimaryStage();
            primaryStage.setScene(new SinglePlayerController(primaryStage));
        });
    }
}
