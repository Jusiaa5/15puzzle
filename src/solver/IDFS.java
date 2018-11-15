package solver;

import cache.HashSetCache;
import model.Board;

import java.util.Arrays;
import java.util.Stack;

public class IDFS extends Solver {

    public static final int MAX_DEPTH = 100;
    private Stack<Board> stack;

    public IDFS(Board board, Board targetBoard) {
        super(board, targetBoard, new HashSetCache(board));
        stack = new Stack<>();
    }

    @Override
    Board solve() {
        stack.push(getBoard());
        Board currentBoard;
        Board temp;

        while(!stack.isEmpty()) {
            for (int i = 0; i < MAX_DEPTH; i++) {

                currentBoard = stack.pop();

                if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), getTargetBoard().getPuzzleBoard())) {
                    return currentBoard;
                }

                if (currentBoard.getIdfsLevel() > i) {
                    continue;
                }
                temp = currentBoard.moveUp(getCache());
                if (currentBoard != temp) {
                    stack.push(temp);
                }
                temp = currentBoard.moveDown(getCache());
                if (currentBoard != temp) {
                    stack.push(temp);
                }
                temp = currentBoard.moveLeft(getCache());
                if (currentBoard != temp) {
                    stack.push(temp);
                }
                temp = currentBoard.moveRight(getCache());
                if (currentBoard != temp) {
                    stack.push(temp);
                }

            }
        }
        return null;
    }

}
