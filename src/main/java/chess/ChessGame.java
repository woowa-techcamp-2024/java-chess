package chess;

import chess.board.Board;
import chess.board.Coordinate;
import chess.board.PointCalculator;
import chess.pieces.Piece;
import chess.view.ChessView;

import java.util.List;

public class ChessGame {
    private final Board board;
    private final PointCalculator pointCalculator;
    private final ChessView chessView;

    private int turn = 0;

    public ChessGame(Board board, PointCalculator pointCalculator, ChessView chessView) {
        this.board = board;
        this.pointCalculator = pointCalculator;
        this.chessView = chessView;
    }

    public double calculatePoint(Piece.Color color) {
        return pointCalculator.calculatePoint(this.board, color);
    }

    public String printBoard() {
        return chessView.printBoard(board);
    }

    public void move(String fromStr, String toStr) {
        isValidMoveRequest(fromStr, toStr);
        Coordinate from = Coordinate.of(fromStr);
        Coordinate to = Coordinate.of(toStr);

        Piece piece = board.findPiece(from);

        if (isWhiteTurn() && piece.isBlack()) {
            throw new IllegalArgumentException("해당 턴에 움직일 수 없는 말입니다.");
        }

        if (isBlackTurn() && piece.isWhite()) {
            throw new IllegalArgumentException("해당 턴에 움직일 수 없는 말입니다.");
        }

        if(piece.isBlank()) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }

        if(!piece.verifyMoveCoordinate(from, to)) {
            throw new IllegalArgumentException("말을 해당 위치로 이동할 수 없습니다.");
        }

        // 해당 위치에 같은 색의 기물이 있는지 확인
        Piece target = board.findPiece(to);
        if(target.isSameColor(piece.getColor())) {
            throw new IllegalArgumentException("해당 위치에 같은 색의 기물이 있습니다.");
        }

        board.movePiece(from, to);
        turn++;
    }

    private boolean isWhiteTurn() {
        return turn % 2 == 0;
    }

    private boolean isBlackTurn() {
        return turn % 2 == 1;
    }

    private void isValidMoveRequest(String from, String to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("같은 좌표로 이동할 수 없습니다.");
        }
    }

    public List<Piece> sortPiecesByPoint(Piece.Color color, Board.SORT sort) {
        return board.sortPiecesByPoint(color, sort);
    }
}
