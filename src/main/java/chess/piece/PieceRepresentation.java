package chess.piece;

import java.util.Arrays;

public enum PieceRepresentation {
    PAWN_WHITE(Type.PAWN, PieceColor.WHITE, '♙'), PAWN_BLACK(Type.PAWN, PieceColor.BLACK, '♟'),
    KING_WHITE(Type.KING, PieceColor.WHITE, '♔'), KING_BLACK(Type.KING, PieceColor.BLACK, '♚'),
    ROOK_WHITE(Type.ROOK, PieceColor.WHITE, '♖'), ROOK_BLACK(Type.ROOK, PieceColor.BLACK, '♜'),
    BISHOP_WHITE(Type.BISHOP, PieceColor.WHITE, '♗'), BISHOP_BLACK(Type.BISHOP, PieceColor.BLACK, '♝'),
    KNIGHT_WHITE(Type.KNIGHT, PieceColor.WHITE, '♘'), KNIGHT_BLACK(Type.KNIGHT, PieceColor.BLACK, '♞'),
    QUEEN_WHITE(Type.QUEEN, PieceColor.WHITE, '♕'), QUEEN_BLACK(Type.QUEEN, PieceColor.BLACK, '♛'),
    ;

    private final Type type;
    private final PieceColor pieceColor;
    private final char representation;

    PieceRepresentation(final Type type, final PieceColor pieceColor, final char representation) {
        this.type = type;
        this.pieceColor = pieceColor;
        this.representation = representation;
    }

    public static char getPieceRepresentation(final PieceColor color, final Type type) {
        return Arrays.stream(PieceRepresentation.values())
                .filter(pieceRepresentation -> pieceRepresentation.type.equals(type))
                .filter(pieceRepresentation -> pieceRepresentation.pieceColor.equals(color))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("기물이 잘못 입력되었습니다."))
                .representation;
    }

    public char getPieceRepresentation() {
        return this.representation;
    }
}
