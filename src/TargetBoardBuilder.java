import model.Board;

public class TargetBoardBuilder {

    // 0 is in the bottom right corner ([i - 1][j - 1])
    public Board buildTargetBoard(Board board) {
        byte length = (byte)board.getPuzzleBoard()[0].length;
        byte[][] target = new byte[length][length];
        byte counter = 1;
        byte i = 0;
        byte j = 0;

        for (i = 0; i < length; i++) {
            for (j = 0; j < length; j++) {
                target[i][j] = counter;
                counter++;
            }
        }
        target[i - 1][j - 1] = 0;
        return new Board(target, Board.TARGET_BOARD);
    }

}
