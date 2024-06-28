package com.seong.chess;

import com.seong.chess.pieces.Blank;
import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import java.util.List;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(String sourcePosition, String targetPosition) {
        // 동일한 색인지, 동일한 위치인지 검사
        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece targetPiece = board.findPiece(targetPosition);
        sourcePiece.checkSameColor(targetPiece);
        checkIsSamePosition(sourcePosition, targetPosition);

        // 움직일 수 있는 경로인지 확인. 폰의 경우 추가 규칙에 따른 경로 수정
        List<Position> movablePosition = sourcePiece.findMovablePosition(sourcePosition);
        if (targetPiece.isNotBlank()) {
            movablePosition.addAll(board.getPawnMovable(sourcePosition, targetPosition));
            movablePosition.removeAll(board.getPawnCanNotMovable(sourcePosition, targetPosition));
        }
        checkIsMovable(movablePosition, targetPosition);

        // 블락되어 있는지 확인.
        board.checkIsBlocked(sourcePosition, targetPosition);

        board.move(sourcePosition, Blank.create());
        board.move(targetPosition, sourcePiece);
    }

    private void checkIsSamePosition(String sourcePosition, String targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("이동 위치와 현재 위치가 동일합니다.");
        }
    }

    private void checkIsMovable(List<Position> movablePosition, String targetPosition) {
        if (movablePosition.contains(Position.convert(targetPosition))) {
            return;
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
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
