package org.example.chess.board;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Piece;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

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
