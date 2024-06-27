package chess;

import chess.pieces.ChessPiece;
import chess.pieces.Pawn;
import chess.pieces.PieceFactory;
import chess.pieces.PieceTypes;

public class PieceCreatorWithFactory implements PieceCreator{
    @Override
    public ChessPiece createPiece(PieceTypes type) {
        return PieceFactory.createPiece(type);
    }
}
