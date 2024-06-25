package chess;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChessBoard {

    public static final int LENGTH = 8;

    private final Cell[][] cells;

    public ChessBoard() {
        this.cells = new Cell[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }
    }

    public void initialize() {
        clear();
        for (int c = 0; c < LENGTH; c++) {
            set(1, c, new Pawn(Color.WHITE));
        }
        for (int c = 0; c < LENGTH; c++) {
            set(6, c, new Pawn(Color.BLACK));
        }
    }

    Piece get(int r, int c) {
        return cellAt(r, c).getPiece();
    }

    void set(int r, int c, Piece piece) {
        cellAt(r, c).setPiece(piece);
    }

    void clear() {
        stream().forEach(cell -> cell.clear());
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
                sb.append(cellAt(r, c));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
