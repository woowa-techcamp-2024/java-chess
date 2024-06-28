package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.PieceFactory;
import com.woopaca.javachess.pieces.Type;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int BOARD_SIZE = 8;
    public static final int PAWNS_COUNT = 8;
    public static final int BLACK_PAWNS_RANK = 1;
    public static final int WHITE_PAWNS_RANK = 6;
    public static final int BLACK_MAIN_RANK = 0;
    public static final int WHITE_MAIN_RANK = 7;

    private final List<Rank> ranks = new ArrayList<>();

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.add(new Rank());
        }
    }

    public void initialize() {
        addBlankRanks();
        addPawns();
        addBlackMainPieces();
        addWhiteMainPieces();
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = ranks.get(i);
            for (int j = 0; j < BOARD_SIZE; j++) {
                rank.addPiece(PieceFactory.createBlank());
            }
        }
    }

    private void addBlankRanks() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.get(2).addPiece(PieceFactory.createBlank());
            ranks.get(3).addPiece(PieceFactory.createBlank());
            ranks.get(4).addPiece(PieceFactory.createBlank());
            ranks.get(5).addPiece(PieceFactory.createBlank());
        }
    }

    private void addPawns() {
        for (int i = 0; i < PAWNS_COUNT; i++) {
            ranks.get(BLACK_PAWNS_RANK).addPiece(PieceFactory.createBlackPawn());
            ranks.get(WHITE_PAWNS_RANK).addPiece(PieceFactory.createWhitePawn());
        }
    }

    private void addBlackMainPieces() {
        Rank blackMainRank = ranks.get(BLACK_MAIN_RANK);
        blackMainRank.addPiece(PieceFactory.createBlackRook());
        blackMainRank.addPiece(PieceFactory.createBlackKnight());
        blackMainRank.addPiece(PieceFactory.createBlackBishop());
        blackMainRank.addPiece(PieceFactory.createBlackQueen());
        blackMainRank.addPiece(PieceFactory.createBlackKing());
        blackMainRank.addPiece(PieceFactory.createBlackBishop());
        blackMainRank.addPiece(PieceFactory.createBlackKnight());
        blackMainRank.addPiece(PieceFactory.createBlackRook());
    }

    private void addWhiteMainPieces() {
        Rank whiteMainRank = ranks.get(WHITE_MAIN_RANK);
        whiteMainRank.addPiece(PieceFactory.createWhiteRook());
        whiteMainRank.addPiece(PieceFactory.createWhiteKnight());
        whiteMainRank.addPiece(PieceFactory.createWhiteBishop());
        whiteMainRank.addPiece(PieceFactory.createWhiteQueen());
        whiteMainRank.addPiece(PieceFactory.createWhiteKing());
        whiteMainRank.addPiece(PieceFactory.createWhiteBishop());
        whiteMainRank.addPiece(PieceFactory.createWhiteKnight());
        whiteMainRank.addPiece(PieceFactory.createWhiteRook());
    }

    public int pieceCount() {
        return ranks.stream()
                .mapToInt(Rank::getPiecesCount)
                .sum();
    }

    public int getPiecesCount(Color color, Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.getPiecesCount(color, type))
                .sum();
    }

    public Piece findPiece(String fileRank) {
        Position position = new Position(fileRank);
        return findPiece(position);
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getRankIndex())
                .findPieceByFile(position.getFileIndex());
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = findPiece(sourcePosition);
        Piece targetPiece = findPiece(targetPosition);
        placePiece(sourcePosition, targetPiece);
        placePiece(targetPosition, sourcePiece);
    }

    public void placePiece(Position position, Piece piece) {
        ranks.get(position.getRankIndex())
                .moveTo(position.getFileIndex(), piece);
    }

    public List<Rank> getRanks() {
        return ranks;
    }

}
