package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.Piece.Color;
import com.woopaca.javachess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.woopaca.javachess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 테스트")
public class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @DisplayName("체스판을 초기화하면 흰색 폰과 검은색 폰이 추가된다.")
    @Test
    void initialize() {
        board.initialize();
        assertThat(board.getWhitePawnsResult()).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(board.getBlackPawnsResult()).isEqualTo("♟♟♟♟♟♟♟♟");

        System.out.println(board.print());
    }

    @DisplayName("체스판의 모든 기물의 상태를 볼 수 있다.")
    @Test
    void print() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);

        String blankRow = appendNewLine("........");
        assertThat(board.showBoard()).isEqualTo(
                appendNewLine("♜♞♝♛♚♝♞♜") +
                        appendNewLine("♟♟♟♟♟♟♟♟") +
                        blankRow + blankRow + blankRow + blankRow +
                        appendNewLine("♙♙♙♙♙♙♙♙") +
                        appendNewLine("♖♘♗♕♔♗♘♖")
        );
    }

    @DisplayName("체스판의 기물 개수를 조회할 수 있다.")
    @Test
    void piecesCount() {
        board.initialize();

        assertThat(board.getPiecesCount(Color.WHITE, Type.PAWN)).isEqualTo(8);
        assertThat(board.getPiecesCount(Color.WHITE, Type.ROOK)).isEqualTo(2);
        assertThat(board.getPiecesCount(Color.WHITE, Type.KING)).isEqualTo(1);
        assertThat(board.getPiecesCount(Color.BLACK, Type.PAWN)).isEqualTo(8);
        assertThat(board.getPiecesCount(Color.BLACK, Type.ROOK)).isEqualTo(2);
        assertThat(board.getPiecesCount(Color.BLACK, Type.KING)).isEqualTo(1);
    }

    @DisplayName("특정 위치의 기물을 찾을 수 있다.")
    @Test
    void findPiece() {
        board.initialize();

        assertThat(board.findPiece("a8")).isEqualTo(Piece.createBlackRook());
        assertThat(board.findPiece("h8")).isEqualTo(Piece.createBlackRook());
        assertThat(board.findPiece("a1")).isEqualTo(Piece.createWhiteRook());
        assertThat(board.findPiece("h1")).isEqualTo(Piece.createWhiteRook());
    }

    @DisplayName("임의의 기물을 체스판 위에 추가(이동)할 수 있다.")
    @Test
    void move() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertThat(board.findPiece(position)).isEqualTo(piece);

        position = "e2";
        piece = Piece.createWhiteKing();
        board.move(position, piece);

        assertThat(board.findPiece(position)).isEqualTo(piece);

        System.out.println(board.showBoard());
    }

    @DisplayName("체스판에 존재하는 특정 색상 기물들의 점수를 계산할 수 있다.")
    @Test
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertThat(board.calculatePoint(Color.BLACK)).isEqualTo(15.0);
        assertThat(board.calculatePoint(Color.WHITE)).isEqualTo(7.0);
    }

    @DisplayName("체스판에 존재하는 특정 색상 기물들을 점수를 기준으로 정렬할 수 있다.")
    @Test
    void sortPieces() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertThat(board.sortPiecesByPoint(Color.BLACK).get(0)).isEqualTo(Piece.createBlackKing());
        assertThat(board.sortPiecesByPoint(Color.BLACK).get(1)).isEqualTo(Piece.createBlackPawn());
        assertThat(board.sortPiecesByPointDescending(Color.BLACK).get(0)).isEqualTo(Piece.createBlackQueen());
        assertThat(board.sortPiecesByPointDescending(Color.BLACK).get(1)).isEqualTo(Piece.createBlackRook());

        assertThat(board.sortPiecesByPoint(Color.WHITE).get(0)).isEqualTo(Piece.createWhiteKing());
        assertThat(board.sortPiecesByPoint(Color.WHITE).get(1)).isEqualTo(Piece.createWhitePawn());
        assertThat(board.sortPiecesByPointDescending(Color.WHITE).get(0)).isEqualTo(Piece.createWhiteRook());
        assertThat(board.sortPiecesByPointDescending(Color.WHITE).get(1)).isEqualTo(Piece.createWhitePawn());
    }

    private void addPiece(String fileRank, Piece piece) {
        board.move(fileRank, piece);
    }

}
