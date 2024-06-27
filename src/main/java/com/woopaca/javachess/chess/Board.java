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
                rank.addPiece(PieceFactory.createBlank(new Position(j, i)));
            }
        }
    }

    private void addBlankRanks() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.get(2).addPiece(PieceFactory.createBlank(new Position(i, 2)));
            ranks.get(3).addPiece(PieceFactory.createBlank(new Position(i, 3)));
            ranks.get(4).addPiece(PieceFactory.createBlank(new Position(i, 4)));
            ranks.get(5).addPiece(PieceFactory.createBlank(new Position(i, 5)));
        }
    }

    private void addPawns() {
        for (int i = 0; i < PAWNS_COUNT; i++) {
            ranks.get(BLACK_PAWNS_RANK).addPiece(PieceFactory.createBlackPawn(new Position(i, BLACK_PAWNS_RANK)));
            ranks.get(WHITE_PAWNS_RANK).addPiece(PieceFactory.createWhitePawn(new Position(i, WHITE_PAWNS_RANK)));
        }
    }

    private void addBlackMainPieces() {
        Rank blackMainRank = ranks.get(BLACK_MAIN_RANK);
        blackMainRank.addPiece(PieceFactory.createBlackRook(new Position(0, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackKnight(new Position(1, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackBishop(new Position(2, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackQueen(new Position(3, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackKing(new Position(4, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackBishop(new Position(5, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackKnight(new Position(6, BLACK_MAIN_RANK)));
        blackMainRank.addPiece(PieceFactory.createBlackRook(new Position(7, BLACK_MAIN_RANK)));
    }

    private void addWhiteMainPieces() {
        Rank whiteMainRank = ranks.get(WHITE_MAIN_RANK);
        whiteMainRank.addPiece(PieceFactory.createWhiteRook(new Position(0, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteKnight(new Position(1, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteBishop(new Position(2, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteQueen(new Position(3, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteKing(new Position(4, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteBishop(new Position(5, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteKnight(new Position(6, WHITE_MAIN_RANK)));
        whiteMainRank.addPiece(PieceFactory.createWhiteRook(new Position(7, WHITE_MAIN_RANK)));
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
