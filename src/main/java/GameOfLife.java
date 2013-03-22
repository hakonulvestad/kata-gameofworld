public class GameOfLife {


    public boolean[][] calculateNextState(boolean[][] input) {
        boolean[][] result = new boolean[input.length][input[0].length];

        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input[x].length; y++) {
                int neighbours = calculateLiveNeighbours(input, x, y);
                boolean cellAlive = input[x][y];
                if (neighbours < 2 || neighbours > 3) {
                    cellAlive=false;
                } else if (neighbours == 3) {
                    cellAlive = true;
                }
                result[x][y] = cellAlive;
            }
        }

        return result;
    }

    public int calculateLiveNeighbours(boolean[][] input, int x, int y) {
        int result = 0;

        result += calculateNeighbourRow(input, x, y-1);
        result += calculateNeighbourRow(input, x, y+1);

        if(isLeftNeighbourAlive(input, x, y)) {
            result++;
        }

        if(isRightNeighbourAlive(input, x, y)) {
            result++;
        }

        return result;
    }

    private boolean isLeftNeighbourAlive(boolean[][] input, int x, int y) {
        return isCellAlive(input, x-1, y);
    }

    private boolean isRightNeighbourAlive(boolean[][] input, int x, int y) {
        return isCellAlive(input, x+1, y);
    }

    private int calculateNeighbourRow(boolean[][] input, int x, int y) {
        int result = 0;
        for(int i = x-1; i <= x+1 ; i++) {
            if(isCellAlive(input, i, y)) {
                result++;
            }
        }

        return result;
    }

    private boolean isCellAlive(boolean[][] input, int x, int y) {
        return isWithinBounds(input, x, y) && input[x][y];
    }

    private boolean isWithinBounds(boolean[][] input, int x, int y) {
        return x >= 0 && x < input.length && y >= 0 && y < input[0].length;
    }
}
