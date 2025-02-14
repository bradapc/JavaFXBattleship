import javafx.geometry.Pos;

import java.util.ArrayList;

public class SinglePlayerGameboard extends GameBoard {
    private GameSquare[][] gameSquares;
    private int[][] shipPlacements;
    private final String boardType;
    private ArrayList<GameSquare> shipSquares;
    private SinglePlayerService singlePlayerService;

    public SinglePlayerGameboard(int[][] shipPlacements, String boardType) {
        this.shipPlacements = shipPlacements;
        this.boardType = boardType;
        this.shipSquares = new ArrayList<>();
        gameSquares = new GameSquare[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gameSquares[i][j] = new GameSquare(this, InitializerGameSquare.WIDTH, InitializerGameSquare.HEIGHT, i, j);
                if (shipPlacements[i][j] > 0) {
                    gameSquares[i][j].setType(GameBoard.getTypeFromBoardNumber(shipPlacements[i][j]));
                    shipSquares.add(gameSquares[i][j]);
                }
                add(gameSquares[i][j], j, i);
            }
        }
        renderColors();
    }

    public void setSinglePlayerService(SinglePlayerService singlePlayerService) {
        this.singlePlayerService = singlePlayerService;
    }

    public SinglePlayerService getSinglePlayerService() {
        return singlePlayerService;
    }

    public boolean isBoardDead() {
        for (GameSquare shipSquare : shipSquares) {
            if (!shipSquare.isHit()) {
                return false;
            }
        }
        return true;
    }

    public GameSquare getGameSquare(int row, int col) {
        return gameSquares[row][col];
    }

    public String getBoardType() {
        return boardType;
    }

    @Override
    public void renderColors() {
        if (boardType.equals("USER")) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    GameSquare current = gameSquares[i][j];
                    current.setFill(gameSquares[i][j].getColorFromType());
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    GameSquare current = gameSquares[i][j];
                    if (!current.isHit()) return;
                    current.setFill(gameSquares[i][j].getColorFromType());
                }
            }
        }
    }
}
