package com.wootecam.chess.board;

import com.wootecam.chess.common.Order;
import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import com.wootecam.chess.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public Piece get(Position pos) {
        return ranks[pos.x].get(pos.y);
    }

    public int size() {
        return totalPieces;
    }

    public String print() {
        return Arrays.stream(ranks)
                .map(Rank::print)
                .collect(Collectors.joining(StringUtils.NEW_LINE, "", StringUtils.NEW_LINE));
    }

    public int countPiece(PieceType type, Color color) {
        return Arrays.stream(ranks)
                .mapToInt(r -> r.countPiece(type, color))
                .sum();
    }

    public double calculateScore(Color color) {
        double score = Arrays.stream(ranks)
                .mapToDouble(r -> r.calculateScoreExceptPawn(color))
                .sum();
        score += calculatePawnScore(color);

        return score;
    }

    private double calculatePawnScore(Color color) {
        double score = 0;

        for (int col = 0; col < MAX_COL; ++col) {
            int count = countPawnsInColumn(color, col);

            if (count == 1) {
                score += PieceType.PAWN.point;
            } else if (count >= 2) {
                score += PieceType.PAWN.point * count / 2;
            }
        }

        return score;
    }

    private int countPawnsInColumn(Color color, int col) {
        return (int) Arrays.stream(ranks)
                .map(r -> r.get(col))
                .filter(p -> p.isColor(color) && p.isPawn())
                .count();
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

    private static class Rank {
        private final Piece[] squares;

        public Rank() {
            this.squares = new Piece[MAX_COL];
            Arrays.fill(squares, Piece.createBlank());
        }

        public void place(Piece piece, int index) {
            checkValidIndex(index);
            checkEmptySquare(index);

            squares[index] = piece;
        }

        private void checkValidIndex(int index) {
            if (index < 0 || index >= MAX_COL) {
                throw new IllegalArgumentException("The index is invalid: " + index);
            }
        }

        private void checkEmptySquare(int index) {
            if (squares[index].isPiece()) {
                throw new IllegalArgumentException("The square is already occupied");
            }
        }

        public String print() {
            return Arrays.stream(squares)
                    .map(Piece::getRepresentation)
                    .map(r -> r.value)
                    .collect(Collectors.joining());
        }

        public Piece get(int index) {
            checkValidIndex(index);

            return squares[index];
        }

        public int countPiece(PieceType type, Color color) {
            return (int) Arrays.stream(squares)
                    .filter(piece -> piece.hasTypeAndColor(type, color))
                    .count();
        }

        public double calculateScoreExceptPawn(Color color) {
            return Arrays.stream(squares)
                    .filter(p -> p.isColor(color) && !p.isPawn())
                    .mapToDouble(p -> p.getType().point)
                    .sum();
        }

        public List<Piece> getPieces(Color color) {
            return Arrays.stream(squares)
                    .filter(p -> p.isColor(color))
                    .toList();
        }
    }
}
