import javafx.geometry.Pos;

public class InitializerGameBoard extends GameBoard {
    InitializerService initializerService;
    private int shipsToPlace;
    private InitializerGameSquare[][] initializerGameSquares;
    private String orientation = "HORIZONTAL";

    public InitializerGameBoard() {
        shipsToPlace = 5;
        shipPlacements = new int[10][10];
        initializerGameSquares = new InitializerGameSquare[10][10];
        setAlignment(Pos.CENTER);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                initializerGameSquares[i][j] = new InitializerGameSquare(this, InitializerGameSquare.WIDTH, InitializerGameSquare.HEIGHT, i, j);
                add(initializerGameSquares[i][j], j, i);
            }
        }
    }

    @Override
    public void renderColors() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                initializerGameSquares[i][j].setFill(initializerGameSquares[i][j].getColorFromType());
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
                InitializerGameSquare current = initializerGameSquares[i][j];
                shipPlacements[i][j] = indexOfType(current.getType()) + 1;
            }
        }
        initializerService.startGame(shipPlacements);
    }

    public void tryToInitialize(int row, int col) {
        if (!isValidInitializerChoice(row, col)) {
            return;
        }
        int pos = orientation.equals("HORIZONTAL") ? col : row;
        for (int i = pos; i < pos + GameBoard.getCurrentShipSize(shipsToPlace); i++) {
            if (orientation.equals("HORIZONTAL")) {
                initializerGameSquares[row][i].setType(getCurrentShipType());
            } else {
                initializerGameSquares[i][col].setType(getCurrentShipType());
            }
        }
        shipsToPlace--;
        initializerService.updateChooseShipLBL();
        renderColors();
        if (shipsToPlace == 0) {
            finishInitialization();
        }
    }

    public boolean isValidInitializerChoice(int row, int col) {
        int pos = 0;
        if (orientation.equals("HORIZONTAL")) {
            pos = col;
        } else {
            pos = row;
        }
        for (int i = pos; i < pos + GameBoard.getCurrentShipSize(shipsToPlace); i++) {
            if (i >= 10) {
                return false;
            }
            InitializerGameSquare current = orientation.equals("HORIZONTAL") ? getGameSquare(row, i) : getGameSquare(i, col);
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

    public InitializerGameSquare getGameSquare(int row, int col) {
        return initializerGameSquares[row][col];
    }

    public String getOrientation() {
        return orientation;
    }

    public void flipOrientation() {
        if (orientation.equals("HORIZONTAL")) {
            orientation = "VERTICAL";
        } else {
            orientation = "HORIZONTAL";
        }
    }

    public void setSinglePlayerService(InitializerService initializerService) {
        this.initializerService = initializerService;
    }

    public int getShipsToPlace() {
        return shipsToPlace;
    }
}
