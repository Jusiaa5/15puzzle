package solver;

import cache.HashSetCache;
import model.Board;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BFS {

    private Board board;
    private Board targetBoard;
    private HashSetCache cache;
    // FIFO queue
    private Queue<Board> queue;

    public BFS(Board board, Board targetBoard) {
        this.board = board;
        this.targetBoard = targetBoard;
        this.cache = new HashSetCache(board);
        queue = new ArrayDeque<>();
    }

    // returns last board
    private Board solve() {
        queue.add(board);
        Board currentBoard;

        while (!queue.isEmpty()) {
            currentBoard = queue.poll();

            if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), targetBoard.getPuzzleBoard())) {
                return currentBoard;
            }

            if (currentBoard.moveDown(cache)) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.DOWN));
            }
            if (currentBoard.moveRight(cache)) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.RIGHT));
            }
            if (currentBoard.moveUp(cache)) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.UP));
            }
            if (currentBoard.moveLeft(cache)) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.LEFT));
            }
        }
        return null;
    }

    public void run() {

        long startTime = System.currentTimeMillis();
        Board solved = solve();
        long time = System.currentTimeMillis() - startTime;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Time: " + time);
        System.out.println("Memory: " + memory);
        System.out.println("Sequence: " + cache.getSequence(solved));
    }
}
