package cache;

import model.Board;

import java.util.*;

public class HashMapCache implements BoardCache {

    private final Map<String, Board> cache;

    public HashMapCache() {
        this.cache = new HashMap<>();
    }

    public HashMapCache(Board initialBoard) {
        this.cache = new HashMap<>();
        Board initial = new Board(initialBoard.getPuzzleBoardCopy(), Board.INITIAL_BOARD);
        cache.put(initial.toString(), initial);
    }

    public boolean addBoardIfNotCached(Board board) {
        if (isAlreadyCached(board)) {
            return false;
        } else {
            cache.put(board.toString(), board);
            return true;
        }
    }

    public boolean isAlreadyCached(Board board) {
        return cache.containsKey(board.toString());
    }

    // if returns empty string = something went wrong
    public String getSequence(Board lastBoard) {
        if (lastBoard == null) {
            return "";
        }
        Board consideredBoard;
        StringBuilder result = new StringBuilder();
        HashMapCache tempCache = new HashMapCache();
        consideredBoard = lastBoard;

        while (consideredBoard.getParentMove() != Board.INITIAL_BOARD) {
            if (!cache.containsKey(consideredBoard.toString())) {
                return "";
            } else {

                Board b = cache.get(consideredBoard.toString());
                result.insert(0, b.getParentMove());
                cache.remove(b.toString());
                consideredBoard = getParentBoard(b, tempCache);
            }
        }
        // removing '0' at the beginning
        return result.toString().substring(1);
    }

    private Board getParentBoard(Board board, BoardCache tempCache) {
        char parentMove = board.getParentMove();
        switch (parentMove) {
            case Board.DOWN:
                return board.moveUp(tempCache);
            case Board.UP:
                return board.moveDown(tempCache);
            case Board.RIGHT:
                return board.moveLeft(tempCache);
            case Board.LEFT:
                return board.moveRight(tempCache);
            default:
                return board;
        }
    }

    public void clearCache() {
        cache.clear();
    }
}
