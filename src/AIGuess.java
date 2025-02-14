public class AIGuess {
    static int[][] heatMap = new int[10][10];

    public static int[] makeGuess(char[][] guesses)
    {
        generateHeatmap(guesses);
        return getNextGuess(heatMap, guesses);
    }

    public static boolean isShipAlive(char[][] guesses, int n) {
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].length; j++) {
                if (guesses[i][j] == n + '0') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void generateHeatmap(char[][] guesses) {
        for (int i = 0; i < heatMap.length; i++) {
            for (int j = 0; j < heatMap[i].length; j++) {
                heatMap[i][j] = 0;
            }
        }
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses.length; j++) {
                //if (isShipAlive(guesses, 1)) {
                updateNodeScoreRow(guesses, i, j, 2);
                updateNodeScoreCol(guesses, i, j, 2);
                //}
                if (isShipAlive(guesses, 2)) {
                    updateNodeScoreRow(guesses, i, j, 3);
                    updateNodeScoreCol(guesses, i, j, 3);
                }
                if (isShipAlive(guesses, 3)) {
                    updateNodeScoreRow(guesses, i, j, 3);
                    updateNodeScoreCol(guesses, i, j, 3);
                }
                if (isShipAlive(guesses, 4)) {
                    updateNodeScoreRow(guesses, i, j, 4);
                    updateNodeScoreCol(guesses, i, j, 4);
                }
                if (isShipAlive(guesses, 5)) {
                    updateNodeScoreRow(guesses, i, j, 5);
                    updateNodeScoreCol(guesses, i, j, 5);
                }
            }
        }
        updateXNodes(guesses);
    }

    public static int[] getNextGuess(int[][] heatMap, char[][] guesses) {
        int[] coords = new int[2];
        //Find max value on heatMap
        int max = 0;
        for (int i = 0; i < heatMap.length; i++) {
            for (int j = 0; j < heatMap[i].length; j++) {
                if (heatMap[i][j] > max && guesses[i][j] == '.') {
                    max = heatMap[i][j];
                    coords[0] = i;
                    coords[1] = j;
                }
            }
        }
        int maxCount = 0;
        int[][] tiedCoords = new int[50][2];
        if (max == 0) {
            for (int i = 0; i < heatMap.length; i++) {
                for (int j = 0; j < heatMap[i].length; j++) {
                    if (guesses[i][j] == '.') {
                        coords[0] = i;
                        coords[1] = j;
                        break;
                    }
                }
            }
        } else {
            //Check for ties
            for (int i = 0; i < heatMap.length; i++) {
                for (int j = 0; j < heatMap[i].length; j++) {
                    if (heatMap[i][j] == max) {
                        int[] tie = {i, j};
                        tiedCoords[maxCount] = tie;
                        maxCount++;
                    }
                }
            }
        }
        //if (maxCount < 2) {
        return coords;
        /*} else {
            return findBestCoords(tiedCoords, guesses);
        }*/
    }

    public static boolean isValid(int i) {
        return i >= 0 && i < 10;
    }

    public static boolean isLoneX(char[][] guesses, int row, int col) {
        if (isValid(row + 1) && guesses[row + 1][col] == 'X') {
            return false;
        }
        if (isValid(row - 1) && guesses[row - 1][col] == 'X') {
            return false;
        }
        if (isValid(col + 1) && guesses[row][col + 1] == 'X') {
            return false;
        }
        if (isValid(col - 1) && guesses[row][col - 1] == 'X') {
            return false;
        }
        return true;
    }

    public static int getLargestShipAlive(char[][] guesses) {
        if (isShipAlive(guesses, 5)) {
            return 5;
        } else if (isShipAlive(guesses, 4)) {
            return 4;
        } else if (isShipAlive(guesses, 3)) {
            return 3;
        } else if (isShipAlive(guesses, 2)) {
            return 3;
        } else {
            return 2;
        }
    }

    public static int getMaximumHorizontalDistance(char[][] guesses, int row, int col, int dir) {
        int shipLength = getLargestShipAlive(guesses);
        if (dir == 1) {
            for (int i = 1; i < shipLength; i++) {
                if (!isValid(col + i) || guesses[row][col + i] != '.') {
                    return i - 1;
                }
            }
        } else if (dir == -1) {
            for (int i = 1; i < shipLength; i++) {
                if (!isValid(col - i) || guesses[row][col - i] != '.') {
                    return i - 1;
                }
            }
        }
        return shipLength - 1;
    }

    public static int getMaximumVerticalDistance(char[][] guesses, int row, int col, int dir) {
        int shipLength = getLargestShipAlive(guesses);
        if (dir == 1) {
            for (int i = 1; i < shipLength; i++) {
                if (!isValid(row + i) || guesses[row + i][col] != '.') {
                    return i - 1;
                }
            }
        } else if (dir == -1) {
            for (int i = 1; i < shipLength; i++) {
                if (!isValid(row - i) || guesses[row - i][col] != '.') {
                    return i - 1;
                }
            }
        }
        return shipLength - 1;
    }
    public static boolean isShipFittable(char[][] guesses, int row, int col, int dir, int size) {
        //1 is right, 2 is left, 3 is up, 4 is down
        if (dir == 1) {
            for (int i = col; i < col + size; i++) {
                if (!isValid(i) || guesses[row][i] != '.') {
                    return false;
                }
            }
        } else if (dir == 2) {
            for (int i = col; i > col - size; i--) {
                if (!isValid(i) || guesses[row][i] != '.') {
                    return false;
                }
            }
        } else if (dir == 3) {
            for (int i = row; i > row - size; i--) {
                if (!isValid(i) || guesses[i][col] != '.') {
                    return false;
                }
            }
        } else if (dir == 4) {
            for (int i = row; i < row + size; i++) {
                if (!isValid(i) || guesses[i][col] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getShipWeighting(char[][] guesses, int row, int col, int dir) {
        int weight = 0;
        if (isShipFittable(guesses, row, col, dir, 2) && isShipAlive(guesses, 1)) weight += 50;
        if (isShipFittable(guesses, row, col, dir, 3) && isShipAlive(guesses, 2)) weight -= 20;
        if (isShipFittable(guesses, row, col, dir, 4) && isShipAlive(guesses, 4)) weight -= 30;
        if (isShipFittable(guesses, row, col, dir, 5) && isShipAlive(guesses, 5)) weight -= 40;
        return weight;
    }

    public static void updateXNodes(char[][] guesses) {
        for (int row = 0; row < guesses.length; row++) {
            for (int col = 0; col < guesses.length; col++) {
                if (guesses[row][col] == 'X') {
                    if (isLoneX(guesses, row, col)) {
                        if (isValid(col + 1)) {
                            heatMap[row][col + 1] = 50 + getMaximumHorizontalDistance(guesses, row, col + 1, 1);
                        }
                        if (isValid(col - 1)) {
                            heatMap[row][col - 1] = 50 + getMaximumHorizontalDistance(guesses, row, col - 1, -1);
                        }
                    } else {
                        if (isValid(col + 2) && guesses[row][col + 1] == 'X') {
                            heatMap[row][col + 2] += 150;
                        }
                        if (isValid(col - 2) && guesses[row][col - 1] == 'X') {
                            heatMap[row][col - 2] += 150;
                        }
                    }
                }
                if (guesses[row][col] == 'X') {
                    if (isLoneX(guesses, row, col)) {
                        if (isValid(row + 1)) {
                            heatMap[row + 1][col] = 50 + getMaximumVerticalDistance(guesses, row + 1, col, 1);
                        }
                        if (isValid(row - 1)) {
                            heatMap[row - 1][col] = 50 + getMaximumVerticalDistance(guesses, row - 1, col, -1);
                        }
                    } else {
                        if (isValid(row + 2) && guesses[row + 1][col] == 'X') {
                            heatMap[row + 2][col] += 150;
                        }
                        if (isValid(row - 2) && guesses[row - 1][col] == 'X') {
                            heatMap[row - 2][col] += 150;
                        }
                    }
                }
            }
        }
    }

    public static void updateNodeScoreCol(char[][] guesses, int row, int col, int shipSize) {
        if (guesses[row][col] != '.') {
            heatMap[row][col] = 0;
        }
        else if (row + shipSize > 10) {
            return;
        } else {
            for (int i = row; i < row + shipSize; i++) {
                if (guesses[i][col] != '.') {
                    return;
                }
            }
            for (int i = row; i < row + shipSize; i++) {
                heatMap[i][col]++;
            }
        }
    }
    public static void updateNodeScoreRow(char[][] guesses, int row, int col, int shipSize) {
        if (guesses[row][col] != '.') {
            heatMap[row][col] = 0;
        }
        if (col + shipSize > 10) {
            return;
        } else {
            for (int i = col; i < col + shipSize; i++) {
                if (guesses[row][i] != '.') {
                    return;
                }
            }
            for (int i = col; i < col + shipSize; i++) {
                heatMap[row][i]++;
            }
        }
    }
}