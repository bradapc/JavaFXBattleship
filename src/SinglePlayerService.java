import javafx.animation.PauseTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

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
            gameSquare.setHit(true);
            if (gameSquare.getType().equals("empty")) {
                singlePlayerController.getEnemyChatBox().addChatMessage(new ChatMessage("You shoot the enemy at " +
                        gameSquare.getCoordString() + " and miss."));
                gameSquare.setFill(Color.BLUE);
            } else {
                if (isDead(gameSquare.getType(), enemyGameBoard)) {
                    singlePlayerController.getEnemyChatBox().addChatMessage(new ChatMessage("You destroyed the" +
                            " enemy " + gameSquare.getType() + "!"));
                    updateColorToDead(gameSquare.getType(), enemyGameBoard);
                } else {
                    singlePlayerController.getEnemyChatBox().addChatMessage(new ChatMessage("You shoot the enemy at " +
                            gameSquare.getCoordString() + ". Hit!"));
                    gameSquare.setFill(Color.RED);
                }
            }
        }
        singlePlayerController.doWait();
    }

    private char[][] populateGuessesBoard() {
        char[][] guesses = new char[10][10];
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].length; j++) {
                GameSquare current = userGameBoard.getGameSquare(i, j);
                if (!current.isHit()) {
                    guesses[i][j] = '.';
                } else {
                    String currentType = current.getType();
                    if (currentType.equals("empty")) {
                        guesses[i][j] = '0';
                    } else {
                        boolean isCurrentTypeDead = isDead(currentType, userGameBoard);
                        if (isCurrentTypeDead) {
                            guesses[i][j] = (char) ('0' + GameBoard.getNumericalFromType(currentType));
                        } else {
                            guesses[i][j] = 'X';
                        }
                    }
                }
            }
        }
        return guesses;
    }

    public boolean isDead(String currentType, SinglePlayerGameboard board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameSquare current = board.getGameSquare(i, j);
                if (current.getType().equals(currentType) && !current.isHit()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void enemyHitRequest() {
        char[][] guesses = populateGuessesBoard();
        int[] guess = AIGuess.makeGuess(guesses);
        GameSquare current = userGameBoard.getGameSquare(guess[0], guess[1]);
        current.setHit(true);
        if (current.getType().equals("empty")) {
            singlePlayerController.getPlayerChatBox().addChatMessage(new ChatMessage("The enemy shoots you at " +
                    current.getCoordString() + " and misses."));
            current.setFill(Color.BLUE);
        } else {
            if (isDead(current.getType(), userGameBoard)) {
                singlePlayerController.getPlayerChatBox().addChatMessage(new ChatMessage("The enemy has destroyed your "
                         + current.getType() + "!"));
                updateColorToDead(current.getType(), userGameBoard);
            } else {
                singlePlayerController.getPlayerChatBox().addChatMessage(new ChatMessage("The enemy shoots you at " +
                        current.getCoordString() + " and hits you!"));
                current.setFill(Color.RED);
            }
        }
        swapTurn();
    }

    public void updateColorToDead(String type, SinglePlayerGameboard board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameSquare current = board.getGameSquare(i, j);
                if (current.getType().equals(type)) {
                    current.setFill(Color.BROWN);
                }
            }
        }
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
