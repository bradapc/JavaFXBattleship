import javafx.geometry.Pos;

public class InitializerGameBoard extends GameBoard {
    SinglePlayerService singlePlayerService;
    private int shipsToPlace;
    private GameSquare[][] gameSquares;

    public InitializerGameBoard() {
        shipsToPlace = 5;
        shipPlacements = new int[10][10];
        gameSquares = new GameSquare[10][10];
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j] = new GameSquare(this, GameSquare.WIDTH, GameSquare.HEIGHT, i, j);
                add(gameSquares[i][j], j, i);
            }
        }
    }

    @Override
    public void renderColors() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j].setFill(gameSquares[i][j].getColorFromType());
            }
        }
    }

    public int indexOfType(String s) {
        for (int i = 0; i < types.length; i++) {
            if (s.equals(types[i])) {
                return i;
            }
        }
        return -1;
    }

    public void finishInitialization() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameSquare current = gameSquares[i][j];
                shipPlacements[i][j] = indexOfType(current.getType()) + 1;
            }
        }
        singlePlayerService.startGame(shipPlacements);
    }

    public void tryToInitialize(int row, int col) {
        if(shipsToPlace > 0) {
            if (isValidInitializerChoice(row, col)) {
                for (int i = col; i < col + getCurrentShipSize(); i++) {
                    gameSquares[row][i].setType(types[shipsToPlace - 1]);
                }
                shipsToPlace--;
                singlePlayerService.updateChooseShipLBL();
                renderColors();
                if (shipsToPlace == 0) {
                    finishInitialization();
                }
            }
        }
    }

    public boolean isValidInitializerChoice(int row, int col) {
        if (shipsToPlace <= 0) {
            return false;
        }
        for (int i = col; i < col + getCurrentShipSize(); i++) {
            if (i >= 10) {
                return false;
            }
            GameSquare current = gameSquares[row][i];
            if (!current.getType().equals("empty")) {
                return false;
            }
        }
        return true;
    }

    public String getCurrentShipType() {
        if (shipsToPlace == 0) {
            return "";
        }
        return types[shipsToPlace - 1];
    }

    public int getCurrentShipSize() {
        if (shipsToPlace == 0) {
            return 0;
        }
        return sizes[shipsToPlace - 1];
    }

    @Override
    public GameSquare getGameSquare(int row, int col) {
        return gameSquares[row][col];
    }

    public void setSinglePlayerService(SinglePlayerService singlePlayerService) {
        this.singlePlayerService = singlePlayerService;
    }
}
