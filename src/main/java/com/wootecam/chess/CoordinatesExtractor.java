package com.wootecam.chess;

import static com.wootecam.chess.board.Board.END_COLUMN_SYMBOL;
import static com.wootecam.chess.board.Board.END_ROW_SYMBOL;
import static com.wootecam.chess.board.Board.START_COLUMN_SYMBOL;
import static com.wootecam.chess.board.Board.START_ROW_SYMBOL;

import com.wootecam.chess.board.Rank;
import com.wootecam.chess.pieces.Position;

public class CoordinatesExtractor {

    private static final int ROW_COORDINATE_INDEX = 1;
    private static final int COLUMN_COORDINATES_INDEX = 0;

    public Position extractPosition(final String coordinates) {
        validateCoordinates(coordinates);

        return new Position(extractRowIndex(coordinates), extractColumnIndex(coordinates));
    }

    private void validateCoordinates(String coordinate) {
        char columnSymbol = coordinate.charAt(COLUMN_COORDINATES_INDEX);
        char rowSymbol = coordinate.charAt(ROW_COORDINATE_INDEX);

        if (isOutOfCoordinates(columnSymbol, rowSymbol)) {
            String message = String.format("%d x %d 크기의 체스판의 범위를 벗어나는 좌표입니다. coordinate = %s",
                    Rank.PIECE_COUNT,
                    Rank.PIECE_COUNT,
                    coordinate
            );
            throw new IllegalArgumentException(message);
        }
    }

    private int extractRowIndex(String coordinate) {
        int rawRow = Character.getNumericValue(coordinate.charAt(ROW_COORDINATE_INDEX));

        return Math.abs(rawRow - Rank.PIECE_COUNT);
    }

    private int extractColumnIndex(String coordinate) {
        return coordinate.charAt(COLUMN_COORDINATES_INDEX) - START_COLUMN_SYMBOL;
    }

    private boolean isOutOfCoordinates(char columnSymbol, char rowSymbol) {
        return columnSymbol < START_COLUMN_SYMBOL
                || columnSymbol > END_COLUMN_SYMBOL
                || rowSymbol < START_ROW_SYMBOL
                || rowSymbol > END_ROW_SYMBOL;
    }

    public String createCoordinate(char columnSymbol, char rowSymbol) {
        return String.valueOf(columnSymbol) + rowSymbol;
    }
}
