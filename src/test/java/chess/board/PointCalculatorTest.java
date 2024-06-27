package chess.board;

import chess.pieces.Piece;
import chess.view.ChessView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointCalculatorTest {


    @Test
    @DisplayName("pawn이 세로로 2개 있는 경우 0.5점씩으로 계산한다.")
    void caculcatePointWhenPawnIsVerticallyAligned() {
        Board board = new Board();
        ChessView chessView = new ChessView(board);
        PointCalculator pointCalculator = new PointCalculator();
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn(), board);
        addPiece("e6", Piece.createBlackQueen(), board);
        addPiece("b8", Piece.createBlackKing(), board);
        addPiece("c8", Piece.createBlackRook(), board);

        addPiece("f2", Piece.createWhitePawn(), board);
        addPiece("f3", Piece.createWhitePawn(), board);
        addPiece("g2", Piece.createWhitePawn(), board);
        addPiece("e1", Piece.createWhiteRook(), board);
        addPiece("f1", Piece.createWhiteKing(), board);

        assertEquals(15.0, pointCalculator.calculatePoint(board, Piece.Color.BLACK));
        assertEquals(6.5,  pointCalculator.calculatePoint(board, Piece.Color.WHITE));

        System.out.println(chessView.printBoard());
    }

    private void addPiece(String coordniate, Piece piece, Board board) {
        board.move(Coordinate.of(coordniate), piece);
    }

}