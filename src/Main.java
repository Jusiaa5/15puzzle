import model.Board;
import solver.BF;
import solver.BFS;
import solver.DFS;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        Board board = new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD);
        Board targetBoard = new TargetBoardBuilder().buildTargetBoard(board);
        System.out.println(Arrays.deepToString(board.getPuzzleBoard()));
        System.out.println(Arrays.deepToString(targetBoard.getPuzzleBoard()));
//        new DFS(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard).run();
//        new BFS(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard).run();
//        new BF(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 0).run(); // 0
        new BF(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 1).run(); // manhattan
        new BF(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 2).run(); // wrong placed
    }

}
