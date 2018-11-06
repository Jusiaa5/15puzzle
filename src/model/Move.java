package model;

public class Move {

    Board newBoard;

    //we return new board = in each solver we will have set of already existing boards to check if a node has been visited
    public Board moveUp(Board board) {
        //cannot move up
        if (board.getZeroRow() == 0) {
            return board;
        }

        newBoard.copy(board);
        newBoard.swapNumbers(board.getZeroRow() - 1, board.getZeroColumn());
        return newBoard;
    }

    public Board moveDown(Board board) {
        if (board.getZeroRow() == (Math.sqrt(board.getSize()) - 1)) {
            return board;
        }

        newBoard.copy(board);
        newBoard.swapNumbers(board.getZeroRow()+1, board.getZeroColumn());
        return newBoard;
    }

    public Board moveRight(Board board) {
        if (board.getZeroColumn() == (Math.sqrt(board.getSize()) - 1)) {
            return board;
        }

        newBoard.copy(board);
        newBoard.swapNumbers(board.getZeroRow(), board.getZeroColumn() + 1);
        return newBoard;
    }

    public Board moveLeft(Board board) {
        if (newBoard.getZeroColumn() == 0) {
            return board;
        }

        newBoard.copy(board);
        newBoard.swapNumbers(board.getZeroRow(), board.getZeroColumn() - 1);
        return newBoard;
    }
}
