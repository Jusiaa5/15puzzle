import model.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardCache {

    private final static ArrayList<Board> cache;

    static {
        cache = new ArrayList<>();
    }

    static boolean addBoardIfNotCached(Board board) {
        if (isAlreadyCached(board)) {
            return false;
        } else {
            cache.add(board);
            return true;
        }
    }

    private static boolean isAlreadyCached(Board board) {
        return cache.stream().anyMatch(b -> Arrays.deepEquals(b.getPuzzleBoard(), board.getPuzzleBoard()));
    }

    static void clearCache() {
        cache.clear();
    }
}
