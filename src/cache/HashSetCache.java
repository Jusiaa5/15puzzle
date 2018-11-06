package cache;

import model.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class HashSetCache implements BoardCache {

    private final HashSet<Board> cache;

    public HashSetCache() {
        this.cache = new HashSet<>();
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
