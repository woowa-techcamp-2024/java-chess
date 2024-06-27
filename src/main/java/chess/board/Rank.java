package chess.board;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Rank {

    private int size;

    private List<Piece> pieces;

    public Rank(int size, List<Piece> rank) {
        if (size == 0 || rank == null || rank.size() != size) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.pieces = rank;
    }

    public static Rank createRank(int size, Supplier<Piece> supplier) {
        List<Piece> pieces = new ArrayList<>();
        for (int count = 0; count < size; count++) {
            pieces.add(supplier.get());
        }
        return new Rank(size, pieces);
    }

    public static Rank createWhitePawnRank(int size) {
        return createRank(size, PieceFactory::createWhitePawn);
    }

    public static Rank createBlackPawnRank(int size) {
        return createRank(size, PieceFactory::createBlackPawn);
    }

    public static Rank createBlankRank(int size) {
        return createRank(size, PieceFactory::createBlankPiece);
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

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public List<Piece> getPieces(Color color) {
        return pieces.stream()
                .filter(p -> p.hasColor(color))
                .toList();
    }

    public int count(Type type, Color color) {
        return (int) pieces.stream()
                .filter(piece -> piece.hasType(type))
                .filter(piece -> piece.hasColor(color))
                .count();
    }

    public void set(int fileNum, Piece piece) {
        if (fileNum >= size || piece == null) {
            throw new IllegalArgumentException();
        }
        pieces.set(fileNum, piece);
    }
}
