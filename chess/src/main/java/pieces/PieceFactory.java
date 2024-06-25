package pieces;

public class PieceFactory {
    // black
    public Piece createBlackPawn()
    {
        return new Piece(PieceColor.BLACK, PieceUnicode.BLACK_PAWN);
    }

    public Piece createBlackKnight()
    {
        return new Piece(PieceColor.BLACK, PieceUnicode.BLACK_KNIGHT);
    }

    public Piece createBlackBishop()
    {
        return new Piece(PieceColor.BLACK, PieceUnicode.BLACK_BISHOP);
    }

    public Piece createBlackRook()
    {
        return new Piece(PieceColor.BLACK, PieceUnicode.BLACK_ROOK);
    }

    public Piece createBlackQueen()
    {
        return new Piece(PieceColor.BLACK, PieceUnicode.BLACK_QUEEN);
    }

    public Piece createBlackKing()
    {
        return new Piece(PieceColor.BLACK, PieceUnicode.BLACK_KING);
    }

    // white
    public Piece createWhitePawn()
    {
        return new Piece(PieceColor.WHITE, PieceUnicode.WHITE_PAWN);
    }

    public Piece createWhiteKnight()
    {
        return new Piece(PieceColor.WHITE, PieceUnicode.WHITE_KNIGHT);
    }

    public Piece createWhiteBishop()
    {
        return new Piece(PieceColor.WHITE, PieceUnicode.WHITE_BISHOP);
    }

    public Piece createWhiteRook()
    {
        return new Piece(PieceColor.WHITE, PieceUnicode.WHITE_ROOK);
    }

    public Piece createWhiteQueen()
    {
        return new Piece(PieceColor.WHITE, PieceUnicode.WHITE_QUEEN);
    }

    public Piece createWhiteKing()
    {
        return new Piece(PieceColor.WHITE, PieceUnicode.WHITE_KING);
    }

}
