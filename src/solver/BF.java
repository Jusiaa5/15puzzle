package solver;

import cache.HashSetCache;
import model.Board;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BF extends Solver {

    private Queue<Board> queue;
    private Method heuristic;

    public BF(Board board, Board targetBoard, int heuristicID) {
        super(board, targetBoard, new HashSetCache(board));
        queue = new ArrayDeque<>();

        try {
            if (heuristicID == 1) {
                this.heuristic = Heuristics.class.getMethod("manhattan");
            } else if (heuristicID == 2) {
                this.heuristic = Heuristics.class.getMethod("wrongPlaced");
            } else {
                this.heuristic = null;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private int calculateCost(int[][] puzzleBoard) {
        try {
            return (int)this.heuristic.invoke(puzzleBoard);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // return 0 if no heuristic method were used
        return 0;
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
