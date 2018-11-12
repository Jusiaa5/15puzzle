package solver;

import cache.BoardCache;
import model.Board;

public abstract class Solver {

    private Board board;
    private Board targetBoard;
    private BoardCache cache;

    Solver(Board board, Board targetBoard, BoardCache cache) {
        this.board = board;
        this.targetBoard = targetBoard;
        this.cache = cache;
    }

    // Override this method in specific implementations
    // returns last board (first one that will be taken into consideration when reading the moves sequence)
    abstract Board solve();

    public void run() {

        long startTime = System.currentTimeMillis();
        Board solved = solve();
        long time = System.currentTimeMillis() - startTime;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Time: " + time + " milliseconds");
        System.out.println("Memory: " + memory + " bytes");
        System.out.println("Sequence: " + cache.getSequence(solved));
    }

    public Board getBoard() {
        return board;
    }

    public Board getTargetBoard() {
        return targetBoard;
    }

    public BoardCache getCache() {
        return cache;
    }
}
