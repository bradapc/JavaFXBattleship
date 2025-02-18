import javafx.scene.Scene;
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
                    setStyle("-fx-control-inner-background: green;");
                } else {
                    setText(item.getMatchTime() + ": " + item.getWinner() + " won in " + item.getTurnCount() + " turns.");
                    setStyle("-fx-control-inner-background: red;");
                }
            }
        });
        mainPane.getChildren().add(lv);
    }

    public MatchHistoryScene(VBox mainPane) {
        super(mainPane, 400, 400);
        this.mainPane = mainPane;
    }
}
