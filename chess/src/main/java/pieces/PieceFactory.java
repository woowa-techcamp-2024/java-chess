package pieces;

import chess.Position;

public class PieceFactory {
    public Piece createPiece(PieceColor color, PieceType type, Position position) {
        return new Piece(color, type, position);
    }

    public Piece createBlackPawn(Position position){
        return createPiece(PieceColor.BLACK, PieceType.PAWN, position);
    }

    public Piece createBlackRook(Position position){
        return createPiece(PieceColor.BLACK, PieceType.ROOK, position);
    }

    public Piece createBlackKnight(Position position){
        return createPiece(PieceColor.BLACK, PieceType.KNIGHT, position);
    }
    public Piece createBlackBishop(Position position){
        return createPiece(PieceColor.BLACK, PieceType.BISHOP, position);
    }
    public Piece createBlackQueen(Position position){
        return createPiece(PieceColor.BLACK, PieceType.QUEEN, position);
    }
    public Piece createBlackKing(Position position){
        return createPiece(PieceColor.BLACK, PieceType.KING, position);
    }

    public Piece createWhitePawn(Position position){
        return createPiece(PieceColor.WHITE, PieceType.PAWN, position);
    }
    public Piece createWhiteRook(Position position){
        return createPiece(PieceColor.WHITE, PieceType.ROOK, position);
    }
    public Piece createWhiteKnight(Position position){
        return createPiece(PieceColor.WHITE, PieceType.KNIGHT, position);
    }
    public Piece createWhiteBishop(Position position){
        return createPiece(PieceColor.WHITE, PieceType.BISHOP, position);
    }
    public Piece createWhiteQueen(Position position){
        return createPiece(PieceColor.WHITE, PieceType.QUEEN, position);
    }
    public Piece createWhiteKing(Position position){
        return createPiece(PieceColor.WHITE, PieceType.KING, position);
    }

    public Piece createBlank(Position position){
        return new Piece(PieceColor.NO_COLOR, PieceType.NO_PIECE, position);
    }
}
