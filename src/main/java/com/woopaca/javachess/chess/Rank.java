package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public String generateResult() {
        return pieces.stream()
                .map(piece -> piece == null ? "." : String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public int getPiecesCount() {
        return (int) pieces.stream()
                .filter(piece -> piece.getType() != Piece.Type.NO_PIECE)
                .count();
    }

    public int getPiecesCount(Piece.Color color, Piece.Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.getColor() == color && piece.getType() == type)
                .count();
    }
}
