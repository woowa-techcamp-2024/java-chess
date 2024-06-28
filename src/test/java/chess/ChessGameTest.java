package chess;

import chess.pieces.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.stream.Stream;

import static chess.ChessGame.initializeCmdToPos;
import static chess.pieces.Piece.Color.*;
import static org.junit.jupiter.api.Assertions.*;
import static utils.StringUtils.appendNewLine;

class ChessGameTest {

    ChessGame game;
    String initializedBoard;

    @BeforeEach
    void setUp() {
        game = new ChessGame();
        initializedBoard =
                """
                   ........
                   PPPPPPPP
                   ........
                   ........
                   ........
                   ........
                   pppppppp
                   ........
                   """;
    }

    @BeforeAll
    static void setUpBoard() {
        initializeCmdToPos();
    }

    @Test
    void create() {
        game.initialize();
        System.out.println(game.showBoard());
        assertEquals(
                appendNewLine("RNBQKBNR8") +
                        appendNewLine("PPPPPPPP7") +
                        appendNewLine("........6") +
                        appendNewLine("........5") +
                        appendNewLine("........4") +
                        appendNewLine("........3") +
                        appendNewLine("pppppppp2") +
                        appendNewLine("rnbqkbnr1") +
                        appendNewLine("abcdefgh"),
                game.showBoard());
    }


    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가")
    void addPiece_by_pos() {
        game.initializeEmpty();
        String pos = "b5";
        var position = CommandChanger.getPosition(pos);
        Piece piece = Piece.of(Rook.class, BLACK, position);
        game.add(pos, piece);

        assertEquals(piece, game.findPiece(pos));
        System.out.println(game.showBoard());
    }

    @Test
    @DisplayName("체스 프로그램 점수 계산하기")
    void calcScore() {
        game.initializeEmpty();

        game.add("b6", Piece.of(Pawn.class, BLACK));
        game.add("e6", Piece.of(Queen.class, BLACK));
        game.add("b8", Piece.of(King.class, BLACK));
        game.add("c8", Piece.of(Rook.class, BLACK));

        game.add("f2", Piece.of(Pawn.class, WHITE));
        game.add("g2", Piece.of(Pawn.class, WHITE));
        game.add("e1", Piece.of(Rook.class, WHITE));
        game.add("f1", Piece.of(King.class, WHITE));

        assertEquals(15.0, game.calculatePoint(BLACK), 0.01);
        assertEquals(7.0, game.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(game.showBoard());


    }

    @Test
    @DisplayName("기물의 점수가 높은 순으로 정렬")
    void sortByScore() {
        game.initialize();

        List<Piece> whitePieces = new ArrayList<>(game.getWhitePieces());
        whitePieces.sort(Comparator.comparing(Piece::getDefaultScore).reversed());
        for (Piece whitePiece : whitePieces) {

            System.out.println(whitePiece.getDefaultScore());
        }
    }


    @Test
    @DisplayName("기물의 이동 구현")
    void move() {
        game.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";

        game.move(sourcePosition, targetPosition);

        System.out.println(game.showBoard());
        Piece piece = Piece.of(Pawn.class, WHITE, new Position(targetPosition));

        assertEquals(Piece.of(NoPiece.class, NOCOLOR, new Position(sourcePosition)), game.findPiece(sourcePosition));
        assertEquals(piece, game.findPiece(targetPosition));
    }


    @Test
    @DisplayName("주어진 위치의 기물을 조회")
    void findPiece_by_pos() {
        game.initialize();
        assertAll(
                () -> assertEquals(Piece.of(Rook.class, BLACK, "a8"), game.findPiece("a8")),
                () -> assertEquals(Piece.of(Rook.class, BLACK, "h8"), game.findPiece("h8")),
                () -> assertEquals(Piece.of(Rook.class, WHITE, "a1"), game.findPiece("a1")),
                () -> assertEquals(Piece.of(Rook.class, WHITE, "h1"), game.findPiece("h1"))
        );
    }


    @ParameterizedTest
    @DisplayName("Piece add find ")
    @MethodSource("providedParam")
    void addPiece(Piece piece, String pos) {
        game.initializeEmpty();
        game.add(pos, piece);

        assertEquals(piece, game.findPiece(pos));
    }

    static Stream<Arguments> providedParam() {
        return Stream.of(
                Arguments.arguments(Piece.of(Knight.class, WHITE, "a1"), "a1"),
                Arguments.arguments(Piece.of(Knight.class, WHITE, "b1"), "b1")
        );
    }

    @Test
    @DisplayName("이동하려는 위치에 같은 편의 기물이 있는지 확인")
    void checkSameColor() {
        game.initialize();

        assertFalse(game.isAvailableToMove(WHITE, "e2"));
    }

    @Test
    @DisplayName("king move")
    void moveKing() {
        game.initialize();

        assertThrows(IllegalArgumentException.class, () -> game.move("e1", "e2"));

        assertTrue(game.findPiece("e1") instanceof King);
        assertTrue(game.findPiece("e2") instanceof Pawn);
    }

    @Test
    @DisplayName("king이 체스판 밖으로 이동하는 경우 처리")
    void checkInBoard() {
        game.initialize();

        Piece p = game.findPiece("a1");

        assertThrows(IllegalArgumentException.class, () -> game.move("a1", "a0"));

        assertEquals(game.findPiece("a1"), p);
    }

    @ParameterizedTest
    @MethodSource("queenMove")
    @DisplayName("Queen 이동 구현")
    void moveQueen(String s, String t) {
        game.initializeEmpty();
        game.add(s, Piece.of(Queen.class, BLACK));

        game.move(s, t);

        game.showBoard();

        assertEquals(game.findPiece(t).getClass(), Queen.class);
    }

    static Stream<Arguments> queenMove() {
        return Stream.of(
                Arguments.arguments("e1", "e3"),
                Arguments.arguments("a1", "h8")
        );
    }


    @Test
    @DisplayName("Knight 이동 구현")
    void moveKnight() {
        game.initialize();

        assertThrows(IllegalArgumentException.class, () -> game.move("b1", "d2"));

        game.move("b1", "c3");
        assertEquals(Knight.class, game.findPiece("c3").getClass());
    }

    @Test
    @DisplayName("Bishop 이동 구현")
    void moveBishop() {
        String source = "a1";
        String target = "g7";

        game.initializeEmpty();

        game.add(source, Piece.of(Bishop.class, WHITE, source));

        game.move(source, target);

        assertEquals(Bishop.class, game.findPiece(target).getClass());
    }

    @Test
    @DisplayName("Rook 이동 구현")
    void moveRook() {
        String source = "a1";
        String target = "a8";

        game.initializeEmpty();

        game.add(source, Piece.of(Rook.class, WHITE, source));

        game.move(source, target);

        assertEquals(Rook.class, game.findPiece(target).getClass());
    }

    @Test
    @DisplayName("Pawn 이동 구현")
    void movePawn() {
        String source = "a2";
        String target1 = "a4";
        String target2 = "a5";

        game.initialize();

        game.move(source, target1);

        assertEquals(Pawn.class, game.findPiece(target1).getClass());

        game.move(target1, target2);

        assertEquals(Pawn.class, game.findPiece(target2).getClass());

        assertThrows(IllegalArgumentException.class, () -> game.move(target2, "a7"));
    }
}
