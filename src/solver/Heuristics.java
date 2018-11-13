package solver;

import model.Board;

public class Heuristics {

    private int size;
    private int[][] puzzleBoard;

    public Heuristics(Board board) {
        this.puzzleBoard = board.getPuzzleBoard();
        this.size = puzzleBoard.length;
    }

    public void setPuzzleBoard(int[][] puzzleBoard) {
        this.puzzleBoard = puzzleBoard;
    }

    public int wrongPlaced() {
        int counter = 0;

        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){

                if (puzzleBoard[i][j] != (this.size * i + j)) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    public int manhattan() {
        int length = 0;

        for (int i = 0; i < this.size; i++){
            for (int j = 0; j < this.size; j++){

                int value = this.puzzleBoard[i][j];

                if (value != 0) {
                    int width = Math.abs(i - (value - 1) / this.size);
                    int height = Math.abs(j - (value - 1) % this.size);
                    length += width + height;
                }
            }
        }
        return length;
    }
}
