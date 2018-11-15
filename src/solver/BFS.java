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
        Board temp;

        while (!queue.isEmpty()) {
            currentBoard = queue.poll();

            if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), getTargetBoard().getPuzzleBoard())) {
                return currentBoard;
            }

            temp = currentBoard.moveDown(getCache());
            if (temp != currentBoard) {
                queue.add(temp);
            }

            temp = currentBoard.moveRight(getCache());
            if (temp != currentBoard) {
                queue.add(temp);
            }

            temp = currentBoard.moveUp(getCache());
            if (temp != currentBoard) {
                queue.add(temp);
            }

            temp = currentBoard.moveLeft(getCache());
            if (temp != currentBoard) {
                queue.add(temp);
            }

        }
        return null;
    }
}
