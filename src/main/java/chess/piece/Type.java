package chess.piece;

import chess.exception.InvalidPieceException;

import java.util.function.Function;
import java.util.stream.Stream;

import static chess.exception.ExceptionConstant.INVALID_PIECE;

public enum Type {
    PAWN('♙', 1.0, Pawn::create),
    ROOK('♖', 5.0, Rook::create),
    QUEEN('♕', 9.0, Queen::create),
    KING('♔', 0.0, King::create),
    KNIGHT('♘', 2.5, Knight::create),
    BISHOP('♗', 3.0, Bishop::create),
    NO_PIECE('.', 0.0, Blank::create);

    private final char representation;
    private final double defaultScore;
    private final Function<PieceColor, Piece> constructor;

    Type(final char representation, final double defaultScore, Function<PieceColor, Piece> constructor) {
        this.representation = representation;
        this.defaultScore = defaultScore;
        this.constructor = constructor;
    }

    public char getRepresentation(final PieceColor pieceColor) {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return (char) (this.representation + 6);
        } else if (pieceColor.equals(PieceColor.NO_COLOR)) {
            return NO_PIECE.representation;
        }

        return this.representation;
    }

    public char getRepresentation() {
        return this.representation;
    }

    public double getDefaultScore() {
        return this.defaultScore;
    }

    public Piece createPiece(PieceColor color) {
        return constructor.apply(color);
    }

    public static Type of(final String piece) {
        return Stream.of(Type.values())
                .filter(type -> type.name().equalsIgnoreCase(piece))
                .findFirst()
                .orElseThrow(() -> new InvalidPieceException(INVALID_PIECE));
    }
}
