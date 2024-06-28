package com.wootecam.chess.board;

import static com.wootecam.chess.error.ErrorMessage.PIECE_CANNOT_FOUND;

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
        validPiece(piece);

        if (get(target).isPiece()) {
            --totalPieces;
        }

        ranks[target.x].place(piece, target.y);
        ranks[source.x].clearSquare(source.y);
    }

    private void validPiece(Piece piece) {
        if (!piece.isPiece()) {
            throw new IllegalArgumentException(PIECE_CANNOT_FOUND.value);
        }
    }

    public Piece get(Position pos) {
        return ranks[pos.x].get(pos.y);
    }

    public int size() {
        return totalPieces;
    }

    public boolean isAllyPieceAt(Position target, Piece piece) {
        if (isEmpty(target)) {
            return false;
        }

        Piece posPiece = get(target);
        return posPiece.isAlly(piece);
    }

    public boolean isEmpty(Position pos) {
        return !ranks[pos.x].get(pos.y).isPiece();
    }

    public List<List<Piece>> getState() {
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

    public List<Piece> sortByPoint(Color color, Order order) {
        return Arrays.stream(ranks)
                .flatMap(r -> r.getPieces(color).stream())
                .sorted(order::compare)
                .toList();
    }
}
