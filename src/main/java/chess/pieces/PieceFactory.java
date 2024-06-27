package chess.pieces;

public class PieceFactory {
    private PieceFactory(){
    }
    public static ChessPiece createPiece(PieceTypes piece) {
        PieceTypes.Type type = piece.getType();
        return switch(type){
            case PAWN -> new Pawn(piece);
            case ROOK -> new Rook(piece);
            case KNIGHT -> new Knight(piece);
            case BISHOP -> new Bishop(piece);
            case QUEEN -> new Queen(piece);
            case KING -> new King(piece);
            case NO_PIECE -> new NoPiece(piece);
        };
    }
}
