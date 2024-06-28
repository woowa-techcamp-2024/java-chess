package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Bishop;
import com.wootecam.chess.pieces.Blank;
import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.King;
import com.wootecam.chess.pieces.Knight;
import com.wootecam.chess.pieces.Pawn;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Queen;
import com.wootecam.chess.pieces.Rook;
import com.wootecam.chess.pieces.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rank {

    public static final int PIECE_COUNT = 8;

    private final List<? extends Piece> pieces;

    public Rank(List<? extends Piece> pieces) {
        this.pieces = pieces;
    }

    public static Rank createPawns(Color color) {
        return new Rank(IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> new Pawn(color))
                .toList());
    }

    public static Rank createBlackOtherPieces() {
        return new Rank(
                List.of(new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK), new Queen(Color.BLACK),
                        new King(Color.BLACK), new Bishop(Color.BLACK), new Knight(Color.BLACK), new Rook(Color.BLACK))
        );
    }

    public static Rank createWhiteOtherPieces() {
        return new Rank(
                List.of(new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE),
                        new King(Color.WHITE), new Bishop(Color.WHITE), new Knight(Color.WHITE), new Rook(Color.WHITE))
        );
    }

    public static Rank createBlanks() {
        return new Rank(IntStream.range(0, PIECE_COUNT)
                .mapToObj(i -> new Blank())
                .toList());
    }

    public int countPieces() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isBlank())
                .count();
    }

    public int countSpecificPieces(Color color, Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.isSameColorAndType(color, type))
                .count();
    }

    public Piece findPieceByColumn(int columnIndex) {
        return pieces.get(columnIndex);
    }

    public Rank placePiece(int columnIndex, Piece piece) {
        List<Piece> newPieces = new ArrayList<>(pieces);
        newPieces.set(columnIndex, piece);

        return new Rank(Collections.unmodifiableList(newPieces));
    }

    public double calculateRankPiecesPoint(final Color color) {
        return pieces.stream()
                .filter(piece -> piece.isApplicablePiece(color))
                .mapToDouble(piece -> piece.getType().getPoint())
                .sum();
    }

    public String createResults() {
        return pieces.stream()
                .map(Piece::getRepresentation)
                .collect(Collectors.joining());
    }
}
