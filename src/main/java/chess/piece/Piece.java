package chess.piece;

import chess.piece.rule.PieceMove;

public abstract class Piece {

    protected final PieceColor color;

    protected Piece(final PieceColor color) {
        this.color = color;
    }

    public abstract Type getType();

    public abstract PieceMove getMoveable();

//    public abstract int movableDistance();

    public PieceColor getColor() {
        return color;
    }

    public boolean isSameColor(final Piece piece) {
        return this.color == piece.color;
    }
}
