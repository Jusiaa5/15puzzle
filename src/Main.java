import model.Board;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        Board board = new Board(new PuzzleFileReader().readFile("puzzle.txt"));
        Board targetBoard = new TargetBoardBuilder().buildTargetBoard(board);
        System.out.println(Arrays.deepToString(board.getPuzzleBoard()));
        System.out.println(Arrays.deepToString(targetBoard.getPuzzleBoard()));
    }

}
