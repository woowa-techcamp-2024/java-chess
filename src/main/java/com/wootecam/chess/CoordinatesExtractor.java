package com.wootecam.chess;

import com.wootecam.chess.pieces.Rank;

public class CoordinatesExtractor {

    private static final int ROW_COORDINATE_INDEX = 1;
    private static final char START_COLUMN_SYMBOL = 'a';
    private static final char END_COLUMN_SYMBOL = 'h';
    private static final char START_ROW_SYMBOL = '1';
    private static final char END_ROW_SYMBOL = '8';

    public int extractRowIndex(String coordinate) {
        int rawRow = Character.getNumericValue(coordinate.charAt(ROW_COORDINATE_INDEX));

        return Math.abs(rawRow - Rank.PIECE_COUNT);
    }

    public int extractColumnIndex(String coordinate) {
        return coordinate.charAt(0) - START_COLUMN_SYMBOL;
    }

    public void validateCoordinates(String coordinate) {
        char columnSymbol = coordinate.charAt(0);
        char rowSymbol = coordinate.charAt(1);

        if (isOutOfCoordinates(columnSymbol, rowSymbol)) {
            String message = String.format("%d x %d 크기의 체스판의 범위를 벗어나는 좌표입니다. coordinate = %s",
                    Rank.PIECE_COUNT,
                    Rank.PIECE_COUNT,
                    coordinate
            );
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isOutOfCoordinates(char columnSymbol, char rowSymbol) {
        return columnSymbol < START_COLUMN_SYMBOL
                || columnSymbol > END_COLUMN_SYMBOL
                || rowSymbol < START_ROW_SYMBOL
                || rowSymbol > END_ROW_SYMBOL;
    }
}
