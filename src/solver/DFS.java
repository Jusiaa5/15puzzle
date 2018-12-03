package solver;

import cache.HashMapCache;
import model.Board;

import java.util.Arrays;
import java.util.Stack;

public class DFS extends Solver {

    // LIFO stack
    private Stack<Board> stack;

    public DFS(Board board, Board targetBoard) {
        super(board, targetBoard, new HashMapCache(board));
        stack = new Stack<>();
    }

    @Override
    Board solve() {
        stack.push(getBoard());
        Board currentBoard;
        Board temp;

        while (!stack.isEmpty()) {
            currentBoard = stack.pop();

            if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), getTargetBoard().getPuzzleBoard())) {
                return currentBoard;
            }

            temp = currentBoard.moveDown(getCache());
            if (temp != currentBoard) {
                stack.push(temp);
            }

            temp = currentBoard.moveLeft(getCache());
            if (temp != currentBoard) {
                stack.push(temp);
            }

            temp = currentBoard.moveRight(getCache());
            if (temp != currentBoard) {
                stack.push(temp);
            }

            temp = currentBoard.moveUp(getCache());
            if (temp != currentBoard) {
                stack.push(temp);
            }
        }
        return null;
    }
}
