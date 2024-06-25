package com.example.demo.context;

import com.example.demo.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("보드를 생성하면 폰의 초기 상태가 설정되어 있다.")
    public void create_pawn() {
        Board board = Board.createBoard();

        for (File file : File.values()) {
            Piece black = board.getPiece(Rank.TWO, file);
            assertThat(black).isInstanceOf(Pawn.class);
            assertThat(black.getColor()).isEqualTo(Color.WHITE);

            Piece white = board.getPiece(Rank.SEVEN, file);
            assertThat(white).isInstanceOf(Pawn.class);
            assertThat(white.getColor()).isEqualTo(Color.BLACK);
        }
    }

    @DisplayName("보드를 생성하면 킹의 초기 상태가 설정되어 있다.")
    @ParameterizedTest
    @MethodSource("kingLocations")
    public void create_king(Rank rank, File file, Color color) {
        Board board = Board.createBoard();

        Piece piece = board.getPiece(rank, file);
        assertThat(piece).isInstanceOf(King.class);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private static Stream<Arguments> kingLocations() {
        return Stream.of(
                Arguments.of(Rank.ONE, File.D, Color.WHITE),
                Arguments.of(Rank.EIGHT, File.D, Color.BLACK)
        );
    }

    @DisplayName("보드를 생성하면 퀸의 초기 상태가 설정되어 있다.")
    @ParameterizedTest
    @MethodSource("queenLocations")
    public void create_queen(Rank rank, File file, Color color) {
        Board board = Board.createBoard();

        Piece piece = board.getPiece(rank, file);
        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private static Stream<Arguments> queenLocations() {
        return Stream.of(
                Arguments.of(Rank.ONE, File.E, Color.WHITE),
                Arguments.of(Rank.EIGHT, File.E, Color.BLACK)
        );
    }

    @DisplayName("보드를 생성하면 비숍의 초기 상태가 설정되어 있다.")
    @ParameterizedTest
    @MethodSource("bishopLocations")
    public void create_bishop(Rank rank, File file, Color color) {
        Board board = Board.createBoard();

        Piece piece = board.getPiece(rank, file);
        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private static Stream<Arguments> bishopLocations() {
        return Stream.of(
                Arguments.of(Rank.ONE, File.C, Color.WHITE),
                Arguments.of(Rank.ONE, File.F, Color.WHITE),
                Arguments.of(Rank.EIGHT, File.C, Color.BLACK),
                Arguments.of(Rank.EIGHT, File.F, Color.BLACK)
        );
    }

    @DisplayName("보드를 생성하면 나이트의 초기 상태가 설정되어 있다.")
    @ParameterizedTest
    @MethodSource("knightLocations")
    public void create_knight(Rank rank, File file, Color color) {
        Board board = Board.createBoard();

        Piece piece = board.getPiece(rank, file);
        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private static Stream<Arguments> knightLocations() {
        return Stream.of(
                Arguments.of(Rank.ONE, File.B, Color.WHITE),
                Arguments.of(Rank.ONE, File.G, Color.WHITE),
                Arguments.of(Rank.EIGHT, File.B, Color.BLACK),
                Arguments.of(Rank.EIGHT, File.G, Color.BLACK)
        );
    }

    @DisplayName("보드를 생성하면 룩의 초기 상태가 설정되어 있다.")
    @ParameterizedTest
    @MethodSource("rookLocations")
    public void create_rook(Rank rank, File file, Color color) {
        Board board = Board.createBoard();

        Piece piece = board.getPiece(rank, file);
        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private static Stream<Arguments> rookLocations() {
        return Stream.of(
                Arguments.of(Rank.ONE, File.A, Color.WHITE),
                Arguments.of(Rank.ONE, File.H, Color.WHITE),
                Arguments.of(Rank.EIGHT, File.A, Color.BLACK),
                Arguments.of(Rank.EIGHT, File.H, Color.BLACK)
        );
    }

    @Test
    @DisplayName("보드를 출력한다.")
    public void printBoard() {
        Board board = Board.createBoard();
        System.out.println(board);
    }

    @Test
    @DisplayName("퀸1 + 룩1 + 나이트1 + 비숍1 = 19.5점")
    public void calculateScoreCase1() {
        // given
        Board board = new Board();
        board.setPiece(Rank.ONE, File.A, new Queen(Color.WHITE));
        board.setPiece(Rank.ONE, File.B, new Rook(Color.WHITE));
        board.setPiece(Rank.ONE, File.C, new Knight(Color.WHITE));
        board.setPiece(Rank.ONE, File.D, new Bishop(Color.WHITE));

        // when
        float score = board.getScore(Color.WHITE);

        // then
        assertThat(score).isEqualTo(19.5f);
    }

    @Test
    @DisplayName("세로줄(File)에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.")
    public void calculateScoreCase2() {
        // given
        Board board = new Board();
        board.setPiece(Rank.TWO, File.A, new Pawn(Color.WHITE));
        board.setPiece(Rank.SEVEN, File.A, new Pawn(Color.WHITE));
        board.setPiece(Rank.FOUR, File.A, new Pawn(Color.WHITE));

        // when
        float score = board.getScore(Color.WHITE);

        // then
        assertThat(score).isEqualTo(3.0f);
    }

    @Test
    @DisplayName("보드의 기물 점수가 높은 순으로 정렬하여 반환한다.")
    public void getSortedPieceCollection() {
        // given
        Board board = new Board();

        var queen = new Queen(Color.WHITE);
        var rook = new Rook(Color.WHITE);
        var bishop = new Bishop(Color.WHITE);
        var knight = new Knight(Color.WHITE);
        var pawn1 = new Pawn(Color.WHITE);
        var pawn2 = new Pawn(Color.WHITE);

        board.setPiece(Rank.ONE, File.A, queen);
        board.setPiece(Rank.ONE, File.B, rook);
        board.setPiece(Rank.ONE, File.C, knight);
        board.setPiece(Rank.ONE, File.D, bishop);
        board.setPiece(Rank.TWO, File.A, pawn1);
        board.setPiece(Rank.SEVEN, File.A, pawn2);

        // when
        Collection<Piece> pieces = board.getPieces(Color.WHITE);

        // then
        assertThat(pieces.stream().map(Piece::toString).toList())
                .containsExactly(queen.toString(), rook.toString(), bishop.toString(), knight.toString(), pawn1.toString(), pawn2.toString());
    }

}