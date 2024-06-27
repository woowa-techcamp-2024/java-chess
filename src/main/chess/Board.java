package chess;

import chess.piece.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Board {

    public static final int LENGTH = 8;

    private final Cell[][] cells;

    public Board() {
        this.cells = new Cell[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
    }

    public void initialize() {
        clear();
        set(Position.of("a1"), Rook.createWhite());
        set(Position.of("b1"), Knight.createWhite());
        set(Position.of("c1"), Bishop.createWhite());
        set(Position.of("d1"), Queen.createWhite());
        set(Position.of("e1"), King.createWhite());
        set(Position.of("f1"), Bishop.createWhite());
        set(Position.of("g1"), Knight.createWhite());
        set(Position.of("h1"), Rook.createWhite());
        for (char file = 'a'; file <= 'h'; file++) {
            set(Position.of(file, 2), Pawn.createWhite());
        }
        for (char file = 'a'; file <= 'h'; file++) {
            set(Position.of(file, 7), Pawn.createBlack());
        }
        set(Position.of("a8"), Rook.createBlack());
        set(Position.of("b8"), Knight.createBlack());
        set(Position.of("c8"), Bishop.createBlack());
        set(Position.of("d8"), Queen.createBlack());
        set(Position.of("e8"), King.createBlack());
        set(Position.of("f8"), Bishop.createBlack());
        set(Position.of("g8"), Knight.createBlack());
        set(Position.of("h8"), Rook.createBlack());
    }

    public Piece get(Position position) {
        return cellAt(position).getPiece();
    }

    public void set(Position position, Piece piece) {
        cellAt(position).setPiece(piece);
    }

    public void clear() {
        stream().forEach(cell -> cell.clear());
    }

    public void move(Position from, Position to) {
        Cell fromCell = cellAt(from);
        if (fromCell.isEmpty()) {
            throw new IllegalArgumentException("Cell is empty");
        }
        Cell toCell = cellAt(to);
        toCell.setPiece(fromCell.getPiece());
        fromCell.clear();
    }

    public Cell cellAt(Position position) {
        return cellAt(position.rankIndex, position.fileIndex);
    }

    private Cell cellAt(int r, int c) {
        return cells[r][c];
    }

    public int countPiece(Class<? extends Piece> type, Piece.Color color) {
        return (int) pieceStream().filter(piece -> piece.isColor(color) && type.isInstance(piece))
                .count();
    }

    public double value(Piece.Color color) {
        double value = 0.0;
        for (int c = 0; c < LENGTH; c++) {
            int pawnCount = 0;
            for (int r = 0; r < LENGTH; r++) {
                Cell cell = cellAt(r, c);
                if (cell.isEmpty()) continue;
                Piece piece = cell.getPiece();
                if (!piece.isColor(color)) continue;

                if (piece instanceof Pawn) pawnCount++;
                else value += piece.value();
            }
            double pawnValue = pawnCount > 1 ? Pawn.MULTIPLE_IN_FILE_VALUE : Pawn.VALUE;
            value += pawnCount * pawnValue;
        }
        return value;
    }

    public List<Piece> getPiecesInDescendingOrder(Piece.Color color) {
        return pieceStream().filter(piece -> piece.isColor(color))
                .sorted(Comparator.comparingDouble(Piece::value).reversed())
                .toList();
    }

    public List<Pawn> findPawns() {
        return pieceStream().filter(piece -> piece instanceof Pawn)
                .map(Pawn.class::cast)
                .toList();
    }

    protected Stream<Cell> stream() {
        return Arrays.stream(cells).flatMap(Arrays::stream);
    }

    protected Stream<Piece> pieceStream() {
        return stream().filter(cell -> !cell.isEmpty())
                .map(cell -> cell.getPiece());
    }

    public static class Cell {

        private final Position position;
        private Piece piece;

        public Cell(int r, int c) {
            this.position = Position.of(r, c);
        }

        public boolean isEmpty() {
            return piece == null;
        }

        public Piece getPiece() {
            return piece;
        }

        private void setPiece(Piece piece) {
            this.piece = piece;
        }

        private void clear() {
            piece = null;
        }

        @Override
        public String toString() {
            return isEmpty() ? "." : getPiece().toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = LENGTH - 1; r >= 0; r--) {
            for (int c = 0; c < LENGTH; c++) {
                sb.append(cellAt(Position.of(r, c)));
            }
            sb.append(StringUtils.NEWLINE);
        }
        return sb.toString();
    }
}
