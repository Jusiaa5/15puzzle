package solver;

public class Heuristics {

    public int wrongPlaced(int[][] puzzleBoard) {
        int counter = 0;
        int size = puzzleBoard.length;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){

                if (puzzleBoard[i][j] != (size * i + j)) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    public int manhattan(int[][] puzzleBoard) {
        int length = 0;
        int size = puzzleBoard.length;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){

                int value = puzzleBoard[i][j];

                if (value != 0) {
                    int width = Math.abs(i - (value - 1) / size);
                    int height = Math.abs(j - (value - 1) % size);
                    length += width + height;
                }
            }
        }
        return length;
    }
}
