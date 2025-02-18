import java.io.*;
import java.util.ArrayList;

public class MatchHistory {
    private ArrayList<MatchOutcome> history;

    public MatchHistory() {
        try {
            history = loadMatchHistory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            history = new ArrayList<MatchOutcome>();
        }
    }

    public void saveMatchOutcome(MatchOutcome mo) {
        history.add(mo);
        saveMatchHistory();
    }

    public void saveMatchHistory() {
        File matchHistoryFile = new File("matchHistory");
        try {
            if (!matchHistoryFile.exists()) {
                matchHistoryFile.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(matchHistoryFile));
            oos.writeObject(history);
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<MatchOutcome> loadMatchHistory() throws Exception {
        ArrayList<MatchOutcome> history;
        File matchHistoryFile = new File("matchHistory");
        if (!matchHistoryFile.exists()) {
            matchHistoryFile.createNewFile();
            return new ArrayList<MatchOutcome>();
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(matchHistoryFile));
        history = (ArrayList<MatchOutcome>) ois.readObject();
        return history;
    }

    public ArrayList<MatchOutcome> getHistory() {
        return history;
    }
}
