package wootecamp.chess.game;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;
import wootecamp.chess.board.Direction;
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
            throw new IllegalArgumentException("차례가 아닙니다.");
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

        Direction direction = moveVector.findDirection().get();
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
        Direction direction = moveVector.findDirection().get();

        Piece targetPiece = board.findPiece(target);

        if (Direction.linearDirection().contains(direction)) {
            if (!targetPiece.isEmptyPiece()) {
                return false;
            }
            if (moveVector.calculateSquareDistance() == 4) {
                if (piece.getColor() == Piece.Color.WHITE && source.getRank() != '2') {
                    return false;
                }
                if (piece.getColor() == Piece.Color.BLACK && source.getRank() != '7') {
                    return false;
                }
            }
        }

        if (Direction.diagonalDirection().contains(direction)) {
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

    public String showBoard() {
        return board.showBoard();
    }
}
