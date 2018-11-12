package solver;

import cache.HashSetCache;
import model.Board;

import java.util.Arrays;
import java.util.Stack;

public class DFS extends Solver {

    // LIFO stack
    private Stack<Board> stack;

    public DFS(Board board, Board targetBoard) {
        super(board, targetBoard, new HashSetCache(board));
        stack = new Stack<>();
    }

    @Override
    Board solve() {
        stack.push(getBoard());
        Board currentBoard;

        while (!stack.isEmpty()) {
            currentBoard = stack.pop();

            if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), getTargetBoard().getPuzzleBoard())) {
                return currentBoard;
            }

            if (currentBoard.moveUp(getCache())) {
                stack.push(new Board(currentBoard.getPuzzleBoardCopy(), Board.UP));
            }
            if (currentBoard.moveLeft(getCache())) {
                stack.push(new Board(currentBoard.getPuzzleBoardCopy(), Board.LEFT));
            }
            if (currentBoard.moveRight(getCache())) {
                stack.push(new Board(currentBoard.getPuzzleBoardCopy(), Board.RIGHT));
            }
            if (currentBoard.moveDown(getCache())) {
                stack.push(new Board(currentBoard.getPuzzleBoardCopy(), Board.DOWN));
            }
        }
        return null;
    }
}
