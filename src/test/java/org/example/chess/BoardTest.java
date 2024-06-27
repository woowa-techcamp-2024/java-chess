package org.example.chess;

import static org.example.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.example.pieces.Piece;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    private Board board;
    private static final int boardSize = 8;

    @BeforeEach
    public void setUpBoard() {
        board = new Board();
    }

    @Test
    public void create() throws Exception {
        board.initialize();

        assertEquals(32, board.nonEmptyPiece());
        String blankRank = appendNewLine("........");
        assertEquals(
            appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") +
                appendNewLine("♖♘♗♕♔♗♘♖"),
            board.showBoard());

    }


    @ParameterizedTest
    @DisplayName("선택한 기물의 개수를 확인한다")
    @MethodSource("countByQueryArgument")
    public void countByQuery(
        Color color,
        Type type,
        int count
    ) {
        board.initialize();
        assertEquals(count, board.countByQuery(color, type));
    }


    @Test
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece(new Position("a8")));
        assertEquals(Piece.createBlackRook(), board.findPiece(new Position("h8")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(new Position("a1")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(new Position("h1")));
    }

    @Test
    public void move() throws Exception {
        Position position = new Position("b5");
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    private static Stream<Arguments> countByQueryArgument() {
        // 색상, 기물 종류, 개수
        return Stream.of(
            Arguments.arguments(Color.BLACK, Type.PAWN, 8),
            Arguments.arguments(Color.WHITE, Type.PAWN, 8),
            Arguments.arguments(Color.BLACK, Type.ROOK, 2),
            Arguments.arguments(Color.WHITE, Type.ROOK, 2),
            Arguments.arguments(Color.BLACK, Type.KNIGHT, 2),
            Arguments.arguments(Color.WHITE, Type.KNIGHT, 2),
            Arguments.arguments(Color.BLACK, Type.BISHOP, 2),
            Arguments.arguments(Color.WHITE, Type.BISHOP, 2),
            Arguments.arguments(Color.BLACK, Type.QUEEN, 1),
            Arguments.arguments(Color.WHITE, Type.QUEEN, 1),
            Arguments.arguments(Color.BLACK, Type.KING, 1),
            Arguments.arguments(Color.WHITE, Type.KING, 1),
            Arguments.arguments(Color.NO_COLOR, Type.NO_PIECE, 32)
        );
    }

    @Test
    public void calculatePoint() throws Exception {

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("새로로 폰이 있을 때 테스트")
    public void calculatePoint2() throws Exception {
        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("f4", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        // 점수 계산 확인
        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(6.5, board.calculatePoint(Color.WHITE), 0.01);

        // 체스판 출력
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("점수 높은 순서대로 정렬")
    public void sort() throws Exception {
        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("f4", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());
        addPiece("f5", Piece.createWhiteQueen());

        // 기대 점수
        Double[] expectedBlackPoints = {9.0, 5.0, 1.0, 0.0};
        Double[] expectedWhitePoints = {9.0, 5.0, 1.0, 1.0, 1.0, 0.0};

        // 검은색 점수가 높은 순서대로 정렬. 확인하는 법은 Piece의 점수를 pick해준다.
        assertArrayEquals(expectedBlackPoints,
            board.sortByPoint(Color.BLACK).stream().map(Piece::getPoint).toArray());

        //흰색
        assertArrayEquals(expectedWhitePoints,
            board.sortByPoint(Color.WHITE).stream().map(Piece::getPoint).toArray());
    }

    private void addPiece(String position, Piece piece) {
        board.move(new Position(position), piece);
    }
}
