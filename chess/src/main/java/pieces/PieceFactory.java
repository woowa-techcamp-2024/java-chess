package pieces;

public class PieceFactory {
    public Piece createBlackPiece(PieceType type){
        if(type.equals(PieceType.ROOK)){
            return new Piece(PieceColor.BLACK, type, PieceUnicode.BLACK_ROOK);
        }
        else if(type.equals(PieceType.KNIGHT))
        {
            return new Piece(PieceColor.BLACK, type, PieceUnicode.BLACK_KNIGHT);
        }
        else if(type.equals(PieceType.BISHOP))
        {
            return new Piece(PieceColor.BLACK, type, PieceUnicode.BLACK_BISHOP);
        }
        else if(type.equals(PieceType.QUEEN))
        {
            return new Piece(PieceColor.BLACK, type, PieceUnicode.BLACK_QUEEN);
        }
        else if(type.equals(PieceType.KING))
        {
            return new Piece(PieceColor.BLACK, type, PieceUnicode.BLACK_KING);
        }
        return new Piece(PieceColor.BLACK, type, PieceUnicode.BLACK_PAWN);
    }

    public Piece createWhitePiece(PieceType type) {
        if(type.equals(PieceType.ROOK))
        {
            return new Piece(PieceColor.WHITE, type, PieceUnicode.WHITE_ROOK);
        }
        else if (type.equals(PieceType.KNIGHT)) {
            return new Piece(PieceColor.WHITE, type, PieceUnicode.WHITE_KNIGHT);
        } else if (type.equals(PieceType.BISHOP)) {
            return new Piece(PieceColor.WHITE, type, PieceUnicode.WHITE_BISHOP);
        } else if (type.equals(PieceType.QUEEN)) {
            return new Piece(PieceColor.WHITE, type, PieceUnicode.WHITE_QUEEN);
        } else if (type.equals(PieceType.KING)) {
            return new Piece(PieceColor.WHITE, type, PieceUnicode.WHITE_KING);
        }
        return new Piece(PieceColor.WHITE, type, PieceUnicode.WHITE_PAWN);
    }

    public Piece createBlank(){
        return new Piece(PieceColor.NO_COLOR, PieceType.NO_PIECE, PieceUnicode.BLANK);
    }
}
