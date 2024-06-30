package wootecamp.chess.game;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.pieces.Piece;

import java.util.List;

public class Game {
    private final Board board;

    private Piece.Color curTurnColor = Piece.Color.WHITE;
    private State state = State.READY;
    private BoardPosition selectedPosition = null;

    public Game(Board board) {
        this.board = board;
    }

    public void start() {
        board.initialize();
        state = State.STANDBY_PICKING;
    }

    public List<BoardPosition> pick(BoardPosition source) {
        if (state != State.STANDBY_PICKING && state != State.STANDBY_MOVING) {
            throw new IllegalArgumentException("기물 선택을 할 수 없는 상태입니다.");
        }

        Piece piece = board.findPiece(source);
        if (piece.isEmptyPiece()) {
            throw new IllegalArgumentException("해당 칸에는 기물이 존재하지 않습니다.");
        }

        if (piece.getColor() != curTurnColor) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }

        List<BoardPosition> movablePositions = piece.findAllMovablePositions(board, source);
        if (movablePositions.isEmpty()) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 있는 위치가 없습니다.");
        }

        selectedPosition = source;
        state = State.STANDBY_MOVING;
        return movablePositions;
    }

    public void move(BoardPosition target) {
        if (state != State.STANDBY_MOVING) {
            throw new IllegalArgumentException("기물을 먼저 선택하세요.");
        }

        Piece piece = board.findPiece(selectedPosition);
        List<BoardPosition> movablePositions = piece.findAllMovablePositions(board, selectedPosition);

        if (movablePositions.contains(target)) {
            board.move(selectedPosition, target);
        }

        selectedPosition = null;
        state = State.STANDBY_PICKING;
        curTurnColor = curTurnColor.getOppositeColor();
        //TODO : King이 잡혔을 때, 프로모션 등의 상황 추가
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
