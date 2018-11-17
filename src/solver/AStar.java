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
    private int promising;
    public static final int MAX_DEPTH = 10;

    public AStar(Board board, Board targetBoard, int heuristicID) {

        super(board, targetBoard, new HashSetCache(board));
        Comparator<Board> priorityComparator = Comparator.comparingInt(Board::getPromisingValue);
        queue = new PriorityQueue<>(priorityComparator);

        try {
            if (heuristicID == 1) {
                this.heuristic = Heuristics.class.getMethod("manhattan", int[][].class);
            } else if (heuristicID == 2) {
                this.heuristic = Heuristics.class.getMethod("wrongPlaced", int[][].class);
            } else {
                this.heuristic = null;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        heuristics = new Heuristics();
    }

    private int calculateCost(int[][] puzzleBoard) {
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

        try {
            while (!queue.isEmpty()) {
                for(int i = 0; i < MAX_DEPTH; i++) {
                    currentBoard = queue.poll();

                    if (Arrays.deepEquals(currentBoard.getPuzzleBoard(), getTargetBoard().getPuzzleBoard())) {
                        return currentBoard;
                    }

                    if (currentBoard.getIdfsLevel() > i) {
                        continue;
                    }

                    temp = currentBoard.moveDown(getCache());
                    if (temp != currentBoard) {
                        temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()));
                        queue.add(temp);
                    }

                    temp = currentBoard.moveRight(getCache());
                    if (temp != currentBoard) {
                        temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()));
                        queue.add(temp);
                    }

                    temp = currentBoard.moveUp(getCache());
                    if (temp != currentBoard) {
                        temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()));
                        queue.add(temp);
                    }

                    temp = currentBoard.moveLeft(getCache());
                    if (temp != currentBoard) {
                        temp.setPromisingValue(calculateCost(temp.getPuzzleBoard()));
                        queue.add(temp);
                    }
                }
            }
        }  catch (NullPointerException e) {
            System.err.println("The structure is empty - the path is too short to find the answer.");
        }

        return null;
    }
}
