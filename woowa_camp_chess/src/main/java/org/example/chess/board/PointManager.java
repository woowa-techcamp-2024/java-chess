package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

public class PointManager {
    private Board board;
    private double whitePoint;
    private double blackPoint;

    public PointManager(Board board) {
        this.board = board;
        this.whitePoint = 0;
        this.blackPoint = 0;
    }

    public String showPoint() {
        return "White: " + whitePoint + " Black: " + blackPoint;
    }

    public void updatePointByPosition(Position position) {
        Piece piece = board.findPiece(position);

        double defaultPoint = getPiecePoint(position, piece);

        if(piece.getColor().equals(Piece.Color.WHITE))
            blackPoint += defaultPoint;
        else
            whitePoint += defaultPoint;
    }

    private double getPiecePoint(Position position, Piece piece) {
        double defaultPoint;
        if(piece.getPieceType().equals(PieceType.PAWN) && board.isSamePawnOnColumn(piece.getColor(), position.getColumn(), position.getRow()))
            defaultPoint = 0.5;
        else defaultPoint = piece.getPieceType().getDefaultPoint();
        return defaultPoint;
    }
}
