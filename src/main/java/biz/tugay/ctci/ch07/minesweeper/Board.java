package biz.tugay.ctci.ch07.minesweeper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Board {

    enum BoardResponse {
        OK, BOMB, ALL_CELLS_REVEALED
    }

    Cell[][] rows;

    // Returns a new Board with a few bombs and the values around the bombs.
    static Board newBoard() {
        Board board = new Board();
        board.rows = new Cell[7][7];  // todo: Accept parameters instead of 7,7

        for (int i = 0; i < board.rows.length; i++)
            for (int j = 0; j < board.rows[i].length; j++)
                board.rows[i][j] = new Cell(i, j);

        board.rows[1][1].val = Cell.BOMB;  // todo: randomise bomb placement
        board.rows[3][3].val = Cell.BOMB;  // todo: make sure bombs are not adjacent
        board.rows[5][5].val = Cell.BOMB;

        // Fill bombs surroundings with values.
        for (Cell[] row : board.rows)
            for (Cell cell : row)
                if (cell.isBomb())
                    board.surroundingCells(cell).forEach(c -> c.val = c.val + 1);

        return board;
    }

    // Accepts a row and a column, modifies the state as needed and returns whether
    // game is finished, a bomb has been clicked or game continues.
    BoardResponse flipCell(int... coordinates) {
        Cell clicked = rows[coordinates[0]][coordinates[1]];
        clicked.revealed = true;

        // Player lost
        if (clicked.isBomb())
            return BoardResponse.BOMB;

        // Check if game finished, i.e. player won..
        // For the game to finish all cells
        // (except the ones holding bombs) must be revealed.
        boolean allCellsRevealed = true;
        isAllCellsRevealed:
        for (Cell[] row : rows)
            for (Cell cell : row)
                if (!cell.revealed && !cell.isBomb()) {
                    allCellsRevealed = false;
                    break isAllCellsRevealed;
                }

        if (allCellsRevealed)
            return BoardResponse.ALL_CELLS_REVEALED;

        // If the cell clicked on is an empty cell,
        // reveal all surrounding cells recursively until valued cells.
        if (clicked.isEmpty()) {
            Queue<Cell> queue = new LinkedList<>();
            surroundingCells(clicked)
                    .stream().filter(c -> !c.revealed).forEach(queue::add);
            while (!queue.isEmpty()) {
                Cell cell = queue.remove();
                cell.revealed = true;
                if (cell.isEmpty()) {
                    Set<Cell> cells = surroundingCells(cell);
                    cells.stream().filter(c -> !c.revealed).forEach(queue::add);
                }
            }
        }

        return BoardResponse.OK;
    }

    // Given a single cell in the board, returns all surrounding cells in a Set.
    private Set<Cell> surroundingCells(Cell cell) {
        Set<Cell> surroundingCells = new HashSet<>();

        // Cells in upper row
        for (int i = cell.col - 1; i < cell.col + 2; i++)
            if (inBounds(cell.row - 1, i))
                surroundingCells.add(rows[cell.row - 1][i]);

        // Cells in lower row
        for (int i = cell.col - 1; i < cell.col + 2; i++)
            if (inBounds(cell.row + 1, i))
                surroundingCells.add(rows[cell.row + 1][i]);

        // Cell to left
        if (inBounds(cell.row, cell.col - 1))
            surroundingCells.add(rows[cell.row][cell.col - 1]);

        // Cell to right
        if (inBounds(cell.row, cell.col + 1))
            surroundingCells.add(rows[cell.row][cell.col + 1]);

        return surroundingCells;
    }

    // Helper method to surroundingCells.
    // Tries to access the cell in given coordinates handling OutOfBoundException.
    private boolean inBounds(int... coordinates) {
        try {
            Cell cell = rows[coordinates[0]][coordinates[1]];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
