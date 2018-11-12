package solver;

import cache.HashSetCache;
import model.Board;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BFS extends Solver {

    // FIFO queue
    private Queue<Board> queue;

    public BFS(Board board, Board targetBoard) {
        super(board, targetBoard, new HashSetCache(board));
        queue = new ArrayDeque<>();
    }

    @Override
    Board solve() {
        queue.add(getBoard());
        Board currentBoard;

        while (!queue.isEmpty()) {
            currentBoard = queue.poll();

            if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), getTargetBoard().getPuzzleBoard())) {
                return currentBoard;
            }

            if (currentBoard.moveDown(getCache())) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.DOWN));
            }
            if (currentBoard.moveRight(getCache())) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.RIGHT));
            }
            if (currentBoard.moveUp(getCache())) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.UP));
            }
            if (currentBoard.moveLeft(getCache())) {
                queue.add(new Board(currentBoard.getPuzzleBoardCopy(), Board.LEFT));
            }
        }
        return null;
    }
}
