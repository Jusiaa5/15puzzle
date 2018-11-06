package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private int[][] puzzleBoard;
    private int size;

    public Board(int size) {
        this.size = size;
        int length = (int) Math.sqrt(size);
        puzzleBoard = new int[length][length];
    }

    public Board(int[][] puzzleBoard) {
        this.size = (int) Math.pow(puzzleBoard.length, 2);
        this.puzzleBoard = puzzleBoard;
    }

    //populate board with random numbers for a given range; for development purposes
    public void fillBoardWithRandomNumbers() {
        Random random = new Random();
        List<Integer> numbersList = new ArrayList<>();
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

    public int getZeroRow(){
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return i;
                }
            }
        }
        return 1000000;
    }

    public int getZeroColumn(){
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                if (puzzleBoard[i][j] == 0) {
                    return j;
                }
            }
        }
        return 1000000;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setNumber(int row, int column, int number) {
        puzzleBoard[row][column] = number;
    }

    public int[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    public void swapNumbers(int elementRow, int elementColumn) {
        int zeroRow = getZeroRow();
        int zeroColumn = getZeroColumn();
        puzzleBoard[zeroRow][zeroColumn] = puzzleBoard[elementRow][elementColumn];
        puzzleBoard[elementRow][elementColumn] = 0;
    }

    public void copy(Board boardToCopy) {
        this.size = boardToCopy.getSize();
        for (int i = 0; i < puzzleBoard.length; i++) {
            for (int j = 0; j < puzzleBoard.length; j++) {
                puzzleBoard[i][j] = boardToCopy.getNumber(i, j);
            }
        }
    }

}
