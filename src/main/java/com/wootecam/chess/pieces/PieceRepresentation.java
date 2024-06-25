package com.wootecam.chess.pieces;

import static com.wootecam.chess.pieces.PieceType.BISHOP;
import static com.wootecam.chess.pieces.PieceType.KING;
import static com.wootecam.chess.pieces.PieceType.KNIGHT;
import static com.wootecam.chess.pieces.PieceType.PAWN;
import static com.wootecam.chess.pieces.PieceType.QUEEN;
import static com.wootecam.chess.pieces.PieceType.ROOK;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum PieceRepresentation {
    BLACK_PAWN(PAWN, Color.BLACK, "P"),
    BLACK_KNIGHT(KNIGHT, Color.BLACK, "K"),
    BLACK_ROOK(ROOK, Color.BLACK, "R"),
    BLACK_BISHOP(BISHOP, Color.BLACK, "B"),
    BLACK_QUEEN(QUEEN, Color.BLACK, "Q"),
    BLACK_KING(KING, Color.BLACK, "K"),
    WHITE_PAWN(PAWN, Color.WHITE, "p"),
    WHITE_KNIGHT(KNIGHT, Color.WHITE, "k"),
    WHITE_ROOK(ROOK, Color.WHITE, "r"),
    WHITE_BISHOP(BISHOP, Color.WHITE, "b"),
    WHITE_QUEEN(QUEEN, Color.WHITE, "q"),
    WHITE_KING(KING, Color.WHITE, "k"),
    NONE(null, null, ".");

    private static final Map<PieceAttributes, PieceRepresentation> CONVERTOR = Arrays.stream(values())
            .collect(Collectors.toMap(
                    pieceRepresentation -> new PieceAttributes(pieceRepresentation.type, pieceRepresentation.color),
                    pieceRepresentation -> pieceRepresentation));
    private final PieceType type;
    private final Color color;
    public final String value;

    PieceRepresentation(PieceType type, Color color, String value) {
        this.value = value;
        this.color = color;
        this.type = type;
    }

    public static PieceRepresentation findByTypeAndColor(PieceType type, Color color) {
        return CONVERTOR.getOrDefault(new PieceAttributes(type, color), NONE);
    }

    private record PieceAttributes(PieceType type, Color color) {
    }
}
