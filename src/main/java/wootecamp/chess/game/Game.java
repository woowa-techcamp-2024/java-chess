package wootecamp.chess.game;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;
import wootecamp.chess.game.state.EndState;
import wootecamp.chess.game.state.PlayingState;
import wootecamp.chess.game.state.ReadyState;
import wootecamp.chess.game.state.State;
import wootecamp.chess.pieces.Direction;
import wootecamp.chess.pieces.Piece;

public class Game {
    private final GameInputManager gameInputManager;
    private final GameOutputManager gameOutputManager;
    private final Board board;


    private State state = new ReadyState(this);
    private Piece.Color curTurnColor = Piece.Color.WHITE;

    public Game(GameInputManager gameInputManager, GameOutputManager gameOutputManager, Board board) {
        this.gameInputManager = gameInputManager;
        this.gameOutputManager = gameOutputManager;
        this.board = board;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void start() {
        board.initialize();
        gameOutputManager.showBoard(board);
    }

    public void move(BoardPosition source, BoardPosition target) {
        Piece piece = board.findPiece(source);
        if (piece.getColor() != curTurnColor) {
            gameOutputManager.showError("차례가 아닙니다.");
            gameOutputManager.showBoard(board);
            return;
        }

        if (verifyMove(source, target, piece)) {
            board.move(source, target);
            gameOutputManager.showBoard(board);
            return;
        }

        gameOutputManager.showError("이동할 수 없는 위치입니다.");
        gameOutputManager.showBoard(board);
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
            curPosition = source.createNextPosition(direction);
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

    public boolean isEnd() {
        return state instanceof EndState;
    }

    public void receiveRequest() {
        //TODO : input을 어떻게 처리할 지 구조 개선해야함
        try {
            String request = gameInputManager.receiveRequest();
            state.handleRequest(request);
        } catch (RuntimeException e) {
            gameOutputManager.showError(e.getMessage());
            if(state instanceof PlayingState) {
                gameOutputManager.showBoard(board);
            }
        }
    }
}
