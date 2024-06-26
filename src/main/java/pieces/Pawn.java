package pieces;

import static chess.DirectionType.NN;
import static chess.DirectionType.SS;
import static chess.DirectionType.pawnDirection;
import static pieces.Color.BLACK;
import static pieces.Color.WHITE;

import chess.DirectionType;
import chess.Position;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, PieceType pieceType, Position position) {
        super(color, pieceType, position);
    }

    /**
     * 일반 이동은 앞으로 하며, 잡을 때는 대각선으로 간다. 처음으로 움직이는 폰은 두 칸을 이동할 수 있다. promotion 체스판 반대편에 도달할 경우, 퀸 나이트 룩 비숍 중 하나로 승진할 수 있다.
     * optional: al paso(앙 파상): 폰이 첫 이동으로 두 칸 앞으로 나갔을 때, 상대방의 폰 바로 옆칸으로 이동하는 경우에 상대방의 폰은 첫번째 폰이 지나갈 때 잡을 수 있다. 이건 직후에 둬야한다.
     *
     * @param position 이동하려는 위치
     */
    @Override
    public void move(String position) {
        Position target = Position.of(position);
        validatePosition(target);
    }

    @Override
    public List<Position> getAccessiblePosition() {
        return null;
    }

    private void validatePosition(Position target) {
        List<DirectionType> directionTypes = pawnDirection(color);
        if (!isNotMoved()) {
            addAdditionalDirection(directionTypes);
        }
        for (DirectionType directionType : directionTypes) {
            if (position.addDirection(directionType).equals(target)) {
                return;
            }
        }
        throw new IllegalArgumentException("invalid move position");
    }

    private void addAdditionalDirection(List<DirectionType> directionTypes) {
        if (color == BLACK) {
            directionTypes.add(SS);
        } else {
            directionTypes.add(NN);
        }
    }

    /**
     * 한 번도 움직이지 않았을 때
     */
    private boolean isNotMoved() {
        return (color == BLACK && position.getColumn() == 1) || (color == WHITE && position.getRow() == 6);
    }
}
