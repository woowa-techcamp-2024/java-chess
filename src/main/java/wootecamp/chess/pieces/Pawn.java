package wootecamp.chess.pieces;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, Type.PAWN);
    }

    private static final List<MovableDirection> whiteMovableDirections = List.of(
            new MovableDirection(1, Direction.NORTH)
    );

    private static final List<MovableDirection> blackMovableDirections = List.of(
            new MovableDirection(1, Direction.SOUTH)
    );

    private static final List<MovableDirection> whiteSpecificMovableDirections = List.of(
            new MovableDirection(1, Direction.NORTHWEST),
            new MovableDirection(1, Direction.NORTHEAST)
    );

    private static final List<MovableDirection> blackSpecificMovableDirections = List.of(
            new MovableDirection(1, Direction.SOUTHEAST),
            new MovableDirection(1, Direction.SOUTHEAST)
    );

    @Override
    public List<BoardPosition> findAllMovablePositions(Board board, BoardPosition source) {
        List<BoardPosition> movablePositions = new ArrayList<>();

        int movableTimes = 1;
        if((color == Color.WHITE && source.getRank() == '2') || (color == Color.BLACK && source.getRank() == '7')) {
            movableTimes = 2;
        }

        for (MovableDirection direction : getMovableDirections()) {
            BoardPosition curPosition = source;
            for (int i = 0; i < movableTimes && curPosition.canCreateNextPosition(direction.direction()); i++) {
                curPosition = curPosition.createNextPosition(direction.direction());
                Piece curPiece = board.findPiece(curPosition);
                if(!curPiece.isEmptyPiece()) {
                    break;
                }
                movablePositions.add(curPosition);
            }
        }

        for (MovableDirection direction : getSpecificMovableDirections()) {
            if(!source.canCreateNextPosition(direction.direction())) {
                continue;
            }

            BoardPosition curPosition = source.createNextPosition(direction.direction());
            if(board.findPiece(curPosition).isPawn(color.getOppositeColor())) {
                movablePositions.add(curPosition);
            }
        }

        return movablePositions;
    }

    @Override
    public List<MovableDirection> getMovableDirections() {
        if(color == Color.WHITE) {
            return whiteMovableDirections;
        }
        return blackMovableDirections;
    }

    private List<MovableDirection> getSpecificMovableDirections() {
        if(color == Color.WHITE) {
            return whiteSpecificMovableDirections;
        }
        return blackSpecificMovableDirections;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
