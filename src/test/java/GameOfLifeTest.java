import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameOfLifeTest {

    private GameOfLife gameOfLife = new GameOfLife();

    private boolean[][] input;

    @Before
    public void setup() {
        input = new boolean[3][3];
    }

    @Test
    public void shouldReturnWorldOfSameSize() {
        boolean[][] result = gameOfLife.calculateNextState(input);

        assertEquals(result.length, 3);
        assertEquals(result[0].length, 3);
    }

    @Test
    public void numberOfNeighboursNone() {
        int x = 1;
        int y = 1;
        input[x][y] = true;

        int liveNeighbours = gameOfLife.calculateLiveNeighbours(input, x, y);

        assertEquals(liveNeighbours, 0);
    }

    @Test
    public void numberOfNeighboursSome() {
        int x = 1;
        int y = 1;
        input[0][0] = true;
        input[2][0] = true;
        input[2][1] = true;
        input[0][2] = true;

        int liveNeighbours = gameOfLife.calculateLiveNeighbours(input, x, y);

        assertEquals(liveNeighbours, 4);
    }

    @Test
    public void numberOfNeighboursRightSideEdge() {
        input[2][0] = true;
        input[2][2] = true;

        int liveNeighbours = gameOfLife.calculateLiveNeighbours(input, 2, 1);

        assertEquals(liveNeighbours, 2);

    }

    @Test
    public void topLeftCorner() {
        input[1][1] = true;
        int i = gameOfLife.calculateLiveNeighbours(input, 0, 0);
        assertEquals(i, 1);
    }

    @Test
    public void lessThanTwoNeighboursShouldDie() {

        input[1][1] = true;

        boolean[][] result = gameOfLife.calculateNextState(input);

        assertFalse(result[1][1]);
    }

    @Test
    public void moreThanThreeNeighboursShouldDie() {
        input[1][1] = true;

        input[0][0] = true;
        input[0][1] = true;
        input[0][2] = true;
        input[1][0] = true;

        boolean[][] result = gameOfLife.calculateNextState(input);

        assertFalse(result[1][1]);

    }

    @Test
    public void liveCellWithThreeNeighboursShouldLiveOn() {
        input[1][1] = true;

        input[0][0] = true;
        input[0][1] = true;
        input[0][2] = true;

        boolean[][] result = gameOfLife.calculateNextState(input);

        assertTrue(result[1][1]);

    }

    @Test
    public void deadCellWithTwoNeighboursShouldNotLive() {
        input[0][0] = true;
        input[0][1] = true;

        boolean[][] result = gameOfLife.calculateNextState(input);

        assertFalse(result[1][1]);

    }

    @Test
    public void cellWithThreeNeighboursShouldCome() {
        input[0][0] = true;
        input[0][1] = true;
        input[0][2] = true;

        boolean[][] result = gameOfLife.calculateNextState(input);

        assertTrue(result[1][1]);

    }
}
