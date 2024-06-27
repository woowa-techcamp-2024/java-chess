package chess;

import chess.piece.Piece;
import chess.util.ChessPoint;
import chess.util.MoveRule;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private boolean isWhiteTurn = true;
    private boolean isStarted = false;


    public ChessGame(Board board) {
        this.board = board;
    }

    public ChessGame() {
        this(new Board());
    }

    public void start() {
        isStarted = true;
        board.initialize();
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void move(String source, String target) {
        checkTurn(board.findPiece(source));
        board.move(source, target);
        isWhiteTurn = !isWhiteTurn;
    }

    public Map<ChessPoint, MoveRule> getMovablePoints(String source) {
        Piece piece = board.findPiece(source);
        if (piece == null) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
        checkTurn(piece);
        Map<ChessPoint, MoveRule> movablePoints = piece.getMovablePoints(ChessPoint.of(source), board);
        return movablePoints;
    }

    private void checkTurn(Piece piece) {
        if (piece.isWhite() != isWhiteTurn) {
            throw new IllegalArgumentException((isWhiteTurn ? "백색" : "흑색") + " 차례입니다.");
        }
    }

    public void print() {
        board.print();
    }

    public boolean isEnded() {
        return board.isKingDead(isWhiteTurn ? Piece.Color.WHITE : Piece.Color.BLACK);
    }

    public boolean isCheck() {
        boolean check = board.isCheck(isWhiteTurn ? Piece.Color.WHITE : Piece.Color.BLACK);
        return check;
    }
}
