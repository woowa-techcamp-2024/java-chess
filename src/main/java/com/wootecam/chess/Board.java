package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.NEW_LINE;
import static com.wootecam.chess.utils.StringUtils.appendNewLine;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Rank;
import com.wootecam.chess.pieces.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    public static final char START_COLUMN_SYMBOL = 'a';
    public static final char END_COLUMN_SYMBOL = 'h';
    public static final char START_ROW_SYMBOL = '1';
    public static final char END_ROW_SYMBOL = '8';

    private final List<Rank> ranks;
    private final CoordinatesExtractor extractor;

    public Board(final List<Rank> ranks, final CoordinatesExtractor extractor) {
        this.ranks = ranks;
        this.extractor = extractor;
    }

    public String showBoard() {
        return appendNewLine(ranks.stream()
                .map(this::createRankResults)
                .collect(Collectors.joining(NEW_LINE)));
    }

    public int countBoardPieces() {
        return ranks.stream()
                .mapToInt(Rank::countPieces)
                .sum();
    }

    public int countSpecificBoardPieces(final Color color, final Type type) {
        return ranks.stream()
                .mapToInt(rank -> rank.countSpecificPieces(color, type))
                .sum();
    }

    private String createRankResults(Rank rank) {
        return rank.createResults();
    }

    public void print() {
        System.out.println(showBoard());
    }

    public Piece findPiece(String coordinate) {
        extractor.validateCoordinates(coordinate);

        int rowIndex = extractor.extractRowIndex(coordinate);
        int columnIndex = extractor.extractColumnIndex(coordinate);
        Rank rowRank = ranks.get(rowIndex);

        return rowRank.findPieceByColumn(columnIndex);
    }

    public void move(String coordinate, Piece piece) {
        extractor.validateCoordinates(coordinate);

        int rowIndex = extractor.extractRowIndex(coordinate);
        int columnIndex = extractor.extractColumnIndex(coordinate);
        Rank rank = ranks.get(rowIndex);
        ranks.set(rowIndex, rank.placePiece(columnIndex, piece));
    }

    public double calculatePoint(Color color) {
        return calculatePiecesPoint(color) + calculatePawnsPoint(color);
    }

    private double calculatePiecesPoint(Color color) {
        return ranks.stream()
                .mapToDouble(rank -> rank.calculateRankPiecesPoint(color))
                .sum();
    }

    private double calculatePawnsPoint(final Color color) {
        return IntStream.rangeClosed(START_COLUMN_SYMBOL, END_COLUMN_SYMBOL)
                .mapToDouble(columnSymbol -> calculateColumnPawnPoint(color, (char) columnSymbol))
                .sum();
    }

    private double calculateColumnPawnPoint(final Color color, final char columnSymbol) {
        int pawnCount = (int) IntStream.rangeClosed(START_ROW_SYMBOL, END_ROW_SYMBOL)
                .filter(rowSymbol -> isSamePawn(color, extractor.createCoordinate(columnSymbol, (char) rowSymbol)))
                .count();

        return Type.getPawnPoint(pawnCount);
    }

    private boolean isSamePawn(final Color color, final String coordinates) {
        return findPiece(coordinates).isSameColorAndType(color, Type.PAWN);
    }
}
