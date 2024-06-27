package chess.pieces;

import java.util.List;

public abstract class ChessPiece {
    private final PieceTypes pieceType;

    protected ChessPiece(PieceTypes pieceType) {
        this.pieceType = pieceType;
    }

    public PieceTypes.Color getColor() {
        return this.pieceType.getColor();
    }

    public char getRepresentation() {
        return this.pieceType.getRepresentation();
    }

    public PieceTypes.Type getType() {
        return this.pieceType.getType();
    }

    public boolean isWhite() {
        return PieceTypes.Color.WHITE.equals(this.getColor());
    }

    public boolean isBlack() {
        return PieceTypes.Color.BLACK.equals(this.getColor());
    }

    public Course getCourse(){
        return this.pieceType.getCourse();
    }
}
