package chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.StringUtils.appendNewLine;

import chess.board.Board;
import chess.calculator.DefaultPointCalculateStrategy;
import chess.calculator.PointCalculator;
import chess.calculator.SameFilePawnPointCalculateStrategy;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.pieces.PieceFactory;
import chess.pieces.Position;
import chess.sorter.Direction;
import chess.sorter.PointSorter;
import chess.sorter.Sorter;
import chess.view.ChessView;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

    private static Piece whitePawn;

    private static Piece blackPawn;

    private static Piece whiteRook;

    private static Piece blackRook;

    private static Piece whiteKnight;

    private static Piece blackKnight;

    private static Piece whiteBishop;

    private static Piece blackBishop;

    private static Piece whiteQueen;

    private static Piece blackQueen;

    private static Piece whiteKing;

    private static Piece blackKing;

    private static Piece blankPiece;

    private static ChessView chessView;

    private static PointCalculator calculator;

    private Board board;


    @BeforeAll
    static void beforeAll() {
        whitePawn = PieceFactory.createWhitePawn();
        blackPawn = PieceFactory.createBlackPawn();
        whiteRook = PieceFactory.createWhiteRook();
        blackRook = PieceFactory.createBlackRook();
        whiteKnight = PieceFactory.createWhiteKnight();
        blackKnight = PieceFactory.createBlackKnight();
        whiteBishop = PieceFactory.createWhiteBishop();
        blackBishop = PieceFactory.createBlackBishop();
        whiteQueen = PieceFactory.createWhiteQueen();
        blackQueen = PieceFactory.createBlackQueen();
        whiteKing = PieceFactory.createWhiteKing();
        blackKing = PieceFactory.createBlackKing();
        blankPiece = PieceFactory.createBlankPiece();
        chessView = new ChessView();
        calculator = new PointCalculator(List.of(
                new DefaultPointCalculateStrategy(),
                new SameFilePawnPointCalculateStrategy()
        ));
    }

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @DisplayName("체스판 초기화한다.")
    @Test
    void initialize() {
        board.initialize();
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                chessView.showBoard(board)
        );
    }

    @DisplayName("주어진 위치의 기물을 조회한다.")
    @ParameterizedTest
    @MethodSource
    void findPiece(List<Position> positions, Piece expectedPiece) {
        board.initialize();
        positions.forEach(pos -> {
            assertEquals(expectedPiece, board.findPiece(pos));
        });
    }

    private static Stream<Arguments> findPiece() {
        return Stream.of(
                Arguments.of(List.of(Position.a1, Position.h1), whiteRook,
                        Arguments.of(List.of(Position.b1, Position.g1), whiteKnight),
                        Arguments.of(List.of(Position.c1, Position.f1), whiteBishop),
                        Arguments.of(List.of(Position.d1), whiteQueen),
                        Arguments.of(List.of(Position.e1), whiteKing),

                        Arguments.of(List.of(
                                        Position.a2, Position.b2, Position.c2, Position.d2,
                                        Position.e2, Position.f2, Position.g2, Position.h2)
                                , whitePawn),

                        Arguments.of(List.of(
                                        Position.a7, Position.b7, Position.c7, Position.d7,
                                        Position.e7, Position.f7, Position.g7, Position.h7)
                                , blackPawn),

                        Arguments.of(List.of(Position.a8, Position.h8), blackRook),
                        Arguments.of(List.of(Position.b8, Position.g8), blackKnight),
                        Arguments.of(List.of(Position.c8, Position.f8), blackBishop),
                        Arguments.of(List.of(Position.d8), blackQueen),
                        Arguments.of(List.of(Position.e8), blackKing)
                ));
    }

    @DisplayName("기물이 없는 위치를 조회한다.")
    @ParameterizedTest
    @MethodSource
    void findBlankPiece(Position blankPosition) {
        board.initialize();
        assertEquals(blankPiece, board.findPiece(blankPosition));
    }

    private static Stream<Arguments> findBlankPiece() {
        return Stream.of(
                Arguments.of(Position.a3), Arguments.of(Position.b3), Arguments.of(Position.c3),
                Arguments.of(Position.d3),
                Arguments.of(Position.e3), Arguments.of(Position.f3), Arguments.of(Position.g3),
                Arguments.of(Position.h3),
                Arguments.of(Position.a4), Arguments.of(Position.b4), Arguments.of(Position.c4),
                Arguments.of(Position.d4),
                Arguments.of(Position.e4), Arguments.of(Position.f4), Arguments.of(Position.g4),
                Arguments.of(Position.h4),
                Arguments.of(Position.a5), Arguments.of(Position.b5), Arguments.of(Position.c5),
                Arguments.of(Position.d5),
                Arguments.of(Position.e5), Arguments.of(Position.f5), Arguments.of(Position.g5),
                Arguments.of(Position.h5),
                Arguments.of(Position.a6), Arguments.of(Position.b6), Arguments.of(Position.c6),
                Arguments.of(Position.d6),
                Arguments.of(Position.e6), Arguments.of(Position.f6), Arguments.of(Position.g6),
                Arguments.of(Position.h6)
        );
    }

    @DisplayName("체스 프로그램 점수를 계산한다. - whitePawn 같은 열 존재")
    @Test
    void givenWhitePawnOnSameFile_calculatePoint() {
        board.initializeEmpty();
        addPiece(Position.b6, blackPawn);
        addPiece(Position.e6, blackQueen);
        addPiece(Position.b8, blackKing);
        addPiece(Position.c8, blackRook);

        addPiece(Position.f2, whitePawn);
        addPiece(Position.g2, whitePawn);
        addPiece(Position.e1, whiteRook);
        addPiece(Position.f1, whiteKing);

        assertEquals(15.0, calculator.calculate(board, Color.BLACK), 0.1);
        assertEquals(7.0, calculator.calculate(board, Color.WHITE), 0.1);
    }

    @DisplayName("체스 프로그램 점수를 계산한다. - 루카스 예시")
    @Test
    void givenExampleOnLucas_calculatePoint() {
        board.initializeEmpty();
        addPiece(Position.b8, blackKing);
        addPiece(Position.c8, blackRook);
        addPiece(Position.a7, blackPawn);
        addPiece(Position.c7, blackPawn);
        addPiece(Position.d7, blackBishop);
        addPiece(Position.b6, blackPawn);
        addPiece(Position.e6, blackQueen);

        addPiece(Position.f4, whiteKnight);
        addPiece(Position.g4, whiteQueen);
        addPiece(Position.f3, whitePawn);
        addPiece(Position.h3, whitePawn);
        addPiece(Position.f2, whitePawn);
        addPiece(Position.g2, whitePawn);
        addPiece(Position.e1, whiteRook);
        addPiece(Position.f1, whiteKing);

        chessView.showBoard(board);
        assertEquals(20.0, calculator.calculate(board, Color.BLACK), 0.1);
        assertEquals(19.5, calculator.calculate(board, Color.WHITE), 0.1);
    }

    @DisplayName("체스 프로그램 점수를 계산한다.-모든 기물 세팅")
    @Test
    void givenAllPieces_calculatePoint() {
        board.initialize();

        assertEquals(38, calculator.calculate(board, Color.BLACK), 0.1);
        assertEquals(38, calculator.calculate(board, Color.WHITE), 0.1);
    }

    @DisplayName("체스 프로그램 점수를 계산한다.- 기물 없음")
    @Test
    void givenNoPiece_calculatePoint() {
        board.initializeEmpty();

        assertEquals(0.0, calculator.calculate(board, Color.BLACK), 0.1);
        assertEquals(0.0, calculator.calculate(board, Color.WHITE), 0.1);
    }


    private void addPiece(Position position, Piece piece) {
        board.move(position, piece);
    }

    @DisplayName("기물을 점수가 높은 순으로 정렬한다.")
    @Test
    void sortPiecesByPointAsc() {
        board.initialize();
        Sorter sorter = new PointSorter();
        List<Piece> sortedWhitePieces = board.sort(Color.WHITE, sorter, Direction.DESC);

        assertEquals(16, sortedWhitePieces.size());
        assertTrue(sortedWhitePieces.get(0).getDefaultPoint() >
                sortedWhitePieces.get(sortedWhitePieces.size() - 1).getDefaultPoint());

        List<Piece> sortedBlackPieces = board.sort(Color.WHITE, sorter, Direction.DESC);
        assertEquals(16, sortedBlackPieces.size());
        assertTrue(sortedBlackPieces.get(0).getDefaultPoint() >
                sortedBlackPieces.get(sortedBlackPieces.size() - 1).getDefaultPoint());
    }

    @DisplayName("기물을 점수가 낮은 순으로 정렬한다.")
    @Test
    void sortPiecesByPointDesc() {
        board.initialize();
        Sorter sorter = new PointSorter();
        List<Piece> sortedWhitePieces = board.sort(Color.WHITE, sorter, Direction.ASC);

        assertEquals(16, sortedWhitePieces.size());
        assertTrue(sortedWhitePieces.get(0).getDefaultPoint() <
                sortedWhitePieces.get(sortedWhitePieces.size() - 1).getDefaultPoint());

        List<Piece> sortedBlackPieces = board.sort(Color.BLACK, sorter, Direction.ASC);
        assertEquals(16, sortedBlackPieces.size());
        assertTrue(sortedBlackPieces.get(0).getDefaultPoint() <
                sortedBlackPieces.get(sortedBlackPieces.size() - 1).getDefaultPoint());
    }

    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환한다.")
    @Test
    void countPiece() {
        board.initializeEmpty();

        addPiece(Position.b8, blackKing);
        addPiece(Position.c8, blackRook);
        addPiece(Position.a7, blackPawn);
        addPiece(Position.c7, blackPawn);
        addPiece(Position.d7, blackBishop);
        addPiece(Position.b6, blackPawn);
        addPiece(Position.e6, blackQueen);

        addPiece(Position.f4, whiteKnight);
        addPiece(Position.g4, whiteQueen);
        addPiece(Position.f3, whitePawn);
        addPiece(Position.g2, whitePawn);
        addPiece(Position.e1, whiteRook);
        addPiece(Position.f1, whiteKing);

        assertEquals(3, board.pieceCount(Type.PAWN, Color.BLACK));
        assertEquals(1, board.pieceCount(Type.ROOK, Color.BLACK));
        assertEquals(1, board.pieceCount(Type.BISHOP, Color.BLACK));
        assertEquals(1, board.pieceCount(Type.QUEEN, Color.BLACK));

        assertEquals(2, board.pieceCount(Type.PAWN, Color.WHITE));
        assertEquals(1, board.pieceCount(Type.KNIGHT, Color.WHITE));
        assertEquals(1, board.pieceCount(Type.QUEEN, Color.WHITE));
        assertEquals(1, board.pieceCount(Type.KING, Color.WHITE));
        assertEquals(1, board.pieceCount(Type.ROOK, Color.WHITE));
    }

    @DisplayName("기물을 현재 위치에서 다른 위치로 이동한다.")
    @Test
    void move() {
        board.initialize();

        Position sourcePosition = Position.b2;
        Position targetPosition = Position.b3;

        board.move(sourcePosition, targetPosition);

        assertEquals(blankPiece, board.findPiece(sourcePosition));
        assertEquals(whitePawn, board.findPiece(targetPosition));
    }

    @DisplayName("기물을 현재 위치에서 현재 위치로 이동할 수 없다.")
    @Test
    void whenMoveToCurrentPosition_thenFail() {
        board.initialize();

        Position sourcePosition = Position.b2;

        assertThrows(IllegalArgumentException.class, () -> board.move(sourcePosition, sourcePosition));
    }
}
