package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Bishop;
import com.seong.chess.pieces.King;
import com.seong.chess.pieces.Knight;
import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Queen;
import com.seong.chess.pieces.Rook;
import java.util.List;
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

        verifyPieceCount(Pawn.createWhite(), 8);
        verifyPieceCount(Rook.createWhite(), 2);
        verifyPieceCount(Knight.createWhite(), 2);
        verifyPieceCount(Bishop.createWhite(), 2);
        verifyPieceCount(Queen.createWhite(), 1);
        verifyPieceCount(King.createWhite(), 1);

        verifyPieceCount(Pawn.createBlack(), 8);
        verifyPieceCount(Rook.createBlack(), 2);
        verifyPieceCount(Knight.createBlack(), 2);
        verifyPieceCount(Bishop.createBlack(), 2);
        verifyPieceCount(Queen.createBlack(), 1);
        verifyPieceCount(King.createBlack(), 1);
    }

    private void verifyPieceCount(Piece piece, int count) {
        assertThat(board.pieceCount(piece, Color.BLACK)).isEqualTo(count);
    }

    @Test
    @DisplayName("체스 보드의 주어진 위치에 존재하는 체스말을 찾을 수 있다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Rook.createBlack(), board.findPiece("a8"));
        assertEquals(Rook.createBlack(), board.findPiece("h8"));
        assertEquals(Rook.createWhite(), board.findPiece("a1"));
        assertEquals(Rook.createWhite(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("체스 보드에 임의의 기물을 추가할 수 있다.")
    public void moveRandomPiece() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Rook.createBlack();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("체스 보드의 기물을 점수가 높은 순으로 정렬할 수 있다.")
    public void piecesOrderByScoreDesc() {
        board.initialize();

        List<Piece> whitePiecesOrderByDesc = board.getPiecesOrderByHighestScore(Color.WHITE);
        assertThat(whitePiecesOrderByDesc)
                .containsExactly(
                        Queen.createWhite(),
                        Rook.createWhite(), Rook.createWhite(),
                        Bishop.createWhite(), Bishop.createWhite(),
                        Knight.createWhite(), Knight.createWhite(),
                        Pawn.createWhite(), Pawn.createWhite(),
                        Pawn.createWhite(), Pawn.createWhite(),
                        Pawn.createWhite(), Pawn.createWhite(),
                        Pawn.createWhite(), Pawn.createWhite(),
                        King.createWhite()
                );
    }

    @Test
    @DisplayName("체스 보드의 기물을 점수가 낮은 순으로 정렬할 수 있다.")
    public void piecesOrderByScoreAsc() {
        board.initialize();

        List<Piece> blackPiecesOrderByAsc = board.getPiecesOrderByLowest(Color.BLACK);
        assertThat(blackPiecesOrderByAsc)
                .containsExactly(
                        King.createBlack(),
                        Pawn.createBlack(), Pawn.createBlack(),
                        Pawn.createBlack(), Pawn.createBlack(),
                        Pawn.createBlack(), Pawn.createBlack(),
                        Pawn.createBlack(), Pawn.createBlack(),
                        Knight.createBlack(), Knight.createBlack(),
                        Bishop.createBlack(), Bishop.createBlack(),
                        Rook.createBlack(), Rook.createBlack(),
                        Queen.createBlack()
                );
    }
}
