package biz.tugay.ctci.ch07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class MinesweeperGame {
    public static void main(String[] args) {
        new Board().play();
    }
}

class Board {

    static class Cell {
        int val, row, col;
        boolean isBomb, isRevealed;

        public String toString() {
            return !isRevealed ? "-" : val == 0 ? "." : "" + val;
        }
    }

    Cell[][] cells = new Cell[7][7];

    public Board() {
        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = new Cell();
                cell.row = i;
                cell.col = j;
                cells[i][j] = cell;

            }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        cells[random.nextInt(0, 6)][random.nextInt(0, 6)].isBomb = true;
        cells[random.nextInt(0, 6)][random.nextInt(0, 6)].isBomb = true;
        cells[random.nextInt(0, 6)][random.nextInt(0, 6)].isBomb = true;

        for (int i = 0; i < cells.length; i++)
            for (int j = 0; j < cells[i].length; j++)
                if (cells[i][j].isBomb)
                    surroundNumbers(cells, i, j);
    }

    void play() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println(this);

            System.out.println("Row: ");
            int row = in.nextInt();
            if (row == -1)
                break;

            System.out.println("Col: ");
            int col = in.nextInt();
            if (col == -1)
                break;

            Cell cell = cells[row][col];
            if (cell.isBomb) {
                System.out.println("Boom!");
                break;
            }

            if (cell.val == 0)
                revealSurrounding(cell);

            if (cell.val != 0)
                cell.isRevealed = true;

            if (gameFinished()) {
                System.out.println("You won!");
                break;
            }
        }
        in.close();
    }

    void revealSurrounding(Cell cell) {
        if (cell.isRevealed)
            return;

        Queue<Cell> queue = new LinkedList<>();
        queue.add(cell);

        while (!queue.isEmpty()) {
            cell = queue.peek();
            if (cell.val != 0) {
                cell.isRevealed = true;
                queue.remove();
                continue;
            }

            for (int i = cell.col - 1; i < cell.col + 2; i++)
                if (shouldReveal(cells, cell.row - 1, i))
                    queue.add(cells[cell.row - 1][i]);

            for (int i = cell.col - 1; i < cell.col + 2; i++)
                if (shouldReveal(cells, cell.row + 1, i))
                    queue.add(cells[cell.row + 1][i]);

            if (shouldReveal(cells, cell.row, cell.col - 1))
                queue.add(cells[cell.row][cell.col - 1]);

            if (shouldReveal(cells, cell.row, cell.col + 1))
                queue.add(cells[cell.row][cell.col + 1]);

            queue.remove().isRevealed = true;
        }
    }

    private boolean shouldReveal(Cell[][] cells, int row, int col) {
        try {
            return !cells[row][col].isRevealed;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return false;
        }
    }

    private void surroundNumbers(Cell[][] cells, int row, int col) {
        for (int i = col - 1; i < col + 2; i++)
            incrementVal(cells, row - 1, i);

        for (int i = col - 1; i < col + 2; i++)
            incrementVal(cells, row + 1, i);

        incrementVal(cells, row, col - 1);
        incrementVal(cells, row, col + 1);
    }

    private void incrementVal(Cell[][] cells, int row, int col) {
        try {
            cells[row][col].val = cells[row][col].val + 1;
        } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Cell[] cell : cells) {
            for (Cell innerCell : cell)
                sb.append(innerCell).append(" ");
            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean gameFinished() {
        for (Cell[] row : cells)
            for (Cell cell : row)
                if (!cell.isBomb && !cell.isRevealed)
                    return false;

        return true;
    }
}
