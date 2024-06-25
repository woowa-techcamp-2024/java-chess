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

    public void initialize() {
        clear();
        set(0, 0, Piece.createWhite(Piece.Type.ROOK));
        set(0, 1, Piece.createWhite(Piece.Type.KNIGHT));
        set(0, 2, Piece.createWhite(Piece.Type.BISHOP));
        set(0, 3, Piece.createWhite(Piece.Type.QUEEN));
        set(0, 4, Piece.createWhite(Piece.Type.KING));
        set(0, 5, Piece.createWhite(Piece.Type.BISHOP));
        set(0, 6, Piece.createWhite(Piece.Type.KNIGHT));
        set(0, 7, Piece.createWhite(Piece.Type.ROOK));
        for (int c = 0; c < LENGTH; c++) {
            set(1, c, Piece.createWhite(Piece.Type.PAWN));
        }
        for (int c = 0; c < LENGTH; c++) {
            set(6, c, Piece.createBlack(Piece.Type.PAWN));
        }
        set(7, 0, Piece.createBlack(Piece.Type.ROOK));
        set(7, 1, Piece.createBlack(Piece.Type.KNIGHT));
        set(7, 2, Piece.createBlack(Piece.Type.BISHOP));
        set(7, 3, Piece.createBlack(Piece.Type.QUEEN));
        set(7, 4, Piece.createBlack(Piece.Type.KING));
        set(7, 5, Piece.createBlack(Piece.Type.BISHOP));
        set(7, 6, Piece.createBlack(Piece.Type.KNIGHT));
        set(7, 7, Piece.createBlack(Piece.Type.ROOK));
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
            sb.append(StringUtils.NEWLINE);
        }
        return sb.toString();
    }
}
