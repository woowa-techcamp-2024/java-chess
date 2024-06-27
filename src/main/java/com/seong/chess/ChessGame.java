package com.seong.chess;

import com.seong.chess.pieces.Blank;
import com.seong.chess.pieces.Direction;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece piece = board.findPiece(sourcePosition);
        board.move(sourcePosition, Blank.create());
        board.move(targetPosition, piece);
    }

    public void move(String sourcePosition, Direction direction, int moveCount) {
        Piece piece = board.findPiece(sourcePosition);
        Position targetPosition = piece.nextPosition(sourcePosition, direction, moveCount);
        board.move(targetPosition.convert(), piece);
    }

    public void initialize() {
        board.initialize();
    }

    public double calculatePoint(Color color) {
        Double result = board.getPiecesOrderByHighestScore(color).stream()
                .filter(piece -> !piece.isEqual(Type.PAWN, color))
                .reduce(0D, (point, piece) -> point + piece.getDefaultPoint(), Double::sum);

        for (int i = 0; i < Board.BOARD_LENGTH; i++) {
            double pawnCount = board.getColumnPawnCount(color, i);
            if (pawnCount > 1) {
                pawnCount *= 0.5;
            }
            result += pawnCount * Type.PAWN.getDefaultPoint();
        }
        return result;
    }
}
