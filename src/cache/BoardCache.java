package cache;

import model.Board;

public interface BoardCache {

    boolean addBoardIfNotCached(Board board);

    boolean isAlreadyCached(Board board);

    void clearCache();

    String getSequence(Board lastBoard);
}
