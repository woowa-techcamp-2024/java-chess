package com.wootecam.chess;

import static com.wootecam.chess.Board.END_COLUMN_SYMBOL;
import static com.wootecam.chess.Board.END_ROW_SYMBOL;
import static com.wootecam.chess.Board.START_COLUMN_SYMBOL;
import static com.wootecam.chess.Board.START_ROW_SYMBOL;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Type;
import java.util.stream.IntStream;

public class Game {

    private final Board board;
    private final CoordinatesExtractor extractor;

    public Game(final Board board, final CoordinatesExtractor extractor) {
        this.board = board;
        this.extractor = extractor;
    }

    public void move(final String startCoordinates, final String targetCoordinates) {
        int rowIndex = extractor.extractRowIndex(startCoordinates);
        int columnIndex = extractor.extractColumnIndex(startCoordinates);
        Piece piece = board.findPiece(rowIndex, columnIndex);

        validateMovePieces(piece);

        updatePiece(startCoordinates, Piece.createBlank());
        updatePiece(targetCoordinates, piece);
    }

    private void updatePiece(final String coordinates, final Piece piece) {
        extractor.validateCoordinates(coordinates);

        int rowIndex = extractor.extractRowIndex(coordinates);
        int columnIndex = extractor.extractColumnIndex(coordinates);

        board.updatePiece(rowIndex, columnIndex, piece);
    }

    private void validateMovePieces(final Piece piece) {
        if (piece.isBlank()) {
            throw new IllegalArgumentException("빈칸은 이동시킬 수 없습니다.");
        }
    }

    public double calculatePoint(final Color color) {
        return calculatePiecesPoint(color) + calculatePawnsPoint(color);
    }

    private double calculatePiecesPoint(final Color color) {
        return board.getRanks()
                .stream()
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
        int rowIndex = extractor.extractRowIndex(coordinates);
        int columnIndex = extractor.extractColumnIndex(coordinates);

        return board.findPiece(rowIndex, columnIndex).isSameColorAndType(color, Type.PAWN);
    }
}
