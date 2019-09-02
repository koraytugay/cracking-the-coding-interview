package biz.tugay.ctci.ch07.minesweeper;

import java.util.Scanner;

import static java.lang.String.valueOf;

public class MinesweeperCli {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = Board.newBoard();

        Board.BoardResponse boardResponse;
        do {
            int row = in.nextInt();
            int col = in.nextInt();
            boardResponse = board.flipCell(row, col);
            printBoard(board);
            if (boardResponse == Board.BoardResponse.BOMB) {
                System.out.println("Bomb!");
                break;
            }
        } while (boardResponse != Board.BoardResponse.ALL_CELLS_REVEALED);

        in.close();
    }

    static void printBoard(Board board) {
        StringBuilder sb = new StringBuilder();

        for (Cell[] row : board.rows) {
            for (Cell cell : row)
                sb.append(cellRep(cell)).append(" ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static String cellRep(Cell cell) {
        if (!cell.revealed)
            return "-";

        if (cell.isBomb())
            return "*";

        if (cell.isEmpty())
            return ".";

        return valueOf(cell.val);
    }

}
