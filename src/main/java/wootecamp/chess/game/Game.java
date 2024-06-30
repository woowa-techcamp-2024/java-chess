package wootecamp.chess.game;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;
import wootecamp.chess.pieces.Direction;
import wootecamp.chess.pieces.Piece;

public class Game {
    private final Board board;

    private Piece.Color curTurnColor = Piece.Color.WHITE;
    private State state = State.READY;

    public Game(Board board) {
        this.board = board;
    }

    public void start() {
        board.initialize();
    }

    public void move(BoardPosition source, BoardPosition target) {
        Piece piece = board.findPiece(source);
        if (piece.getColor() != curTurnColor) {
            throw new RuntimeException("차례가 아닙니다.");
        }

        if (verifyMove(source, target, piece)) {
            board.move(source, target);
            curTurnColor = curTurnColor == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
            return;
        }

        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }

    private boolean verifyJumpMove(BoardPosition source, BoardPosition target, Piece piece) {
        if (piece.canJump()) {
            return true;
        }
        MoveVector moveVector = new MoveVector(source, target);

        Direction direction = Direction.determineDirection(moveVector).get();
        BoardPosition curPosition = source.createNextPosition(direction);

        while (!curPosition.equals(target)) {
            if (!board.findPiece(curPosition).isEmptyPiece()) {
                return false;
            }
            curPosition = curPosition.createNextPosition(direction);
        }

        return true;
    }


    private boolean verifyMove(BoardPosition source, BoardPosition target, Piece piece) {
        return verifyPieceMove(source, target, piece)
                && verifyJumpMove(source, target, piece)
                && verifyTargetPosition(target, piece)
                && verifyPawnMovement(source, target, piece);
    }

    private boolean verifyPieceMove(BoardPosition source, BoardPosition target, Piece piece) {
        MoveVector moveVector = new MoveVector(source, target);
        return piece.verifyMovePosition(moveVector);
    }


    private boolean verifyTargetPosition(BoardPosition target, Piece piece) {
        Piece targetPiece = board.findPiece(target);
        return targetPiece.getColor() != piece.getColor();
    }

    private boolean verifyPawnMovement(BoardPosition source, BoardPosition target, Piece piece) {
        if (!piece.getType().equals(Piece.Type.PAWN)) {
            return true;
        }

        MoveVector moveVector = new MoveVector(source, target);
        Direction direction = Direction.determineDirection(moveVector).get();

        if (Direction.diagonalDirection().contains(direction)) {
            Piece targetPiece = board.findPiece(target);
            Piece.Color color = piece.getColor() == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
            return targetPiece.isPawn(color);
        }
        return true;
    }

    public void end() {
        this.state = State.END;
    }

    public boolean isEnded() {
        return this.state == State.END;
    }
}
