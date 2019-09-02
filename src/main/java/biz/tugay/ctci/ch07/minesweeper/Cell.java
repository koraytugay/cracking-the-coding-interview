package biz.tugay.ctci.ch07.minesweeper;

public class Cell {

    static final int BOMB = -1;

    boolean revealed = false;
    int row, col, val;

    public Cell(int... coordinates) {
        this.row = coordinates[0];
        this.col = coordinates[1];
    }

    boolean isBomb() {
        return val == BOMB;
    }

    boolean isEmpty() {
        return val == 0;
    }

}

