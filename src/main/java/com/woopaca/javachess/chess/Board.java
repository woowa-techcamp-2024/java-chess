package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.Type;
import com.woopaca.javachess.utils.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board {

    public static final int BOARD_SIZE = 8;
    public static final int PAWNS_COUNT = 8;
    public static final int BLACK_PAWNS_RANK = 1;
    public static final int WHITE_PAWNS_RANK = 6;
    public static final int BLACK_MAJORS_RANK = 0;
    public static final int WHITE_MAJORS_RANK = 7;

    private final List<Rank> ranks = new ArrayList<>();

    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.add(new Rank());
        }
    }

    public void initialize() {
        addBlankRanks();
        addPawns();
        addBlackMajorPieces();
        addWhiteMajorPieces();
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = ranks.get(i);
            for (int j = 0; j < BOARD_SIZE; j++) {
                rank.addPiece(Piece.createBlank());
            }
        }
    }

    private void addBlankRanks() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ranks.get(2).addPiece(Piece.createBlank());
            ranks.get(3).addPiece(Piece.createBlank());
            ranks.get(4).addPiece(Piece.createBlank());
            ranks.get(5).addPiece(Piece.createBlank());
        }
    }

    private void addPawns() {
        Rank blackPawnsRank = ranks.get(BLACK_PAWNS_RANK);
        Rank whitePawnsRank = ranks.get(WHITE_PAWNS_RANK);
        for (int i = 0; i < PAWNS_COUNT; i++) {
            blackPawnsRank.addPiece(Piece.createBlackPawn());
            whitePawnsRank.addPiece(Piece.createWhitePawn());
        }
    }

    private void addBlackMajorPieces() {
        Rank blackMajorRank = ranks.get(BLACK_MAJORS_RANK);
        blackMajorRank.addPiece(Piece.createBlackRook());
        blackMajorRank.addPiece(Piece.createBlackKnight());
        blackMajorRank.addPiece(Piece.createBlackBishop());
        blackMajorRank.addPiece(Piece.createBlackQueen());
        blackMajorRank.addPiece(Piece.createBlackKing());
        blackMajorRank.addPiece(Piece.createBlackBishop());
        blackMajorRank.addPiece(Piece.createBlackKnight());
        blackMajorRank.addPiece(Piece.createBlackRook());
    }

    private void addWhiteMajorPieces() {
        Rank whiteMajorRank = ranks.get(WHITE_MAJORS_RANK);
        whiteMajorRank.addPiece(Piece.createWhiteRook());
        whiteMajorRank.addPiece(Piece.createWhiteKnight());
        whiteMajorRank.addPiece(Piece.createWhiteBishop());
        whiteMajorRank.addPiece(Piece.createWhiteQueen());
        whiteMajorRank.addPiece(Piece.createWhiteKing());
        whiteMajorRank.addPiece(Piece.createWhiteBishop());
        whiteMajorRank.addPiece(Piece.createWhiteKnight());
        whiteMajorRank.addPiece(Piece.createWhiteRook());
    }

    public String getWhitePawnsResult() {
        Rank whitePanwsRank = ranks.get(WHITE_PAWNS_RANK);
        return whitePanwsRank.generateResult();
    }

    public String getBlackPawnsResult() {
        Rank blackPanwsRank = ranks.get(BLACK_PAWNS_RANK);
        return blackPanwsRank.generateResult();
    }

    public String print() {
        StringBuilder boardResult = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = ranks.get(i);
            String rankResult = rank.generateResult();
            boardResult.append(StringUtils.appendNewLine(rankResult));
        }

        return boardResult.toString();
    }

    public int pieceCount() {
        return ranks.stream()
                .mapToInt(Rank::getPiecesCount)
                .sum();
    }

    public String showBoard() {
        return print();
    }

    public int getPiecesCount(Color color, Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.getPiecesCount(color, type))
                .sum();
    }

    public Piece findPiece(String fileRank) {
        Position position = new Position(fileRank);
        return ranks.get(position.getRankIndex())
                .findPieceByFile(position.getFileIndex());
    }

    public void move(String fileRank, Piece piece) {
        Position position = new Position(fileRank);
        Rank rank = ranks.get(position.getRankIndex());
        rank.moveTo(position.getFileIndex(), piece);
    }

    public double calculatePoint(Color color) {
        double pointWithoutPawns = ranks.stream()
                .mapToDouble(rank -> rank.calculatePointWithoutPawns(color))
                .sum();
        double pawnsPoint = calculatePawnsPoint(color);
        return pointWithoutPawns + pawnsPoint;
    }

    private double calculatePawnsPoint(Color color) {
        double pawnsPoint = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int fileIndex = i;
            long pawnsCount = ranks.stream()
                    .filter(rank -> {
                        Piece piece = rank.findPieceByFile(fileIndex);
                        return piece.getType() == Type.PAWN && piece.getColor() == color;
                    })
                    .count();
            pawnsPoint += pawnsCount * (pawnsCount > 1 ? 0.5 : Type.PAWN.getPoint());
        }
        return pawnsPoint;
    }

    public List<Piece> sortPiecesByPoint(Color color) {
        Comparator<Piece> comparator = Comparator.comparing(Piece::getPoint);
        return sortPieces(color, comparator);
    }

    public List<Piece> sortPiecesByPointDescending(Color color) {
        Comparator<Piece> comparator = Comparator.comparing(Piece::getPoint).reversed();
        return sortPieces(color, comparator);
    }

    private List<Piece> sortPieces(Color color, Comparator<Piece> comparator) {
        return ranks.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(piece -> piece.getColor() == color)
                .sorted(comparator)
                .toList();
    }

}
