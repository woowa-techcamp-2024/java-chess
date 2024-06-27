package chess.pieces;

import chess.Board;
import chess.ChessGame;
import chess.ChessView;
import chess.Position;
import chess.constant.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {
    private Board board;
    private ChessView chessView;
    private ChessGame chessGame;

    @BeforeEach
    public void setUp() {
        board = new Board();
        chessView = new ChessView(board);
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("Bishop 기물이 원하는 대로 이동이 가능해야 한다")
    public void moveBishop() {
        board.initialize();
        board.saveByPosition(PieceFactory.createBishop(Color.BLACK, null), new Position(1, 0));
        board.saveByPosition(PieceFactory.createBishop(Color.WHITE, null), new Position(0, 1));
        board.saveByPosition(PieceFactory.createBishop(Color.BLACK, null), new Position(2, 1));

        chessGame.move("b8", "d6");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createBishop(Color.BLACK, new Position("b8")), board.findPiece("b8"));

        chessGame.move("b8", "c7");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createBishop(Color.BLACK, new Position("b8")), board.findPiece("b8"));

        chessGame.move("b8", "a7");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createBishop(Color.BLACK, new Position("a7")), board.findPiece("a7"));
    }
}
