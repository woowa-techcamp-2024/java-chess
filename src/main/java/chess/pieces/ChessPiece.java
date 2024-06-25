package chess.pieces;

public interface ChessPiece {
    PieceTypes.Color getColor();
    char getRepresentation();
    PieceTypes.Type getType();
}
