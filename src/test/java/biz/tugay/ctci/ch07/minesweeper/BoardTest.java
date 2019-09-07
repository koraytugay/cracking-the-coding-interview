package biz.tugay.ctci.ch07.minesweeper;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    Board board;

    @Before
    public void before() {
        board = Board.newBoard();
    }

    @Test
    public void simulateGame() {
        Board.BoardResponse boardResponse = board.flipCell(6, 0);
        // - - - - - - -
        // - - - - - - -
        // 1 1 2 - - - -
        // . . 1 - - - -
        // . . 1 1 2 - -
        // . . . . 1 - -
        // . . . . 1 - -
        assertThat(boardResponse, is(Board.BoardResponse.OK));
        assertThat(board.rows[0][0].revealed, is(false));
        assertThat(board.rows[1][0].revealed, is(false));
        assertThat(board.rows[2][0].revealed, is(true));
        assertThat(board.rows[3][0].revealed, is(true));
        assertThat(board.rows[4][0].revealed, is(true));
        assertThat(board.rows[5][0].revealed, is(true));
        assertThat(board.rows[6][0].revealed, is(true));
        assertThat(board.rows[6][1].revealed, is(true));
        assertThat(board.rows[6][2].revealed, is(true));
        assertThat(board.rows[6][3].revealed, is(true));
        assertThat(board.rows[6][4].revealed, is(true));
        assertThat(board.rows[6][5].revealed, is(false));
        assertThat(board.rows[6][6].revealed, is(false));

        boardResponse = board.flipCell(0, 0);
        // 1 - - - - - -
        // - - - - - - -
        // 1 1 2 - - - -
        // . . 1 - - - -
        // . . 1 1 2 - -
        // . . . . 1 - -
        // . . . . 1 - -
        assertThat(boardResponse, is(Board.BoardResponse.OK));
        assertThat(board.rows[0][0].revealed, is(true));

        boardResponse = board.flipCell(1, 1);
        // 1 - - - - - -
        // - * - - - - -
        // 1 1 2 - - - -
        // . . 1 - - - -
        // . . 1 1 2 - -
        // . . . . 1 - -
        // . . . . 1 - -

        assertThat(boardResponse, is(Board.BoardResponse.BOMB));
        assertThat(board.rows[1][1].revealed, is(true));
    }

}
