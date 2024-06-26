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
    private static final int MAX_ROW = 8;
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

    public void add(Piece piece) {
        if (totalPieces >= TOTAL_CELLS) {
            throw new IllegalArgumentException("Cannot add anymore pieces");
        }
        add(piece, totalPieces);
    }

    public void add(Piece piece, int index) {
        int row = index / MAX_COL;
        int col = index % MAX_COL;
        checkValidIndex(row);

        ranks[row].place(piece, col);

        if (piece.isPiece()) {
            ++totalPieces;
        }
    }

    private void checkValidIndex(int row) {
        if (row < 0 || row >= MAX_ROW) {
            throw new IllegalArgumentException("The row index is invalid: " + row);
        }
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

    public Piece get(String position) {
        checkValidPositionForm(position);

        int row = MAX_ROW - (position.charAt(1) - '0');
        int col = position.charAt(0) - 'a';
        return ranks[row].get(col);
    }

    private void checkValidPositionForm(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        char row = position.charAt(1);
        if (row < '1' || row > '8') {

            throw new IllegalArgumentException("Invalid position: " + position);
        }

        char col = position.charAt(0);
        if (col < 'a' || col > 'h') {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    public int size() {
        return totalPieces;
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

        public int countPiece(PieceType type, Color color) {
            return (int) Arrays.stream(squares)
                    .filter(piece -> piece.hasTypeAndColor(type, color))
                    .count();
        }

        public Piece get(int index) {
            checkValidIndex(index);

            return squares[index];
        }

        public String print() {
            return Arrays.stream(squares)
                    .map(Piece::getRepresentation)
                    .map(r -> r.value)
                    .collect(Collectors.joining());
        }
    }
}
