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

public class KnightTest {
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
    @DisplayName("Knight 기물이 원하는 대로 이동이 가능해야 한다")
    public void moveKnight() throws Exception {
        board.initialize();
        board.saveByPosition(PieceFactory.createKnight(Color.BLACK, null), new Position(1, 0));
        board.saveByPosition(PieceFactory.createKnight(Color.WHITE, null), new Position(1, 4));
        board.saveByPosition(PieceFactory.createKnight(Color.BLACK, null), new Position(3, 4));

        chessGame.move("b8", "a8");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createKnight(Color.BLACK, new Position("b8")), board.findPiece("b8"));

        chessGame.move("b8", "c6");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createKnight(Color.BLACK, new Position("c6")), board.findPiece("c6"));

        chessGame.move("c6", "d4");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createKnight(Color.BLACK, new Position("c6")), board.findPiece("c6"));

        chessGame.move("c6", "b4");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createKnight(Color.BLACK, new Position("b4")), board.findPiece("b4"));
    }

    @Test
    @DisplayName("Knight 기물은 중간의 다른 기물을 뛰어 넘을 수 있어야 한다")
    public void jumpKnight() throws Exception {
        board.initialize();
        board.saveByPosition(PieceFactory.createKnight(Color.BLACK, null), new Position(1, 0));
        board.saveByPosition(PieceFactory.createKnight(Color.WHITE, null), new Position(1, 1));

        chessGame.move("b8", "c6");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createKnight(Color.BLACK, new Position("c6")), board.findPiece("c6"));
    }
}
