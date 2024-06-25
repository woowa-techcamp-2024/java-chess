package chess.piece;

import java.util.Arrays;

public enum PieceRepresentation {
    PAWN_WHITE(Type.PAWN, PieceColor.WHITE, '♙'), PAWN_BLACK(Type.PAWN, PieceColor.BLACK, '♟');

    private final Type type;
    private final PieceColor pieceColor;
    private final char representation;

    PieceRepresentation(final Type type, final PieceColor pieceColor, final char representation) {
        this.type = type;
        this.pieceColor = pieceColor;
        this.representation = representation;
    }

    public static char getPieceRepresentation(final Pawn pawn) {
        return Arrays.stream(PieceRepresentation.values())
                .filter(pieceRepresentation -> pieceRepresentation.type.equals(pawn.getType()))
                .filter(pieceRepresentation -> pieceRepresentation.pieceColor.equals(pawn.getColor()))
                .findAny().get().representation;
    }

    public char getPieceRepresentation() {
        return this.representation;
    }
}
