package wootecamp.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.pieces.Piece;
import wootecamp.chess.pieces.PieceFactory;

import java.util.List;

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
    @DisplayName("좌표로 기물을 찾는다.")
    void findPiece() {
        board.initialize();

        assertThat(board.findPiece(new BoardPosition("a8"))).isEqualTo(PieceFactory.createBlackRook());
        assertThat(board.findPiece(new BoardPosition("h8"))).isEqualTo(PieceFactory.createBlackRook());
        assertThat(board.findPiece(new BoardPosition("a1"))).isEqualTo(PieceFactory.createWhiteRook());
        assertThat(board.findPiece(new BoardPosition("h1"))).isEqualTo(PieceFactory.createWhiteRook());
    }

    @Test
    @DisplayName("현재 보드의 점수를 계산한다.")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", PieceFactory.createBlackPawn());
        addPiece("e6", PieceFactory.createBlackQueen());
        addPiece("b8", PieceFactory.createBlackKing());
        addPiece("c8", PieceFactory.createBlackRook());

        addPiece("f2", PieceFactory.createWhitePawn());
        addPiece("f3", PieceFactory.createWhitePawn());
        addPiece("e1", PieceFactory.createWhiteRook());
        addPiece("f1", PieceFactory.createWhiteKing());

        assertThat(board.calculatePoint(Piece.Color.BLACK)).isEqualTo(15.0);
        assertThat(board.calculatePoint(Piece.Color.WHITE)).isEqualTo(6.0);
    }

    @Test
    @DisplayName("보드에 있는 기물을 점수순으로 정렬한다.")
    void getSortedPiecesPiece() {
        board.initializeEmpty();

        Piece blackPawn = PieceFactory.createBlackPawn();
        Piece blackQueen = PieceFactory.createBlackQueen();
        Piece blackKing = PieceFactory.createBlackKing();
        Piece blackRook = PieceFactory.createBlackRook();

        addPiece("b6", blackPawn);
        addPiece("e6", blackQueen);
        addPiece("b8", blackKing);
        addPiece("c8", blackRook);

        assertThat(board.collectPieces(Piece.Color.BLACK, PieceComparator.ASC))
                .isEqualTo(List.of(blackKing, blackPawn, blackRook, blackQueen));
        assertThat(board.collectPieces(Piece.Color.BLACK, PieceComparator.DESC))
                .isEqualTo(List.of(blackQueen, blackRook, blackPawn, blackKing));
        assertThat(board.collectPieces(Piece.Color.WHITE, PieceComparator.DESC)).isEqualTo(List.of());
    }

    private void addPiece(final String position, final Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("기물을 이동한다.")
    void move() {
        board.initialize();

        BoardPosition source = new BoardPosition("b2");
        BoardPosition target = new BoardPosition("b3");

        board.move(source, target);

        assertThat(board.findPiece(source)).isEqualTo(PieceFactory.createEmptyPiece());
        assertThat(board.findPiece(target)).isEqualTo(PieceFactory.createWhitePawn());
    }
}
