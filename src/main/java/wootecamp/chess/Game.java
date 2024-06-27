package wootecamp.chess;

import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;
import wootecamp.chess.pieces.Direction;
import wootecamp.chess.pieces.Knight;
import wootecamp.chess.pieces.Piece;

public class Game {
    private final Board board = new Board();

    public void start() {
        board.initialize();
        System.out.println(board.showBoard());
    }

    public void move(BoardPosition source, BoardPosition target) {
        //TODO: source가 비어있는 경우 검증
        Piece piece = board.findPiece(source);

        verifyCrossMove(source, target, piece);

        if (verifyMove(source, target, piece)) {
            board.move(source, target);
            System.out.println(board.showBoard());
            return;
        }

        System.out.println(board.showBoard());
        throw new RuntimeException("이동할 수 없는 위치");
    }

    private boolean verifyCrossMove(BoardPosition source, BoardPosition target, Piece piece) {
        //TODO : isCrossable 메서드로 관리
        if (piece.getClass() == Knight.class) {
            return true;
        }
        MoveVector moveVector = new MoveVector(source, target);

        Direction direction = Direction.determineDirection(moveVector).get();
        BoardPosition curPosition = source.createNextPosition(direction);

        while (curPosition.equals(target)) {
            if (!board.findPiece(curPosition).isEmptyPiece()) {
                return false;
            }
        }

        return true;
    }


    private boolean verifyMove(BoardPosition source, BoardPosition target, Piece piece) {
        return verifyPieceMove(source, target, piece)
                && verifyCrossMove(source, target, piece)
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


}
