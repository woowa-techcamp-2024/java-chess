package chess;

import pieces.Piece;
import pieces.PieceColor;
import pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private final int NUM_COL = 8;
    private final List<Piece> pieces;

    public Rank() {
        this.pieces = new ArrayList<>();
        initBlank();
    }

    public Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    private void initBlank()
    {
        for(int i=0; i < NUM_COL; i++)
        {
            pieces.add(new Piece(PieceColor.NO_COLOR, PieceType.NO_PIECE));
        }
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(Piece piece : pieces){
            sb.append(piece.getRepresent());
        }
        return sb.toString();
    }

    public long getNumOfPieces()
    {
        return pieces.stream()
                .filter(p -> !p.type().equals(PieceType.NO_PIECE))
                .count();
    }

    public long countPiece(PieceColor color, PieceType type)
    {
        return pieces.stream()
                .filter(p -> p.color().equals(color) && p.type().equals(type))
                .count();
    }

    public Piece getPiece(int idx){
        return pieces.get(idx);
    }
    public void setPiece(int idx, Piece piece){
        pieces.set(idx, piece);
    }

    public boolean isPawn(PieceColor color, int colIdx){
        Piece piece = pieces.get(colIdx);
        return piece.type().equals(PieceType.PAWN) && piece.color().equals(color);
    }

    public double calculatePoint(PieceColor color){
        return pieces.stream()
                .filter(p -> p.color().equals(color) && !p.type().equals(PieceType.PAWN))
                .mapToDouble(p -> p.type().getPoint())
                .sum();
    }

    public List<Piece> getSpecificColorPieces(PieceColor color)
    {
        return pieces.stream()
                .filter(p -> p.color().equals(color))
                .toList();
    }
}
