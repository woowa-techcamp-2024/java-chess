package pieces;

import chess.Position;

public class PieceFactory {
    public Pawn createBlackPawn(Position position){
        return new Pawn(PieceColor.BLACK, PieceType.PAWN, position);
    }
    public Rook createBlackRook(Position position){
        return new Rook(PieceColor.BLACK, PieceType.ROOK, position);
    }
    public Knight createBlackKnight(Position position){
        return new Knight(PieceColor.BLACK, PieceType.KNIGHT, position);
    }
    public Bishop createBlackBishop(Position position){
        return new Bishop(PieceColor.BLACK, PieceType.BISHOP, position);
    }
    public Queen createBlackQueen(Position position){
        return new Queen(PieceColor.BLACK, PieceType.QUEEN, position);
    }
    public King createBlackKing(Position position){
        return new King(PieceColor.BLACK, PieceType.KING, position);
    }

    public Pawn createWhitePawn(Position position){
        return new Pawn(PieceColor.WHITE, PieceType.PAWN, position);
    }
    public Rook createWhiteRook(Position position){
        return new Rook(PieceColor.WHITE, PieceType.ROOK, position);
    }
    public Knight createWhiteKnight(Position position){
        return new Knight(PieceColor.WHITE, PieceType.KNIGHT, position);
    }
    public Bishop createWhiteBishop(Position position){
        return new Bishop(PieceColor.WHITE, PieceType.BISHOP, position);
    }
    public Queen createWhiteQueen(Position position){
        return new Queen(PieceColor.WHITE, PieceType.QUEEN, position);
    }
    public King createWhiteKing(Position position){
        return new King(PieceColor.WHITE, PieceType.KING, position);
    }

    public Blank createBlank(Position position){
        return new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, position);
    }
}
