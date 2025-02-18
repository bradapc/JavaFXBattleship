import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;

public class MatchHistory extends Scene {
    private VBox mainPane;
    private ArrayList<MatchOutcome> history;

    public MatchHistory() {
        this(new VBox());
    }

    public MatchHistory(VBox mainPane) {
        super(mainPane, 400, 400);
        this.mainPane = mainPane;
        try {
            history = loadMatchHistory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<MatchOutcome> loadMatchHistory() throws Exception {
        ArrayList<MatchOutcome> history;
        File matchHistoryFile = new File("matchHistory");
        if (!matchHistoryFile.exists()) {
            matchHistoryFile.createNewFile();
            return null;
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(matchHistoryFile));
        history = (ArrayList<MatchOutcome>) ois.readObject();
        return history;
    }
}
