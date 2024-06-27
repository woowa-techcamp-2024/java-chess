package wootecamp.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wootecamp.chess.pieces.Piece;

import static org.assertj.core.api.Assertions.assertThat;
import static wootecamp.chess.util.StringUtils.appendNewline;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 초기화한다.")
    void initialize() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);

        String blankRank = appendNewline("........");
        assertThat(board.showBoard()).isEqualTo(
                appendNewline("RNBQKBNR") +
                        appendNewline("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewline("pppppppp") +
                        appendNewline("rnbqkbnr")
        );
    }

    @Test
    @DisplayName("체스판의 기물 개수를 확인한다.")
    void pieceCount() {
        board.initialize();

        assertThat(board.pieceCount(Piece.Color.BLACK, Piece.Type.PAWN)).isEqualTo(8);
        assertThat(board.pieceCount(Piece.Color.BLACK, Piece.Type.ROOK)).isEqualTo(2);
        assertThat(board.pieceCount(Piece.Color.BLACK, Piece.Type.KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Piece.Color.BLACK, Piece.Type.BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Piece.Color.BLACK, Piece.Type.QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Piece.Color.BLACK, Piece.Type.KING)).isEqualTo(1);


        assertThat(board.pieceCount(Piece.Color.WHITE, Piece.Type.PAWN)).isEqualTo(8);
        assertThat(board.pieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Piece.Color.WHITE, Piece.Type.KNIGHT)).isEqualTo(2);
        assertThat(board.pieceCount(Piece.Color.WHITE, Piece.Type.BISHOP)).isEqualTo(2);
        assertThat(board.pieceCount(Piece.Color.WHITE, Piece.Type.QUEEN)).isEqualTo(1);
        assertThat(board.pieceCount(Piece.Color.WHITE, Piece.Type.KING)).isEqualTo(1);
    }

    @Test
    @DisplayName("좌표로 기물을 찾는 기능을 검증한다.")
    void findPiece() {
        board.initialize();

        assertThat(board.findPiece("a8")).isEqualTo(Piece.createBlackRook());
        assertThat(board.findPiece("h8")).isEqualTo(Piece.createBlackRook());
        assertThat(board.findPiece("a1")).isEqualTo(Piece.createWhiteRook());
        assertThat(board.findPiece("h1")).isEqualTo(Piece.createWhiteRook());
    }

    @Test
    @DisplayName("특정 좌표에 기물을 옮기는 기능을 검증한다.")
    void move() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertThat(board.findPiece(position)).isEqualTo(piece);
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 보드의 점수를 계산하는 기능을 검증한다.")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertThat(board.calculatePoint(Piece.Color.BLACK)).isEqualTo(15.0);
        assertThat(board.calculatePoint(Piece.Color.WHITE)).isEqualTo(6.5);


    }

    private void addPiece(final String position, final Piece piece) {
        board.move(position, piece);
    }
}
