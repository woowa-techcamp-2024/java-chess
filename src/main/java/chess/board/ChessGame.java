package chess.board;

import chess.calculator.DefaultPointCalculateStrategy;
import chess.calculator.PointCalculator;
import chess.calculator.SameFilePawnPointCalculateStrategy;
import chess.exception.NotPlayerTurnException;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Position;
import chess.sorter.Direction;
import chess.sorter.PointSorter;
import chess.sorter.Sorter;
import java.util.List;

public class ChessGame {

    private final Board board;

    private final PointCalculator pointCalculator;

    private final Sorter pointSorter;

    private static final Color firstTurn = Color.WHITE;

    private static final Color secondTurn = Color.BLACK;

    private int turn;

    public ChessGame() {
        this.board = new Board();
        this.pointCalculator = new PointCalculator(List.of(
                new DefaultPointCalculateStrategy(),
                new SameFilePawnPointCalculateStrategy()
        ));
        this.pointSorter = new PointSorter();
        turn = 1;
    }


    public Board getBoard() {
        return board;
    }

    public void initialize() {
        board.initialize();
    }

    public void move(Position source, Position target) {
        Piece piece = board.findPiece(source);
        Color required = turn % 2 == 1 ? firstTurn : secondTurn;
        if (!piece.hasColor(required)) {
            throw new NotPlayerTurnException();
        }
        turn++;
        board.move(source, target);
    }

    public double getPointByWhite() {
        return pointCalculator.calculate(board, Color.WHITE);
    }

    public double getPointByBlack() {
        return pointCalculator.calculate(board, Color.BLACK);
    }

    public List<Piece> sortPiecesByPointAsc(Color color) {
        return board.sort(color, pointSorter, Direction.ASC);
    }

    public List<Piece> sortPiecesByPointDesc(Color color) {
        return board.sort(color, pointSorter, Direction.ASC);
    }
}
