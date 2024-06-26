package pieces;

public class PieceFactory {
    public Piece createPiece(PieceColor color, PieceType type) {
        return new Piece(color, type);
    }

    public Piece createBlackPawn(){
        return createPiece(PieceColor.BLACK, PieceType.PAWN);
    }

    public Piece createBlackRook(){
        return createPiece(PieceColor.BLACK, PieceType.ROOK);
    }

    public Piece createBlackKnight(){
        return createPiece(PieceColor.BLACK, PieceType.KNIGHT);
    }
    public Piece createBlackBishop(){
        return createPiece(PieceColor.BLACK, PieceType.BISHOP);
    }
    public Piece createBlackQueen(){
        return createPiece(PieceColor.BLACK, PieceType.QUEEN);
    }
    public Piece createBlackKing(){
        return createPiece(PieceColor.BLACK, PieceType.KING);
    }

    public Piece createWhitePawn(){
        return createPiece(PieceColor.WHITE, PieceType.PAWN);
    }
    public Piece createWhiteRook(){
        return createPiece(PieceColor.WHITE, PieceType.ROOK);
    }
    public Piece createWhiteKnight(){
        return createPiece(PieceColor.WHITE, PieceType.KNIGHT);
    }
    public Piece createWhiteBishop(){
        return createPiece(PieceColor.WHITE, PieceType.BISHOP);
    }
    public Piece createWhiteQueen(){
        return createPiece(PieceColor.WHITE, PieceType.QUEEN);
    }
    public Piece createWhiteKing(){
        return createPiece(PieceColor.WHITE, PieceType.KING);
    }

    public Piece createBlank(){
        return new Piece(PieceColor.NO_COLOR, PieceType.NO_PIECE);
    }
}
