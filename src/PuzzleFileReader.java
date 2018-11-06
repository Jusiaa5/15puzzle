import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PuzzleFileReader {

    public int[][] readLines(String filename) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int[][] result = null;
        int i = 0, length = 0;
        String[] line;
        while (sc.hasNextLine()) {
            line = sc.nextLine().split(" ");

            // Assuming that board is always a square
            if (result == null) {
                length = line.length;
                result = new int[length][length];
            }

            for (int j = 0; j < length; j++) {
                result[i][j] = Integer.parseInt(line[j]);
            }
            ++i;
        }
        return result;
    }
}
