package com.woowatechcamp.chess;

import com.woowatechcamp.chess.pieces.Piece;

import java.util.List;
import static com.woowatechcamp.utils.StringUtils.appendNewLine;

public class Rank {
    private final List<Piece> pieces;

    Rank(Piece... piece) {
        this.pieces = List.of(piece);
    }

    public static Rank createBlankRank() {
        return new Rank(Piece.createBlank(), Piece.createBlank(), Piece.createBlank(), Piece.createBlank(),
                Piece.createBlank(), Piece.createBlank(), Piece.createBlank(), Piece.createBlank());
    }

    public int getPieceCount() {
        return (int) pieces.stream()
                .filter(Piece::isNotBlank)
                .count();
    }

    public int getPieceCount(Piece.Type type, Piece.Color color) {
        return (int) pieces.stream()
                .filter(piece -> piece.isSameTypeAndColor(type, color))
                .count();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Piece piece : pieces) {
            result.append(piece.toString());
        }
        return appendNewLine(result.toString());
    }
}
