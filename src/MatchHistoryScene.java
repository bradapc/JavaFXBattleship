import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class MatchHistoryScene extends Scene {
    private VBox mainPane;
    private MatchHistory matchHistory;

    public MatchHistoryScene() {
        this(new VBox());
        matchHistory = new MatchHistory();
        ListView<MatchOutcome> lv = new ListView<MatchOutcome>();
        lv.getItems().setAll(matchHistory.getHistory());
        lv.setCellFactory(param -> new ListCell<MatchOutcome>() {
            @Override
            protected void updateItem(MatchOutcome item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getWinner() == null) {
                    setText(null);
                } else if (item.getWinner().equals("user")) {
                    setText(item.getMatchTime() + ": " + item.getWinner() + " won in " + item.getTurnCount() + " turns.");
                    setStyle("-fx-control-inner-background: #039e03; -fx-cell-size: 40px; -fx-font-family: Book Antiqua; -fx-font-size: 16px");
                } else {
                    setText(item.getMatchTime() + ": " + item.getWinner() + " won in " + item.getTurnCount() + " turns.");
                    setStyle("-fx-control-inner-background: #d92e2e;  -fx-cell-size: 40px; -fx-font-family: Book Antiqua; -fx-font-size: 16px");
                }
            }
        });
        Button viewReplayButton = new Button("View Selected Replay");
        viewReplayButton.setStyle("-fx-font-family: Verdana; -fx-font-size: 16px;");
        viewReplayButton.setOnAction(e -> {
            MatchOutcome current = lv.getSelectionModel().getSelectedItem();
            if (current == null) {
                return;
            }
            //add replay
        });
        mainPane.getChildren().addAll(viewReplayButton, lv);
    }

    public MatchHistoryScene(VBox mainPane) {
        super(mainPane, 400, 400);
        this.mainPane = mainPane;
    }
}
