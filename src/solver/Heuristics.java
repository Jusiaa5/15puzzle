package solver;

public class Heuristics {

    public int wrongPlaced(byte[][] puzzleBoard) {
        int counter = 0;
        int size = puzzleBoard.length;

        for (byte i = 0; i < size; i++){
            for (byte j = 0; j < size; j++){

                if (puzzleBoard[i][j] != (size * i + j) + 1) {
                    counter += 1;
                }
            }
        }
        return counter;
    }

    public int manhattan(byte[][] puzzleBoard) {
        int length = 0;
        byte size = (byte)puzzleBoard.length;

        for (byte i = 0; i < size; i++){
            for (byte j = 0; j < size; j++){

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
