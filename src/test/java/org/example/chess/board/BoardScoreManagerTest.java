package org.example.chess.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.example.chess.board.sort.PieceComparatorFactory;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Color;
import org.example.chess.pieces.Piece.Type;
import org.example.chess.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardScoreManagerTest {

    private Board board;
    private BoardScoreManager boardScoreManager;
    private BoardInitializeManager boardInitializeManager;
    private BoardMoveManager boardMoveManager;
    private BoardView boardView;

    @BeforeEach
    void setUp() {
        board = new Board();
        boardView = new BoardView(board);
        boardScoreManager = new BoardScoreManager(board);
        boardInitializeManager = new BoardInitializeManager(board);
        boardMoveManager = new BoardMoveManager(board);
    }

    @Test
    void calculatePoint() throws Exception {
        boardInitializeManager.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        addPiece("f2", PieceFactory.createWhitePawn());
        addPiece("f3", PieceFactory.createWhitePawn());
        addPiece("g2", PieceFactory.createWhitePawn());
        addPiece("e1", PieceFactory.createWhiteRook());
        addPiece("f1", PieceFactory.createWhiteKing());

        boardView.print();
        assertEquals(15.0, boardScoreManager.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, boardScoreManager.calculatePoint(Color.WHITE), 0.01);
    }

    private void addPiece(String position, Piece piece) {
        boardMoveManager.move(position, piece);
    }

    @Test
    void sortPiecesByScoreDescendingOrder() {
        boardInitializeManager.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        List<Piece> allPiecesSortByPoint = boardScoreManager.findAllPiecesSortByPoint(Color.BLACK,
                PieceComparatorFactory.DESCENDING);

        assertEquals(Type.QUEEN, allPiecesSortByPoint.get(0).getType());
        assertEquals(Type.ROOK, allPiecesSortByPoint.get(1).getType());
        assertEquals(Type.PAWN, allPiecesSortByPoint.get(2).getType());
        assertEquals(Type.KING, allPiecesSortByPoint.get(3).getType());
    }

    @Test
    void sortPiecesByScoreAscendigOrder() {
        boardInitializeManager.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        List<Piece> allPiecesSortByPoint = boardScoreManager.findAllPiecesSortByPoint(Color.BLACK,
                PieceComparatorFactory.ASCENDING);

        assertEquals(Type.KING, allPiecesSortByPoint.get(0).getType());
        assertEquals(Type.PAWN, allPiecesSortByPoint.get(1).getType());
        assertEquals(Type.ROOK, allPiecesSortByPoint.get(2).getType());
        assertEquals(Type.QUEEN, allPiecesSortByPoint.get(3).getType());
    }
}