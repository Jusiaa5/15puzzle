import model.Board;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        Board board = new Board(new PuzzleFileReader().readLines("puzzle.txt"));
        System.out.println(Arrays.deepToString(board.getPuzzleBoard()));
    }

}
