package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Type;

public class Pawn extends Piece {
    private boolean isFirstMove = true;  // 이걸 사용하려면 이동시에 새로운 객체를 만들면 안됨

    private Pawn(Color color, Position position) {
        super(color, Type.PAWN, position);
    }

    public static Pawn create(Color color, Position position) {
        return new Pawn(color, position);
    }

    @Override
    public boolean canMove(Piece target) {
        if (isSameColor(target)) return false;

        Position targetPosition = target.getPosition();
        Position currentPosition = getPosition();

        int rankDiff = Math.abs(targetPosition.getRank() - currentPosition.getRank());
        int fileDiff = Math.abs(targetPosition.getFile() - currentPosition.getFile());

        // 백색 폰은 위로, 흑색 폰은 아래로 움직인다.
        int forwardDirection = (getColor() == Color.WHITE) ? 1 : -1;

        // 1칸 전진
        if (fileDiff == 0 && rankDiff == forwardDirection) {
            isFirstMove = false;
            return target.getType() == Type.NO_PIECE;
        }

        // 처음에 2칸 전진
        if (isFirstMove && fileDiff == 0 && rankDiff == 2 * forwardDirection) {
            isFirstMove = false;
            return target.getType() == Type.NO_PIECE;
        }

        // 대각선으로 적을 잡는 경우
        if (fileDiff == 1 && rankDiff == forwardDirection) {
            isFirstMove = false;
            return target.getType() != Type.NO_PIECE;
        }

        return false;
    }
}
