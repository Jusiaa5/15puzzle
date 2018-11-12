import model.Board;
import solver.BFS;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        Board board = new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD);
        Board targetBoard = new TargetBoardBuilder().buildTargetBoard(board);
        System.out.println(Arrays.deepToString(board.getPuzzleBoard()));
        System.out.println(Arrays.deepToString(targetBoard.getPuzzleBoard()));
        new BFS(board, targetBoard).run();
    }

}
