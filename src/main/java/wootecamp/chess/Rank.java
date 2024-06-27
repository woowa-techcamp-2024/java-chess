package wootecamp.chess;

import wootecamp.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    public static final int RANK_SIZE = 8;

    private final List<Piece> pieces = new ArrayList<>();

    private Rank() {
    }

    public static Rank createEmptyRank() {
        Rank rank = new Rank();
        for (int i = 0; i < RANK_SIZE; i++) {
            rank.addPiece(Piece.getEmptyPiece());
        }
        return rank;
    }

    public static Rank createInitialRank1() {
        Rank rank = new Rank();
        rank.addPiece(Piece.createWhiteRook());
        rank.addPiece(Piece.createWhiteKnight());
        rank.addPiece(Piece.createWhiteBishop());
        rank.addPiece(Piece.createWhiteQueen());
        rank.addPiece(Piece.createWhiteKing());
        rank.addPiece(Piece.createWhiteBishop());
        rank.addPiece(Piece.createWhiteKnight());
        rank.addPiece(Piece.createWhiteRook());
        return rank;
    }

    public static Rank createInitialRank2() {
        Rank rank = new Rank();
        for (int i = 0; i < RANK_SIZE; i++) {
            rank.addPiece(Piece.createWhitePawn());
        }
        return rank;
    }

    public static Rank createInitialRank7() {
        Rank rank = new Rank();
        for (int i = 0; i < RANK_SIZE; i++) {
            rank.addPiece(Piece.createBlackPawn());
        }
        return rank;
    }

    public static Rank createInitialRank8() {
        Rank rank = new Rank();
        rank.addPiece(Piece.createBlackRook());
        rank.addPiece(Piece.createBlackKnight());
        rank.addPiece(Piece.createBlackBishop());
        rank.addPiece(Piece.createBlackQueen());
        rank.addPiece(Piece.createBlackKing());
        rank.addPiece(Piece.createBlackBishop());
        rank.addPiece(Piece.createBlackKnight());
        rank.addPiece(Piece.createBlackRook());
        return rank;
    }

    private void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public int pieceCount() {
        int pieceCount = 0;
        for (Piece piece : pieces) {
            if(!piece.isEmptyPiece()) {
                pieceCount++;
            }
        }

        return pieceCount;
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        int pieceCount = 0;
        for (Piece piece : pieces) {
            if(piece.isSamePiece(color, type)) {
                pieceCount++;
            }
        }
        return pieceCount;
    }

    public String showRank() {
        StringBuilder builder = new StringBuilder();
        for (Piece piece : pieces) {
            builder.append(piece.getRepresentation());
        }
        return builder.toString();
    }

    public Piece findPiece(int fileIndex) {
        return pieces.get(fileIndex);
    }

    public void setPiece(int filePosition, Piece piece) {
        pieces.set(filePosition, piece);
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0;
        for (Piece piece : pieces) {
            if(piece.getColor() == color) {
                point += piece.getType().getPoint();
            }
        }

        return point;
    }

    public boolean hasKing(Piece.Color color) {
        for (Piece piece : pieces) {
            if(piece.isKing(color)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPawn(Piece.Color color, int filePosition) {
        return pieces.get(filePosition).isPawn(color);
    }
}
