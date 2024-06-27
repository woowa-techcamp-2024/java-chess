package wootecamp.chess.board;

import wootecamp.chess.pieces.Piece;
import wootecamp.chess.pieces.PieceFactory;

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
            rank.addPiece(PieceFactory.createEmptyPiece());
        }
        return rank;
    }

    public static Rank createInitialRank1() {
        Rank rank = new Rank();
        rank.addPiece(PieceFactory.createWhiteRook());
        rank.addPiece(PieceFactory.createWhiteKnight());
        rank.addPiece(PieceFactory.createWhiteBishop());
        rank.addPiece(PieceFactory.createWhiteQueen());
        rank.addPiece(PieceFactory.createWhiteKing());
        rank.addPiece(PieceFactory.createWhiteBishop());
        rank.addPiece(PieceFactory.createWhiteKnight());
        rank.addPiece(PieceFactory.createWhiteRook());
        return rank;
    }

    public static Rank createInitialRank2() {
        Rank rank = new Rank();
        for (int i = 0; i < RANK_SIZE; i++) {
            rank.addPiece(PieceFactory.createWhitePawn());
        }
        return rank;
    }

    public static Rank createInitialRank7() {
        Rank rank = new Rank();
        for (int i = 0; i < RANK_SIZE; i++) {
            rank.addPiece(PieceFactory.createBlackPawn());
        }
        return rank;
    }

    public static Rank createInitialRank8() {
        Rank rank = new Rank();
        rank.addPiece(PieceFactory.createBlackRook());
        rank.addPiece(PieceFactory.createBlackKnight());
        rank.addPiece(PieceFactory.createBlackBishop());
        rank.addPiece(PieceFactory.createBlackQueen());
        rank.addPiece(PieceFactory.createBlackKing());
        rank.addPiece(PieceFactory.createBlackBishop());
        rank.addPiece(PieceFactory.createBlackKnight());
        rank.addPiece(PieceFactory.createBlackRook());
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

    public boolean hasKing(final Piece.Color color) {
        for (Piece piece : pieces) {
            if(piece.isKing(color)) {
                return true;
            }
        }
        return false;
    }

    public List<Piece> collectPieces(final Piece.Color color) {
        final List<Piece> pieces = new ArrayList<>();
        for (Piece piece : this.pieces) {
            if(!piece.isEmptyPiece() && piece.getColor() == color) {
                pieces.add(piece);
            }
        }
        return pieces;
    }
}
