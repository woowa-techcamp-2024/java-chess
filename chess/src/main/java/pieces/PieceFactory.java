package pieces;

public class PieceFactory {
    public Piece createPiece(PieceColor color, PieceType type) {
        return new Piece(color, type);
    }

    public Piece createBlank(){
        return new Piece(PieceColor.NO_COLOR, PieceType.NO_PIECE);
    }
}
