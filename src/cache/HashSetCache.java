package cache;

import model.Board;

import java.util.*;

public class HashSetCache implements BoardCache {

    private final HashSet<Board> cache;

    public HashSetCache() {
        this.cache = new HashSet<>();
    }

    public HashSetCache(Board initialBoard) {
        this.cache = new HashSet<>();
        cache.add(new Board(initialBoard.getPuzzleBoardCopy(), Board.INITIAL_BOARD));
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

    // if returns empty string = something went wrong
    public String getSequence(Board lastBoard) {
        if (lastBoard == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        HashSetCache tempCache = new HashSetCache();
        while (lastBoard.getParentMove() != Board.INITIAL_BOARD) {
            final Board board = lastBoard;
            Optional<Board> cacheBoard =
                    cache.stream()
                            .filter(b -> Arrays.deepEquals(b.getPuzzleBoard(), board.getPuzzleBoard()))
                            .findAny();
            if (!cacheBoard.isPresent()) {
                return "";
            } else {
                Board b = cacheBoard.get();
                result.insert(0, b.getParentMove());
                cache.remove(b);
                lastBoard = getParentBoard(b, tempCache);
            }
        }
        // removing '0' at the beginning
        return result.toString().substring(1);
    }

    private Board getParentBoard(Board board, BoardCache tempCache) {
        char parentMove = board.getParentMove();
        switch (parentMove) {
            case Board.DOWN:
                board.moveUp(tempCache);
                return new Board(board.getPuzzleBoardCopy(), Board.UP);
            case Board.UP:
                board.moveDown(tempCache);
                return new Board(board.getPuzzleBoardCopy(), Board.DOWN);
            case Board.RIGHT:
                board.moveLeft(tempCache);
                return new Board(board.getPuzzleBoardCopy(), Board.LEFT);
            case Board.LEFT:
                board.moveRight(tempCache);
                return new Board(board.getPuzzleBoardCopy(), Board.RIGHT);
            default:
                return new Board(board.getPuzzleBoardCopy(), Board.INITIAL_BOARD);
        }
    }

    public void clearCache() {
        cache.clear();
    }
}
