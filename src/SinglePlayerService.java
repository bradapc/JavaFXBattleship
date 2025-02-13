import javafx.scene.paint.Color;

public class SinglePlayerService {
    private int[][] shipPlacements;
    private SinglePlayerController singlePlayerController;
    private String turn;
    private SinglePlayerGameboard enemyGameBoard;
    private SinglePlayerGameboard userGameBoard;

    public SinglePlayerService(int[][] shipPlacements, SinglePlayerController singlePlayerController) {
        this.shipPlacements = shipPlacements;
        this.singlePlayerController = singlePlayerController;
        turn = "USER";
    }

    public void swapTurn() {
        if (turn.equals("USER")) {
            turn = "ENEMY";
        } else {
            turn = "USER";
        }
    }

    public void playerHitRequest(GameSquare gameSquare) {
        if (!isValidPlayerHit(gameSquare)) return;
        if (gameSquare.getParentGameboard().getBoardType().equals("ENEMY")) {
            swapTurn();
            if (gameSquare.getType().equals("empty")) {
                gameSquare.setFill(Color.BLUE);
            } else {
                gameSquare.setFill(Color.RED);
            }
            gameSquare.setHit(true);
        }
        enemyHitRequest();
    }

    public char[][] populateGuessesBoard() {
        char[][] guesses = new char[10][10];
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].length; j++) {
                GameSquare current = userGameBoard.getGameSquare(i, j);
                if (!current.isHit()) {
                    guesses[i][j] = '.';
                } else {
                    if (current.getType().equals("empty")) {
                        guesses[i][j] = '0';
                    }
                    //else if not empty, get type and check if that type is dead
                    //If not dead, render as X. If dead, render as appropriate number.
                }
            }
        }
        return guesses;
    }

    public void enemyHitRequest() {
        char[][] guesses = populateGuessesBoard();
        //int[] guess = AIGuess.makeGuess();
    }

    public boolean isValidPlayerHit(GameSquare gameSquare) {
        if (turn.equals("USER")) {
            if (gameSquare.getParentGameboard().equals(enemyGameBoard) && !gameSquare.isHit()) {
                return true;
            }
        } else if (turn.equals("ENEMY")) {
            if (gameSquare.getParentGameboard().equals(userGameBoard) && !gameSquare.isHit()) {
                return true;
            }
        }
        return false;
    }

    public void setEnemyGameBoard(SinglePlayerGameboard enemyGameBoard) {
        this.enemyGameBoard = enemyGameBoard;
        enemyGameBoard.setSinglePlayerService(this);
    }

    public void setUserGameBoard(SinglePlayerGameboard userGameBoard) {
        this.userGameBoard = userGameBoard;
        userGameBoard.setSinglePlayerService(this);
    }

}
