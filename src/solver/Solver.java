package solver;

import cache.BoardCache;
import model.Board;

public abstract class Solver {

    private BoardCache cache;

    public Solver(BoardCache cache) {
        this.cache = cache;
    }

    public BoardCache getCache() {
        return cache;
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

        System.out.println("Time: " + time);
        System.out.println("Memory: " + memory);
        System.out.println("Sequence: " + cache.getSequence(solved));
    }
}
