package com.seong.chess;

import com.seong.chess.pieces.Bishop;
import com.seong.chess.pieces.Blank;
import com.seong.chess.pieces.King;
import com.seong.chess.pieces.Knight;
import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;
import com.seong.chess.pieces.Queen;
import com.seong.chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    private Rank() {
    }

    public static Rank createBlackRank() {
        Rank rank = new Rank();
        rank.initializeBlank();
        return rank;
    }

    private void initializeBlank() {
        for (int i = 0; i < Board.BOARD_LENGTH; i++) {
            pieces.add(Blank.create());
        }
    }

    public static Rank createWhitePawnRank() {
        Rank rank = new Rank();
        rank.initializeWhitePawn();
        return rank;
    }

    private void initializeWhitePawn() {
        for (int i = 0; i < Board.BOARD_LENGTH; i++) {
            pieces.add(Pawn.createWhite());
        }
    }

    public static Rank createBlackPawnRank() {
        Rank rank = new Rank();
        rank.initializeBlackPawn();
        return rank;
    }

    private void initializeBlackPawn() {
        for (int i = 0; i < Board.BOARD_LENGTH; i++) {
            pieces.add(Pawn.createBlack());
        }
    }

    public static Rank createWhitePiecesRank() {
        Rank rank = new Rank();
        rank.initializeWhitePieces();
        return rank;
    }

    private void initializeWhitePieces() {
        pieces.add(Rook.createWhite());
        pieces.add(Knight.createWhite());
        pieces.add(Bishop.createWhite());
        pieces.add(Queen.createWhite());
        pieces.add(King.createWhite());
        pieces.add(Bishop.createWhite());
        pieces.add(Knight.createWhite());
        pieces.add(Rook.createWhite());
    }

    public static Rank createBlackPiecesRank() {
        Rank rank = new Rank();
        rank.initializeBlackPieces();
        return rank;
    }

    private void initializeBlackPieces() {
        pieces.add(Rook.createBlack());
        pieces.add(Knight.createBlack());
        pieces.add(Bishop.createBlack());
        pieces.add(Queen.createBlack());
        pieces.add(King.createBlack());
        pieces.add(Bishop.createBlack());
        pieces.add(Knight.createBlack());
        pieces.add(Rook.createBlack());
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

    public void move(int col, Piece piece) {
        pieces.set(col, piece);
    }

    public List<Piece> getSameColorPieces(Color color) {
        return pieces.stream()
                .filter(piece -> piece.isEqual(color))
                .toList();
    }
}
