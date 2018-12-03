package model;

import cache.BoardCache;

import java.util.Arrays;

public class Board {

    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char RIGHT = 'R';
    public static final char LEFT = 'L';

    public static final char INITIAL_BOARD = '0';
    public static final char TARGET_BOARD = '1';

    private byte[][] puzzleBoard;
    private char parentMove;
    private int promisingValue;
    private byte idfsLevel;

    private byte x0;
    private byte y0;

    public Board(byte[][] puzzleBoard, char parentMove) {
        this.puzzleBoard = puzzleBoard;
        this.parentMove = parentMove;
        this.idfsLevel = 0;
        findZero();
    }

    public Board(byte[][] puzzleBoard, char parentMove, byte idfsLevel) {
        this.puzzleBoard = puzzleBoard;
        this.parentMove = parentMove;
        this.idfsLevel = idfsLevel;
        findZero();
    }

    public void findZero() {
        for (byte i = 0; i < puzzleBoard.length; i++) {
            for (byte j = 0; j < puzzleBoard.length; j++) {
                if (puzzleBoard[i][j] == 0) {
                    this.x0 = i;
                    this.y0 = j;
                }
            }
        }
    }

    public byte getZeroRow() {
        return this.x0;
    }

    public byte getZeroColumn() {
        return this.y0;
    }

    public byte[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    // useful for creating new Boards - in order to copy the existing 2D array
    // usage: new Board(oldBoard.getPuzzleBoardCopy())
    public byte[][] getPuzzleBoardCopy() {
        byte[][] newArray = new byte[puzzleBoard.length][];
        for (byte i = 0; i < puzzleBoard.length; i++) {
            newArray[i] = puzzleBoard[i].clone();
        }
        return newArray;
    }

    private void swapNumbers(byte elementRow, byte elementColumn) {
        puzzleBoard[this.x0][this.y0] = puzzleBoard[elementRow][elementColumn];
        puzzleBoard[elementRow][elementColumn] = 0;
        this.x0 = elementRow;
        this.y0 = elementColumn;
    }

    public Board moveUp(BoardCache cache) {
        //cannot move up
        if (this.x0 == 0) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.UP, (byte)(this.getIdfsLevel() + 1));
        newBoard.swapNumbers((byte)(newBoard.getZeroRow() - 1), newBoard.getZeroColumn());
        if (cache.addBoardIfNotCached(newBoard))
            return newBoard;
        else return this;
    }

    public Board moveDown(BoardCache cache) {
        if (this.x0 == puzzleBoard.length - 1) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.DOWN, (byte)(this.getIdfsLevel() + 1));
        newBoard.swapNumbers((byte)(newBoard.getZeroRow() + 1), newBoard.getZeroColumn());
        if (cache.addBoardIfNotCached(newBoard))
            return newBoard;
        else return this;
    }

    public Board moveRight(BoardCache cache) {
        if (this.y0 == puzzleBoard.length - 1) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.RIGHT, (byte)(this.getIdfsLevel() + 1));
        newBoard.swapNumbers(newBoard.getZeroRow(), (byte)(newBoard.getZeroColumn() + 1));
        if (cache.addBoardIfNotCached(newBoard))
            return newBoard;
        else return this;
    }

    public Board moveLeft(BoardCache cache) {
        if (this.y0 == 0) {
            return this;
        }

        Board newBoard = new Board(this.getPuzzleBoardCopy(), Board.LEFT, (byte)(this.getIdfsLevel() + 1));
        newBoard.swapNumbers(newBoard.getZeroRow(), (byte)(newBoard.getZeroColumn() - 1));
        if (cache.addBoardIfNotCached(newBoard))
            return newBoard;
        else return this;
    }

    public char getParentMove() {
        return parentMove;
    }

    public int getPromisingValue() {
        return promisingValue;
    }

    public void setPromisingValue(int promisingValue) {
        this.promisingValue = promisingValue;
    }

    public int getIdfsLevel() {
        return this.idfsLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;
        return Arrays.deepEquals(puzzleBoard, board.puzzleBoard);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(puzzleBoard);
        return result;
    }

    @Override
    public String toString() {
            return Arrays.deepToString(puzzleBoard);
    }

}