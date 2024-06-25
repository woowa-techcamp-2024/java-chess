package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판 초기화 시 모든 기물이 배치된다.")
    public void create() throws Exception {
        board.initialize();
        assertEquals(32, board.pieceCount());
        String blankRank = appendNewLine("........");
        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.showBoard());
    }

    @Test
    @DisplayName("체스 보드에 배치된 체스말 갯수를 알 수 있다.")
    public void findPieceCount() {
        board.initialize();

        verifyWhitePieceCount(Type.PAWN, 8);
        verifyWhitePieceCount(Type.ROOK, 2);
        verifyWhitePieceCount(Type.KNIGHT, 2);
        verifyWhitePieceCount(Type.BISHOP, 2);
        verifyWhitePieceCount(Type.QUEEN, 1);
        verifyWhitePieceCount(Type.KING, 1);

        verifyBlackPieceCount(Type.PAWN, 8);
        verifyBlackPieceCount(Type.ROOK, 2);
        verifyBlackPieceCount(Type.KNIGHT, 2);
        verifyBlackPieceCount(Type.BISHOP, 2);
        verifyBlackPieceCount(Type.QUEEN, 1);
        verifyBlackPieceCount(Type.KING, 1);
    }

    private void verifyWhitePieceCount(Type type, int count) {
        assertThat(board.pieceCount(type, Color.WHITE)).isEqualTo(count);
    }

    private void verifyBlackPieceCount(Type type, int count) {
        assertThat(board.pieceCount(type, Color.BLACK)).isEqualTo(count);
    }
}
