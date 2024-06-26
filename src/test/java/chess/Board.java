package chess;

import chess.pieces.Piece;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Rank> map = new ArrayList<>();


    public static class Rank {
        private final List<Piece> pieces = new ArrayList<>();

        public enum Type { WHITE_PAWN, BLACK_PAWN, WHITE_ROOK_TO_KING, BLACK_ROOK_TO_KING, NO_PIECE;}
        public List<Piece> getPieces() {
            return pieces;
        }

        public Rank(Type type) {
            if (type == Type.WHITE_PAWN) {
                for (int i = 0; i < 8; i++) {
                    pieces.add(Piece.createWhitePawn());
                }
            }
            else if (type == Type.BLACK_PAWN) {
                for (int i = 0; i < 8; i++) {
                    pieces.add(Piece.createBlackPawn());
                }
            }
            else if (type == Type.WHITE_ROOK_TO_KING) {
                pieces.add(Piece.createWhiteRook());
                pieces.add(Piece.createWhiteKnight());
                pieces.add(Piece.createWhiteBishop());
                pieces.add(Piece.createWhiteQueen());
                pieces.add(Piece.createWhiteKing());
                pieces.add(Piece.createWhiteBishop());
                pieces.add(Piece.createWhiteKnight());
                pieces.add(Piece.createWhiteRook());
            }
            else if (type == Type.BLACK_ROOK_TO_KING) {
                pieces.add(Piece.createBlackRook());
                pieces.add(Piece.createBlackKnight());
                pieces.add(Piece.createBlackBishop());
                pieces.add(Piece.createBlackQueen());
                pieces.add(Piece.createBlackKing());
                pieces.add(Piece.createBlackBishop());
                pieces.add(Piece.createBlackKnight());
                pieces.add(Piece.createBlackRook());
            }
            else if (type == Type.NO_PIECE) {
                for (int i = 0; i < 8; i++) {
                    pieces.add(Piece.createBlank());
                }
            }
        }

    }
    public Board() {}

    public void initializeEmpty() {
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
        map.add(new Rank(Rank.Type.NO_PIECE));
    }

    public void initialize() {
        map.add(new Rank(Rank.Type.BLACK_ROOK_TO_KING));
        map.add(new Rank(Rank.Type.BLACK_PAWN));
        for (int i = 0; i < 4; i++) {
            map.add(new Rank(Rank.Type.NO_PIECE));
        }
        map.add(new Rank(Rank.Type.WHITE_PAWN));
        map.add(new Rank(Rank.Type.WHITE_ROOK_TO_KING));
    }

    public void move(String position, Piece piece) {
        Position pos = new Position(position);
        map.get(pos.getRow()).getPieces().set(pos.getCol(), piece);
    }

    public Piece findPiece(String position){
        Position pos = new Position(position);
        return map.get(pos.getRow()).getPieces().get(pos.getCol());
    }

    public int pieceCount(Piece.Color color, Piece.Type type) {
        int count = 0;
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getColor() == color && piece.getType() == type) count++;
            }
        }
        return count;
    }

    public int pieceCount() {
        int count = 0;
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getType() != Piece.Type.NO_PIECE) count++;
            }
        }
        return count;
    }

    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (Rank rank : map) {
            for (Piece piece : rank.getPieces()) {
                if (piece.getType() == Piece.Type.NO_PIECE) builder.append('.');
                else builder.append(piece.getRepresentation().getSymbol());
            }
            builder.append(StringUtils.appendNewLine(""));
        }
        return builder.toString();
    }
}
