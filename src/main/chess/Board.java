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
        set("a1", Piece.createWhite(Piece.Type.ROOK));
        set("b1", Piece.createWhite(Piece.Type.KNIGHT));
        set("c1", Piece.createWhite(Piece.Type.BISHOP));
        set("d1", Piece.createWhite(Piece.Type.QUEEN));
        set("e1", Piece.createWhite(Piece.Type.KING));
        set("f1", Piece.createWhite(Piece.Type.BISHOP));
        set("g1", Piece.createWhite(Piece.Type.KNIGHT));
        set("h1", Piece.createWhite(Piece.Type.ROOK));
        for (char c = 'a'; c <= 'h'; c++) {
            set(c, 2, Piece.createWhite(Piece.Type.PAWN));
        }
        for (char c = 'a'; c <= 'h'; c++) {
            set(c, 7, Piece.createBlack(Piece.Type.PAWN));
        }
        set("a8", Piece.createBlack(Piece.Type.ROOK));
        set("b8", Piece.createBlack(Piece.Type.KNIGHT));
        set("c8", Piece.createBlack(Piece.Type.BISHOP));
        set("d8", Piece.createBlack(Piece.Type.QUEEN));
        set("e8", Piece.createBlack(Piece.Type.KING));
        set("f8", Piece.createBlack(Piece.Type.BISHOP));
        set("g8", Piece.createBlack(Piece.Type.KNIGHT));
        set("h8", Piece.createBlack(Piece.Type.ROOK));
    }

    public Piece get(String fileRank) {
        return cellAt(Position.of(fileRank)).getPiece();
    }

    public void set(String fileRank, Piece piece) {
        cellAt(Position.of(fileRank)).setPiece(piece);
    }

    protected void set(char rank, int file, Piece piece) {
        cellAt(Position.of(rank, file)).setPiece(piece);
    }

    protected void clear() {
        stream().forEach(cell -> cell.clear());
    }

    protected Cell cellAt(Position position) {
        return cells[position.r][position.c];
    }

    public int countPiece(Piece.Color color, Piece.Type type) {
        return (int) stream().filter(cell -> !cell.isEmpty())
                .map(cell -> cell.getPiece())
                .filter(piece -> piece.isColor(color) && piece.isType(type))
                .count();
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
