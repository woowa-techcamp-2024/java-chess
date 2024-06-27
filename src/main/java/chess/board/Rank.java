package chess.board;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rank {

    private int size;

    private List<Piece> pieces;

    public Rank(int size, List<Piece> rank) {
        if (size == 0 || rank.size() != size) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.pieces = rank;
    }

    public Piece findPiece(int fileNum) {
        if (fileNum < 0 || fileNum >= size) {
            throw new IllegalArgumentException();
        }
        return pieces.get(fileNum);
    }

    public StringBuilder show() {
        StringBuilder sb = new StringBuilder();
        pieces.forEach(piece -> sb.append(piece.getRepresentation()));
        return sb;
    }

    public void set(int fileNum, Piece piece) {
        if (fileNum >= size || piece == null) {
            throw new IllegalArgumentException();
        }
        pieces.set(fileNum, piece);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public static Rank getBlankRank(int size) {
        List<Piece> pieces = new ArrayList<>();
        for (int count = 0; count < size; count++) {
            pieces.add(Piece.createBlank());
        }
        return new Rank(size, pieces);
    }

    public int count(Type type, Color color) {
        return (int) pieces.stream()
                .filter(piece -> piece.hasType(type))
                .filter(piece -> piece.hasColor(color))
                .count();
    }
}
