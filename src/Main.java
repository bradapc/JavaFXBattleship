import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Scene mainMenuScene = new Scene(new MainMenuPane());
        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Battleship - Main Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}