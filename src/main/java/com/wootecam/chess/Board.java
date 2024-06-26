package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.NEW_LINE;
import static com.wootecam.chess.utils.StringUtils.appendNewLine;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Rank;
import com.wootecam.chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final int ROW_COORDINATE_INDEX = 1;
    private static final char START_COLUMN_SYMBOL = 'a';
    private static final char END_COLUMN_SYMBOL = 'h';
    private static final char START_ROW_SYMBOL = '1';
    private static final char END_ROW_SYMBOL = '8';

    private final List<Rank> ranks;

    public Board() {
        ranks = new ArrayList<>();

        ranks.add(Rank.createBlackOtherPieces());
        ranks.add(Rank.createPawns(Color.BLACK));
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createPawns(Color.WHITE));
        ranks.add(Rank.createWhiteOtherPieces());
    }

    public void initializeEmpty() {
        ranks.clear();

        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
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
        validateCoordinate(coordinate);
        int rowIndex = extractRowIndex(coordinate);
        int columnIndex = extractColumnIndex(coordinate);

        Rank rowRank = ranks.get(rowIndex);

        return rowRank.findPieceByColumn(columnIndex);
    }

    private int extractRowIndex(String coordinate) {
        int rawRow = Character.getNumericValue(coordinate.charAt(ROW_COORDINATE_INDEX));

        return Math.abs(rawRow - Rank.PIECE_COUNT);
    }

    private static int extractColumnIndex(String coordinate) {
        return coordinate.charAt(0) - START_COLUMN_SYMBOL;
    }

    public void move(String coordinate, Piece piece) {
        validateCoordinate(coordinate);

        int rowIndex = extractRowIndex(coordinate);
        int columnIndex = extractColumnIndex(coordinate);
        Rank rank = ranks.get(rowIndex);
        ranks.set(rowIndex, rank.placePiece(columnIndex, piece));
    }

    private void validateCoordinate(String coordinate) {
        char columnSymbol = coordinate.charAt(0);
        char rowSymbol = coordinate.charAt(1);

        if (isOutOfCoordinate(columnSymbol, rowSymbol)) {
            String message = String.format("%d x %d 크기의 체스판의 범위를 벗어나는 좌표입니다. coordinate = %s",
                    Rank.PIECE_COUNT,
                    Rank.PIECE_COUNT,
                    coordinate
            );
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isOutOfCoordinate(char columnSymbol, char rowSymbol) {
        return columnSymbol < START_COLUMN_SYMBOL
                || columnSymbol > END_COLUMN_SYMBOL
                || rowSymbol < START_ROW_SYMBOL
                || rowSymbol > END_ROW_SYMBOL;
    }
}
