package model;

import cache.BoardCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private int[][] puzzleBoard;

    public Board(int[][] puzzleBoard) {
        this.puzzleBoard = puzzleBoard;
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

    private void swapNumbers(int elementRow, int elementColumn) {
        int zeroRow = getZeroRow();
        int zeroColumn = getZeroColumn();
        puzzleBoard[zeroRow][zeroColumn] = puzzleBoard[elementRow][elementColumn];
        puzzleBoard[elementRow][elementColumn] = 0;
    }

    // all 'move' methods return false if empty field can't be moved or if given combination already exists

    public boolean moveUp(BoardCache cache) {
        //cannot move up
        if (this.getZeroRow() == 0) {
            return false;
        }

        Board newBoard = new Board(puzzleBoard);
        this.swapNumbers(this.getZeroRow() - 1, this.getZeroColumn());
        return cache.addBoardIfNotCached(newBoard);
    }

    public boolean moveDown(BoardCache cache) {
        if (this.getZeroRow() == puzzleBoard.length-1) {
            return false;
        }

        Board newBoard = new Board(puzzleBoard);
        this.swapNumbers(this.getZeroRow() + 1, this.getZeroColumn());
        return cache.addBoardIfNotCached(newBoard);
    }

    public boolean moveRight(BoardCache cache) {
        if (this.getZeroColumn() == puzzleBoard.length-1) {
            return false;
        }

        Board newBoard = new Board(puzzleBoard);
        this.swapNumbers(this.getZeroRow(), this.getZeroColumn() + 1);
        return cache.addBoardIfNotCached(newBoard);
    }

    public boolean moveLeft(BoardCache cache) {
        if (this.getZeroColumn() == 0) {
            return false;
        }

        Board newBoard = new Board(puzzleBoard);
        this.swapNumbers(this.getZeroRow(), this.getZeroColumn() - 1);
        return cache.addBoardIfNotCached(newBoard);
    }

}
