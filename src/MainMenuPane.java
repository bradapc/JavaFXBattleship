import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainMenuPane extends VBox {
    public MainMenuPane() {
        Image titleImage = new Image("https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/893050/header.jpg?t=1734531056");
        ImageView titleImageView = new ImageView(titleImage);
        titleImageView.setFitHeight(200);
        titleImageView.setFitWidth(350);
        getChildren().add(titleImageView);
    }
}
