package chess;

import chess.pieces.ChessPiece;
import chess.pieces.PieceTypes;

public interface PieceCreator {
    ChessPiece createPiece(PieceTypes type);
}
