package solver;

import cache.HashSetCache;
import model.Board;

public class BF extends Solver {

    public BF(Board board, Board targetBoard) {
        super(board, targetBoard, new HashSetCache(board));
    }

    @Override
    Board solve() {
        return null;
    }
}
