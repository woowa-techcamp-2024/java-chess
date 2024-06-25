package chess;

import chess.piece.Pawn;
import chess.piece.Piece;

import java.util.Arrays;
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

    Piece get(int r, int c) {
        return cellAt(r, c).getPiece();
    }

    void set(int r, int c, Piece piece) {
        cellAt(r, c).setPiece(piece);
    }

    protected Cell cellAt(int r, int c) {
        return cells[r][c];
    }

    public List<Pawn> findPawns() {
        return stream().filter(cell -> !cell.isEmpty())
                .map(cell -> cell.piece)
                .filter(piece -> piece instanceof Pawn)
                .map(Pawn.class::cast)
                .toList();
    }

    protected Stream<Cell> stream() {
        return Arrays.stream(cells).flatMap(Arrays::stream);
    }

    public static class Cell {

        final int r, c;
        private Piece piece;

        public Cell(int r, int c) {
            this.r = r;
            this.c = c;
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
                sb.append(cellAt(r, c));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
