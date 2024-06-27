package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public enum Type {WHITE_PAWN, BLACK_PAWN, WHITE_ROOK_TO_KING, BLACK_ROOK_TO_KING, NO_PIECE;}

    public List<Piece> getPieces() {
        return pieces;
    }

    public Rank(Type type) {
        if (type == Type.WHITE_PAWN) {
            for (int i = 0; i < 8; i++) {
                pieces.add(Piece.createWhitePawn());
            }
        } else if (type == Type.BLACK_PAWN) {
            for (int i = 0; i < 8; i++) {
                pieces.add(Piece.createBlackPawn());
            }
        } else if (type == Type.WHITE_ROOK_TO_KING) {
            pieces.add(Piece.createWhiteRook());
            pieces.add(Piece.createWhiteKnight());
            pieces.add(Piece.createWhiteBishop());
            pieces.add(Piece.createWhiteQueen());
            pieces.add(Piece.createWhiteKing());
            pieces.add(Piece.createWhiteBishop());
            pieces.add(Piece.createWhiteKnight());
            pieces.add(Piece.createWhiteRook());
        } else if (type == Type.BLACK_ROOK_TO_KING) {
            pieces.add(Piece.createBlackRook());
            pieces.add(Piece.createBlackKnight());
            pieces.add(Piece.createBlackBishop());
            pieces.add(Piece.createBlackQueen());
            pieces.add(Piece.createBlackKing());
            pieces.add(Piece.createBlackBishop());
            pieces.add(Piece.createBlackKnight());
            pieces.add(Piece.createBlackRook());
        } else if (type == Type.NO_PIECE) {
            for (int i = 0; i < 8; i++) {
                pieces.add(Piece.createBlank());
            }
        }
    }


}

