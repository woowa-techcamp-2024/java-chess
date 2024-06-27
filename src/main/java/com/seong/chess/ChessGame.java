package com.seong.chess;

import com.seong.chess.pieces.Blank;
import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String sourcePosition, String targetPosition) {
        Piece sourcePositionPiece = board.findPiece(sourcePosition);
        Piece targetPositionPiece = board.findPiece(targetPosition);
        sourcePositionPiece.checkMoveTargetPosition(sourcePosition, targetPosition);
        sourcePositionPiece.checkSameColor(targetPositionPiece);
        board.move(sourcePosition, Blank.create());
        board.move(targetPosition, sourcePositionPiece);
    }

    public void initialize() {
        board.initialize();
    }

    public double calculatePoint(Color color) {
        Double result = board.getPiecesOrderByHighestScore(color).stream()
                .filter(piece -> !piece.isPawn(color))
                .reduce(0D, (point, piece) -> point + piece.getDefaultPoint(), Double::sum);

        for (int i = 0; i < Board.BOARD_LENGTH; i++) {
            double pawnCount = board.getColumnPawnCount(color, i);
            if (pawnCount > 1) {
                pawnCount *= 0.5;
            }
            result += pawnCount * Pawn.DEFAULT_POINT;
        }
        return result;
    }

    public void checkRightPlayerTurn(Turn turn, String sourcePosition) {
        Piece sourcePiece = board.findPiece(sourcePosition);
        if (sourcePiece.isEqual(turn.getColor())) {
            return;
        }
        throw new IllegalArgumentException("해당 플레이어의 턴이 아닙니다.");
    }
}
