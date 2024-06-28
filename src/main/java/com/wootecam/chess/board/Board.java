package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.property.Color;
import com.wootecam.chess.pieces.property.PieceType;
import java.util.Arrays;
import java.util.List;

public class Board {
    public static final int MAX_COL = 8;
    public static final int MAX_ROW = 8;

    private final Rank[] ranks;

    private int totalPieces;

    public Board() {
        this.ranks = new Rank[MAX_ROW];
        for (int i = 0; i < MAX_ROW; i++) {
            this.ranks[i] = new Rank();
        }
    }

    public void add(Piece piece, Position pos) {
        ranks[pos.x].place(piece, pos.y);

        if (piece.isPiece()) {
            ++totalPieces;
        }
    }

    public void move(Position source, Position target) {
        Piece piece = get(source);
        validPiece(source, piece);

        ranks[target.x].place(piece, target.y);
        ranks[source.x].clearSquare(source.y);
    }

    private void validPiece(Position source, Piece piece) {
        if (!piece.isPiece()) {
            throw new IllegalArgumentException("No piece found at the source position: " + source);
        }
    }

    public Piece get(Position pos) {
        return ranks[pos.x].get(pos.y);
    }

    public int size() {
        return totalPieces;
    }

    public boolean isAllyPieceAt(Position pos, Piece piece) {
        if (isEmpty(pos)) {
            return false;
        }

        Piece posPiece = ranks[pos.x].get(pos.y);
        return posPiece.isAlly(piece);
    }

    public boolean isEmpty(Position pos) {
        return !ranks[pos.x].get(pos.y).isPiece();
    }

    public List<List<Piece>> getCurrentState() {
        return Arrays.stream(ranks)
                .map(Rank::getPieces)
                .toList();
    }

    public int countPiece(PieceType type, Color color) {
        return Arrays.stream(ranks)
                .mapToInt(r -> r.countPiece(type, color))
                .sum();
    }

    public double calculateScore(ScoreCalculationRule calculationRule, Color color) {
        return calculationRule.apply(ranks, color);
    }

    public List<Piece> getPiecesSortedByScore(Color color, Order order) {
        return Arrays.stream(ranks)
                .flatMap(r -> r.getPieces(color).stream())
                .sorted((p1, p2) -> {
                    int compare = Double.compare(p1.getType().point, p2.getType().point);
                    return order.isAsc() ? compare : -compare;
                })
                .toList();
    }
}
