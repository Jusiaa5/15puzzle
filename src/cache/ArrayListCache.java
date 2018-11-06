package cache;

import model.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListCache implements BoardCache {

    private final ArrayList<Board> cache;

    public ArrayListCache() {
        this.cache = new ArrayList<>();
    }

    public boolean addBoardIfNotCached(Board board) {
        if (isAlreadyCached(board)) {
            return false;
        } else {
            cache.add(board);
            return true;
        }
    }

    public boolean isAlreadyCached(Board board) {
        return cache.stream().anyMatch(b -> Arrays.deepEquals(b.getPuzzleBoard(), board.getPuzzleBoard()));
    }

    public void clearCache() {
        cache.clear();
    }
}
