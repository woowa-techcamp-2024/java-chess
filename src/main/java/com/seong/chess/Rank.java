package com.seong.chess;

import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public Rank() {
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public int pieceCount() {
        return (int) pieces.stream()
                .filter(Piece::isNotBlank)
                .count();
    }

    public int pieceCount(Type type, Color color) {
        return (int) pieces.stream()
                .filter(piece -> piece.isEqual(type, color))
                .count();
    }

    public Piece get(int index) {
        return pieces.get(index);
    }

    public void set(int col, Piece piece) {
        pieces.set(col, piece);
    }

    public List<Piece> getSameColorPieces(Color color) {
        return pieces.stream()
                .filter(piece -> piece.isEqual(color))
                .toList();
    }
}
