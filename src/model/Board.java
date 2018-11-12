package model;

import cache.BoardCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char RIGHT = 'R';
    public static final char LEFT = 'L';
    public static final char INITIAL_BOARD = '0';
    public static final char TARGET_BOARD = '1';

    private int[][] puzzleBoard;
    private char parentMove;

    public Board(int[][] puzzleBoard, char parentMove) {
        this.puzzleBoard = puzzleBoard;
        this.parentMove = parentMove;
    }

    //populate board with random numbers for a given range; for development purposes
    public void fillBoardWithRandomNumbers() {
        Random random = new Random();
        List<Integer> numbersList = new ArrayList<>();
        int size = (int) Math.pow(puzzleBoard.length, 2);
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                int randomNum = random.nextInt((size) + 1);
                if (!numbersList.contains(randomNum)) {
                    puzzleBoard[i][j] = randomNum;
                    numbersList.add(randomNum);
                }
            }
        }
    }

    public int getNumber(int row, int column) {
        return puzzleBoard[row][column];
    }

    public int getZeroRow() {
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int getZeroColumn() {
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return j;
                }
            }
        }
        return -1;
    }

    public void setNumber(int row, int column, int number) {
        puzzleBoard[row][column] = number;
    }

    public int[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    // useful for creating new Boards - in order to copy the existing 2D array
    // usage: new Board(oldBoard.getPuzzleBoardCopy())
    public int[][] getPuzzleBoardCopy() {
        return clone2DArray(puzzleBoard);
    }

    private void swapNumbers(int elementRow, int elementColumn) {
        int zeroRow = getZeroRow();
        int zeroColumn = getZeroColumn();
        puzzleBoard[zeroRow][zeroColumn] = puzzleBoard[elementRow][elementColumn];
        puzzleBoard[elementRow][elementColumn] = 0;
    }

    public Board moveUp(BoardCache cache) {
        //cannot move up
        if (this.getZeroRow() == 0) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.UP);
        newBoard.swapNumbers(newBoard.getZeroRow() - 1, newBoard.getZeroColumn());
        cache.addBoardIfNotCached(newBoard);
        return newBoard;
    }

    public Board moveDown(BoardCache cache) {
        if (this.getZeroRow() == puzzleBoard.length - 1) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.DOWN);
        newBoard.swapNumbers(newBoard.getZeroRow() + 1, newBoard.getZeroColumn());
        cache.addBoardIfNotCached(newBoard);
        return newBoard;
    }

    public Board moveRight(BoardCache cache) {
        if (this.getZeroColumn() == puzzleBoard.length - 1) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.RIGHT);
        newBoard.swapNumbers(newBoard.getZeroRow(), newBoard.getZeroColumn() + 1);
        cache.addBoardIfNotCached(newBoard);
        return newBoard;

    }

    public Board moveLeft(BoardCache cache) {
        if (this.getZeroColumn() == 0) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.LEFT);
        newBoard.swapNumbers(newBoard.getZeroRow(), newBoard.getZeroColumn() - 1);
        cache.addBoardIfNotCached(newBoard);
        return newBoard;
    }

    private int[][] clone2DArray(int[][] board) {
        int[][] newArray = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            newArray[i] = board[i].clone();
        }
        return newArray;
    }

    public char getParentMove() {
        return parentMove;
    }

    public void setParentMove(char parentMove) {
        this.parentMove = parentMove;
    }
}