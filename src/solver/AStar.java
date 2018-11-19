package solver;

import cache.HashSetCache;
import model.Board;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar extends Solver {

    private Queue<Board> queue;
    private Heuristics heuristics;
    private Method heuristic;

    public AStar(Board board, Board targetBoard, int heuristicID) {

        super(board, targetBoard, new HashSetCache(board));
        Comparator<Board> priorityComparator = Comparator.comparingInt(Board::getPromisingValue);
        queue = new PriorityQueue<>(priorityComparator);

        try {
            if (heuristicID == 1) {
                this.heuristic = Heuristics.class.getMethod("manhattan", byte[][].class);
            } else if (heuristicID == 2) {
                this.heuristic = Heuristics.class.getMethod("wrongPlaced", byte[][].class);
            } else {
                this.heuristic = null;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        heuristics = new Heuristics();
    }

    private int calculateCost(byte[][] puzzleBoard) {
        try {
            return (int) this.heuristic.invoke(this.heuristics, (Object)puzzleBoard);
        } catch (NullPointerException e) {
            // return 0 if no heuristic method were used
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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
                    temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()) + temp.getIdfsLevel());
                    queue.add(temp);
                }

                temp = currentBoard.moveRight(getCache());
                if (temp != currentBoard) {
                    temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()) + temp.getIdfsLevel());
                    queue.add(temp);
                }

                temp = currentBoard.moveUp(getCache());
                if (temp != currentBoard) {
                    temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()) + temp.getIdfsLevel());
                    queue.add(temp);
                }

                temp = currentBoard.moveLeft(getCache());
                if (temp != currentBoard) {
                    temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()) + temp.getIdfsLevel());
                    queue.add(temp);
                }
        }

    return null;
    }
}
