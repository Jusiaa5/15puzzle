import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PuzzleFileReader {

    public byte[][] readFile(String filename) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        byte[][] result = null;
        byte i = 0, length = 0;
        String[] line;
        while (sc.hasNextLine()) {
            line = sc.nextLine().split(" ");

            // Assuming that board is always a square
            if (result == null) {
                length = (byte)line.length;
                result = new byte[length][length];
            }

            for (int j = 0; j < length; j++) {
                result[i][j] = Byte.parseByte(line[j]);
            }
            ++i;
        }
        return result;
    }
}
