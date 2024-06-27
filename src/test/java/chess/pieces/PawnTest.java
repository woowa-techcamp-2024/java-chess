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

public class PawnTest {
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
    @DisplayName("Pawn 기물이 처음 이동 시 2칸 이동이 가능해야 한다")
    public void movePawnFirst() throws Exception {
        board.initialize();
        board.saveByPosition(PieceFactory.createPawn(Color.BLACK, null), new Position(1, 1));

        chessGame.move("b7", "b5");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createPawn(Color.BLACK, new Position("b5")), board.findPiece("b5"));

        chessGame.move("b5", "b3");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createPawn(Color.BLACK, new Position("b5")), board.findPiece("b5"));
    }

    @Test
    @DisplayName("Pawn 기물이 원하는 대로 이동이 가능해야 한다")
    public void movePawn() throws Exception {
        board.initialize();
        board.saveByPosition(PieceFactory.createPawn(Color.BLACK, null), new Position(1, 1));
        board.saveByPosition(PieceFactory.createPawn(Color.WHITE, null), new Position(2, 2));
        board.saveByPosition(PieceFactory.createPawn(Color.WHITE, null), new Position(1, 3));

        chessGame.move("b7", "b5");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createPawn(Color.BLACK, new Position("b7")), board.findPiece("b7"));

        chessGame.move("b7", "c6");
        System.out.println(chessView.showBoard());
        assertEquals(PieceFactory.createPawn(Color.BLACK, new Position("c6")), board.findPiece("c6"));
    }
}
