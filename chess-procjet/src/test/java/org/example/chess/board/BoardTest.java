package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.example.chess.board.sort.PieceComparatorFactory;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Color;
import org.example.chess.pieces.Piece.PieceFactory;
import org.example.chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardInitializeManger(), new BoardScoreManager());
    }

    @Test
    void create() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard()
        );
    }

    @Test
    void testCountPiecesByColorAndType() {
        board.initialize();
        assertEquals(8, board.countPiecesByColorAndType(Color.BLACK, Type.PAWN));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.BISHOP));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.KNIGHT));
        assertEquals(2, board.countPiecesByColorAndType(Color.BLACK, Type.ROOK));
        assertEquals(1, board.countPiecesByColorAndType(Color.BLACK, Type.KING));
        assertEquals(1, board.countPiecesByColorAndType(Color.BLACK, Type.QUEEN));

        assertEquals(8, board.countPiecesByColorAndType(Color.WHITE, Type.PAWN));
        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.BISHOP));
        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.KNIGHT));
        assertEquals(2, board.countPiecesByColorAndType(Color.WHITE, Type.ROOK));
        assertEquals(1, board.countPiecesByColorAndType(Color.WHITE, Type.KING));
        assertEquals(1, board.countPiecesByColorAndType(Color.WHITE, Type.QUEEN));
    }

    @Test
    void findPiece() throws Exception {
        board.initialize();
        System.out.println(board.findPiece("a8"));

        assertEquals(PieceFactory.createBlackRook(), board.findPiece("a8"));
        assertEquals(PieceFactory.createBlackRook(), board.findPiece("h8"));
        assertEquals(PieceFactory.createWhiteRook(), board.findPiece("a1"));
        assertEquals(PieceFactory.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        Piece sourcePiece = board.findPiece("b2");
        board.move(sourcePosition, targetPosition);
        // 보드판 초기상탱
        assertEquals(sourcePiece.getType(), Type.PAWN);
        assertEquals(sourcePiece, board.findPiece("b3"));
    }

    @Test
    void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        addPiece("f2", PieceFactory.createWhitePawn());
        addPiece("f3", PieceFactory.createWhitePawn());
        addPiece("g2", PieceFactory.createWhitePawn());
        addPiece("e1", PieceFactory.createWhiteRook());
        addPiece("f1", PieceFactory.createWhiteKing());

        board.print();
        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Test
    void sortPiecesByScoreDescendingOrder() {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        List<Piece> allPiecesSortByPoint = board.findAllPiecesSortByPoint(Color.BLACK,
                PieceComparatorFactory.DESCENDING);

        assertEquals(Type.QUEEN, allPiecesSortByPoint.get(0).getType());
        assertEquals(Type.ROOK, allPiecesSortByPoint.get(1).getType());
        assertEquals(Type.PAWN, allPiecesSortByPoint.get(2).getType());
        assertEquals(Type.KING, allPiecesSortByPoint.get(3).getType());
    }

    @Test
    void sortPiecesByScoreAscendigOrder() {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        List<Piece> allPiecesSortByPoint = board.findAllPiecesSortByPoint(Color.BLACK,
                PieceComparatorFactory.ASCENDING);

        assertEquals(Type.KING, allPiecesSortByPoint.get(0).getType());
        assertEquals(Type.PAWN, allPiecesSortByPoint.get(1).getType());
        assertEquals(Type.ROOK, allPiecesSortByPoint.get(2).getType());
        assertEquals(Type.QUEEN, allPiecesSortByPoint.get(3).getType());
    }
}