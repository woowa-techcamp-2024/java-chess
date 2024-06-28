package chess;

import pieces.Blank;
import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private final List<Piece> pieces;

    public Rank(int y) {
        this.pieces = new ArrayList<>();
        initBlank(y);
    }

    private void initBlank(int y) {
        char start = 'a';
        // a8, b8, ...
        for (int i = 0; i < BoardArea.X.getMax(); i++) {
            char x = (char) (start + i);
            Position position = new Position(x + Integer.toString(y));
            pieces.add(new Blank(PieceColor.NO_COLOR, PieceType.NO_PIECE, position));
        }
    }

    // get
    public long getNumOfPieces() {
        return pieces.stream()
                .filter(p -> !p.getType().equals(PieceType.NO_PIECE))
                .count();
    }

    public long countPiece(PieceColor color, PieceType type) {
        return pieces.stream()
                .filter(p -> p.getColor().equals(color) && p.getType().equals(type))
                .count();
    }

    public Piece getPiece(int idx) {
        return pieces.get(idx);
    }

    public boolean isPawn(PieceColor color, int colIdx) {
        Piece piece = pieces.get(colIdx);
        return piece.getType().equals(PieceType.PAWN) && piece.getColor().equals(color);
    }

    public List<Piece> getSpecificColorPieces(PieceColor color) {
        return pieces.stream()
                .filter(p -> p.getColor().equals(color))
                .toList();
    }

    // set
    public void setPiece(int idx, Piece piece) {
        pieces.set(idx, piece);
    }

    // util
    public double calculatePoint(PieceColor color) {
        return pieces.stream()
                .filter(p -> p.getColor().equals(color) && !p.getType().equals(PieceType.PAWN))
                .mapToDouble(p -> p.getType().getPoint())
                .sum();
    }

    // print
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieces) {
            sb.append(piece.getRepresent());
        }
        return sb.toString();
    }
}
