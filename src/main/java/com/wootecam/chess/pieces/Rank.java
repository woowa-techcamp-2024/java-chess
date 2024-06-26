package com.wootecam.chess.pieces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rank {

    private static final int PIECE_COUNT = 8;
    private static final List<Type> CHESS_PIECE_ORDERS = List.of(
            Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN,
            Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK
    );

    private final List<Piece> pieces;

    public Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static Rank createPawns(Color color) {
        return new Rank(IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> new Piece(color, Type.PAWN))
                .toList());
    }

    public static Rank createBlackOtherPieces() {
        return new Rank(CHESS_PIECE_ORDERS.stream()
                .map(Piece::createBlack)
                .toList());
    }

    public static Rank createWhiteOtherPieces() {
        return new Rank(CHESS_PIECE_ORDERS.stream()
                .map(Piece::createWhite)
                .toList());
    }

    public static Rank createBlanks() {
        return new Rank(IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> Piece.createBlank())
                .toList());
    }

    public String createResults() {
        return pieces.stream()
                .map(Piece::getRepresentation)
                .collect(Collectors.joining());
    }

    public int countPiece() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isBlank())
                .count();
    }
}
