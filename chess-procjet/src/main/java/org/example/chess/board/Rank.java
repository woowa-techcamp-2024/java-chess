package org.example.chess.board;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Piece;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void changePiece(int col, Piece piece) {
        pieces.set(col, piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public int countWhitePieces() {
        return (int) pieces.stream().filter(Piece::isWhite).count();
    }

    public int countBlackPieces() {
        return (int) pieces.stream().filter(Piece::isBlack).count();
    }
}
