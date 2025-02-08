import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuPane extends VBox {
    public MainMenuPane() {
        Image titleImage = new Image("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/893050/header.jpg?t=1734531056");
        ImageView titleImageView = new ImageView(titleImage);
        titleImageView.setFitHeight(200);
        titleImageView.setFitWidth(350);
        HBox buttonHolder = new HBox();
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.setSpacing(30);
        Button playSingleButton = new Button("Play Singleplayer");
        Button playMultiButton = new Button("Play Multiplayer");
        setSpacing(20);
        setPadding(new Insets(0, 0, 15, 0));
        buttonHolder.getChildren().addAll(playSingleButton, playMultiButton);
        getChildren().addAll(titleImageView, buttonHolder);
    }
}
