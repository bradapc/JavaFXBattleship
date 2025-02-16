import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AbandonButton extends Button {

    public AbandonButton(SinglePlayerController singlePlayerController) {super("Abandon");
        setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        setStyle("-fx-border-color: black; -fx-border-radius: 8px; -fx-text-fill: red; -fx-focus-color: transparent;");
        setOnAction(e ->
        {
            Stage primaryStage = singlePlayerController.getPrimaryStage();
            primaryStage.setScene(new Scene(new MainMenuPane(primaryStage)));
        });
    }
}
