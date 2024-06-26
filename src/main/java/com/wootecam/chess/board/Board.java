package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import com.wootecam.chess.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    public static final int MAX_COL = 8;
    static final int MAX_ROW = 8;
    public static final int TOTAL_CELLS = MAX_ROW * MAX_COL;
    public static final int INITIAL_TOTAL_PIECES = 32;

    private final Rank[] ranks;
    private int totalPieces;

    public Board() {
        this.ranks = new Rank[MAX_ROW];
        for (int i = 0; i < MAX_ROW; i++) {
            this.ranks[i] = new Rank();
        }
    }

    public void initialize() {
        addBlackPieces();
        addWhitePieces();
        totalPieces = INITIAL_TOTAL_PIECES;
    }

    private void addBlackPieces() {
        final int blackPieceStartRank = 0;

        ranks[blackPieceStartRank].fill(List.of(
                Piece.createBlackRook(), Piece.createBlackKnight(), Piece.createBlackBishop(), Piece.createBlackQueen(),
                Piece.createBlackKing(), Piece.createBlackBishop(), Piece.createBlackKnight(),
                Piece.createBlackRook()));
        ranks[blackPieceStartRank + 1].fill(List.of(
                Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(),
                Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createBlackPawn()));
    }

    private void addWhitePieces() {
        final int whitePieceStartRank = MAX_ROW - 2;

        ranks[whitePieceStartRank].fill(List.of(
                Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(),
                Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn(), Piece.createWhitePawn()));
        ranks[whitePieceStartRank + 1].fill(List.of(
                Piece.createWhiteRook(), Piece.createWhiteKnight(), Piece.createWhiteBishop(), Piece.createWhiteQueen(),
                Piece.createWhiteKing(), Piece.createWhiteBishop(), Piece.createWhiteKnight(),
                Piece.createWhiteRook()));
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
        int count = 0;
        for (int row = 0; row < MAX_ROW; ++row) {
            Piece piece = ranks[row].get(col);
            if (piece.isColor(color) && piece.isPawn()) {
                ++count;
            }
        }
        return count;
    }

    private static class Rank {
        private final Piece[] squares;

        public Rank() {
            this.squares = new Piece[MAX_COL];
            Arrays.fill(squares, Piece.createBlank());
        }

        private static void checkPiecesLength(List<Piece> pieces) {
            if (pieces.size() > MAX_COL) {
                throw new IllegalArgumentException("pieces size is too large: " + pieces.size());
            }
        }

        public void fill(List<Piece> pieces) {
            checkPiecesLength(pieces);
            for (int i = 0; i < pieces.size(); i++) {
                squares[i] = pieces.get(i);
            }
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
                    .filter(p -> p.isPieceAndNotPawn() && p.isColor(color))
                    .mapToDouble(p -> p.getType().point)
                    .sum();
        }
    }
}
