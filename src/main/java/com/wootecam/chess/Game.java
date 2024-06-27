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
        Position startPosition = extractor.extractPosition(startCoordinates);
        Position targetPosition = extractor.extractPosition(targetCoordinates);

        Piece piece = board.findPiece(startPosition);

        validateMovePieces(piece, startPosition, targetPosition);

        updatePiece(startCoordinates, Piece.createBlank());
        updatePiece(targetCoordinates, piece);
    }

    private void updatePiece(final String coordinates, final Piece piece) {
        Position position = extractor.extractPosition(coordinates);

        board.updatePiece(position, piece);
    }

    private void validateMovePieces(final Piece piece, final Position startPosition, final Position targetPosition) {
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
        Position position = extractor.extractPosition(coordinates);

        return board.findPiece(position).isSameColorAndType(color, Type.PAWN);
    }
}
