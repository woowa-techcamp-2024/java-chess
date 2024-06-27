package chess;

import chess.pieces.Piece;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String position = "b5";
        Piece piece = Piece.createBlack(Piece.Type.ROOK);
        game.add(position, piece);

        assertEquals(piece, game.findPiece(position));
        System.out.println(game.showBoard());
    }

    @Test
    @DisplayName("체스 프로그램 점수 계산하기")
    void calcScore() {
        game.initializeEmpty();

        game.add("b6", Piece.createBlack(Piece.Type.PAWN));
        game.add("e6", Piece.createBlack(Piece.Type.QUEEN));
        game.add("b8", Piece.createBlack(Piece.Type.KING));
        game.add("c8", Piece.createBlack(Piece.Type.ROOK));

        game.add("f2", Piece.createWhite(Piece.Type.PAWN));
        game.add("g2", Piece.createWhite(Piece.Type.PAWN));
        game.add("e1", Piece.createWhite(Piece.Type.ROOK));
        game.add("f1", Piece.createWhite(Piece.Type.KING));

        assertEquals(15.0, game.calculatePoint(Piece.Color.BLACK), 0.01);
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
        assertEquals(Piece.createBlank(new Position(sourcePosition)), game.findPiece(sourcePosition));
        assertEquals(Piece.createWhite(Piece.Type.PAWN, new Position(targetPosition)), game.findPiece(targetPosition));
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
                Arguments.arguments(Piece.of(Piece.Color.WHITE.name(), Piece.Type.KNIGHT.getWhiteRepresentation(), Piece.Type.KNIGHT.getDefaultPoint()), "a1"),
                Arguments.arguments(Piece.of(Piece.Color.WHITE.name(), Piece.Type.PAWN.getWhiteRepresentation(), Piece.Type.PAWN.getDefaultPoint()), "b1")
        );
    }
}
