package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.PieceFactory;
import com.woopaca.javachess.pieces.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.woopaca.javachess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 테스트")
public class BoardTest {

    Board board;
    ChessView chessView;
    PointProcessor pointProcessor;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessView = new ChessView(board);
        pointProcessor = new PointProcessor(board);
    }

    @DisplayName("체스판을 초기화하면 흰색 폰과 검은색 폰이 추가된다.")
    @Test
    void initialize() {
        board.initialize();
        assertThat(chessView.getWhitePawnsResult()).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(chessView.getBlackPawnsResult()).isEqualTo("♟♟♟♟♟♟♟♟");

        System.out.println(chessView.showBoard());
    }

    @DisplayName("체스판의 모든 기물의 상태를 볼 수 있다.")
    @Test
    void print() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);

        String blankRow = appendNewLine("........");
        assertThat(chessView.showBoard()).isEqualTo(
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

        assertThat(board.findPiece("a8")).isEqualTo(PieceFactory.createBlackRook(new Position("a8")));
        Piece findPiece = board.findPiece("h8");
        assertThat(findPiece).isEqualTo(PieceFactory.createBlackRook(new Position("h8")));
        assertThat(board.findPiece("a1")).isEqualTo(PieceFactory.createWhiteRook(new Position("a1")));
        assertThat(board.findPiece("h1")).isEqualTo(PieceFactory.createWhiteRook(new Position("h1")));
    }

    @DisplayName("체스판에 존재하는 특정 색상 기물들의 점수를 계산할 수 있다.")
    @Test
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn(new Position("b6")));
        addPiece("e6", PieceFactory.createBlackQueen(new Position("e6")));
        addPiece("b8", PieceFactory.createBlackKing(new Position("b8")));
        addPiece("c8", PieceFactory.createBlackRook(new Position("c8")));

        addPiece("f2", PieceFactory.createWhitePawn(new Position("f2")));
        addPiece("g2", PieceFactory.createWhitePawn(new Position("g2")));
        addPiece("e1", PieceFactory.createWhiteRook(new Position("e1")));
        addPiece("f1", PieceFactory.createWhiteKing(new Position("f1")));

        assertThat(pointProcessor.calculatePoint(Color.BLACK)).isEqualTo(15.0);
        assertThat(pointProcessor.calculatePoint(Color.WHITE)).isEqualTo(7.0);
    }

    @DisplayName("체스판에 존재하는 특정 색상 기물들을 점수를 기준으로 정렬할 수 있다.")
    @Test
    void sortPieces() {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn(new Position("b6")));
        addPiece("e6", PieceFactory.createBlackQueen(new Position("e6")));
        addPiece("b8", PieceFactory.createBlackKing(new Position("b8")));
        addPiece("c8", PieceFactory.createBlackRook(new Position("c8")));

        addPiece("f2", PieceFactory.createWhitePawn(new Position("f2")));
        addPiece("g2", PieceFactory.createWhitePawn(new Position("g2")));
        addPiece("e1", PieceFactory.createWhiteRook(new Position("e1")));
        addPiece("f1", PieceFactory.createWhiteKing(new Position("f1")));

        assertThat(pointProcessor.sortPiecesByPoint(Color.BLACK).get(0)).isEqualTo(PieceFactory.createBlackKing(new Position("b8")));
        assertThat(pointProcessor.sortPiecesByPoint(Color.BLACK).get(1)).isEqualTo(PieceFactory.createBlackPawn(new Position("b6")));
        assertThat(pointProcessor.sortPiecesByPointDescending(Color.BLACK).get(0)).isEqualTo(PieceFactory.createBlackQueen(new Position("e6")));
        assertThat(pointProcessor.sortPiecesByPointDescending(Color.BLACK).get(1)).isEqualTo(PieceFactory.createBlackRook(new Position("c8")));

        assertThat(pointProcessor.sortPiecesByPoint(Color.WHITE).get(0)).isEqualTo(PieceFactory.createWhiteKing(new Position("f1")));
        assertThat(pointProcessor.sortPiecesByPoint(Color.WHITE).get(1)).isEqualTo(PieceFactory.createWhitePawn(new Position("f2")));
        assertThat(pointProcessor.sortPiecesByPointDescending(Color.WHITE).get(0)).isEqualTo(PieceFactory.createWhiteRook(new Position("e1")));
        assertThat(pointProcessor.sortPiecesByPointDescending(Color.WHITE).get(1)).isEqualTo(PieceFactory.createWhitePawn(new Position("f2")));
    }

    private void addPiece(String fileRank, Piece piece) {
        board.placePiece(new Position(fileRank), piece);
    }

    @DisplayName("체스판 위의 기물을 이동시킬 수 있다.")
    @Test
    void move() {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);

        assertThat(board.findPiece(sourcePosition)).isEqualTo(PieceFactory.createBlank(new Position(sourcePosition)));
        assertThat(board.findPiece(targetPosition)).isEqualTo(PieceFactory.createWhitePawn(new Position(targetPosition)));
    }

}
