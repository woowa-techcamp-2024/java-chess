package chess.game;

import chess.board.Board;
import chess.pieces.Piece;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece piece = board.findPiece(sourcePosition);
        if(piece.verifyMovePosition(sourcePosition, targetPosition)) {
            board.move(sourcePosition, targetPosition);
        } else throw new IllegalMoveException("Illegal move!\n");
    }

}
