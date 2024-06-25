package chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.StringUtils.appendNewLine;

import chess.pieces.Piece;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @DisplayName("체스판에 흰색/검은색 폰을 추가한다.")
    @Test
    void addPawns() {
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();
        AtomicInteger index = new AtomicInteger();
        List<Piece> pieces = List.of(whitePawn, blackPawn);

        pieces.forEach(piece -> {
            board.add(piece);
            assertBoard(board, index.getAndIncrement(), piece);
        });

    }

    void assertBoard(Board board, int index, Piece piece) {
        assertEquals(index + 1, board.size());
        assertEquals(piece, board.findPiece(index));
    }

    @DisplayName("체스판 초기화한다.")
    @Test
    void initialize() {
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
}
