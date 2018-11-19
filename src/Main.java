import model.Board;
import solver.*;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        Board board = new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD);
        Board targetBoard = new TargetBoardBuilder().buildTargetBoard(board);
        System.out.println(Arrays.deepToString(board.getPuzzleBoard()));
        System.out.println(Arrays.deepToString(targetBoard.getPuzzleBoard()));

        System.out.println("\nDFS:\n");
        new DFS(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard).run();

        System.out.println("\nBFS:\n");
        new BFS(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard).run();

        System.out.println("\nIDFS:\n");
        new IDFS(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard).run();

        System.out.println("\nBF Manhattan:\n");
        new BF(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 1).run(); // manhattan

        System.out.println("\nBF Wrong placed:\n");
        new BF(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 2).run(); // wrong placed

        System.out.println("\nA* Manhattan:\n");
        new AStar(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 1).run(); // manhattan

        System.out.println("\nA* Wrong placed:\n");
        new AStar(new Board(new PuzzleFileReader().readFile("puzzle.txt"), Board.INITIAL_BOARD), targetBoard, 2).run(); // wrong placed
    }
}
