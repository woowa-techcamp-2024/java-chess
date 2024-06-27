package chess;

import chess.board.Board;
import chess.board.PointCalculator;
import chess.pieces.Piece;
import chess.view.ChessView;

import java.util.List;

public class ChessGame {
    private final Board board;
    private final PointCalculator pointCalculator;
    private final ChessView chessView;

    public ChessGame(Board board, PointCalculator pointCalculator, ChessView chessView) {
        this.board = board;
        this.pointCalculator = pointCalculator;
        this.chessView = chessView;
    }

    public double calculatePoint(Piece.Color color) {
        return pointCalculator.calculatePoint(this.board, color);
    }

    public String printBoard() {
        return chessView.printBoard();
    }

    public void move(String from, String to) {
        board.move(from, to);
    }

    public List<Piece> sortPiecesByPoint(Piece.Color color, boolean isDescending) {
        return board.sortPiecesByPoint(color, isDescending ? Board.SORT_DESCENDING : Board.SORT_ASCENDING);
    }

}
