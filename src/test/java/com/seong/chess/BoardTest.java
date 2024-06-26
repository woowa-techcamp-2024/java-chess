package com.seong.chess;

import static com.seong.chess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Piece.Type;
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

    @Test
    @DisplayName("체스 보드의 주어진 위치에 존재하는 체스말을 찾을 수 있다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("체스 보드에 임의의 기물을 추가할 수 있다.")
    public void moveRandomPiece() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("체스판 위의 기물에 따라 점수를 계산할 수 있다.")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("체스 보드의 기물을 점수가 높은 순으로 정렬할 수 있다.")
    public void piecesOrderByScoreDesc() {
        board.initialize();

        List<Piece> whitePiecesOrderByDesc = board.getPiecesOrderByHighestScore(Color.WHITE);
        assertThat(whitePiecesOrderByDesc)
                .containsExactly(
                        Piece.createWhiteQueen(),
                        Piece.createWhiteRook(), Piece.createWhiteRook(),
                        Piece.createWhiteBishop(), Piece.createWhiteBishop(),
                        Piece.createWhiteKnight(), Piece.createWhiteKnight(),
                        Piece.createWhitePawn(), Piece.createWhitePawn(),
                        Piece.createWhitePawn(), Piece.createWhitePawn(),
                        Piece.createWhitePawn(), Piece.createWhitePawn(),
                        Piece.createWhitePawn(), Piece.createWhitePawn(),
                        Piece.createWhiteKing()
                );
    }

    @Test
    @DisplayName("체스 보드의 기물을 점수가 낮은 순으로 정렬할 수 있다.")
    public void piecesOrderByScoreAsc() {
        board.initialize();

        List<Piece> blackPiecesOrderByAsc = board.getPiecesOrderByLowest(Color.BLACK);
        assertThat(blackPiecesOrderByAsc)
                .containsExactly(
                        Piece.createBlackKing(),
                        Piece.createBlackPawn(), Piece.createBlackPawn(),
                        Piece.createBlackPawn(), Piece.createBlackPawn(),
                        Piece.createBlackPawn(), Piece.createBlackPawn(),
                        Piece.createBlackPawn(), Piece.createBlackPawn(),
                        Piece.createBlackKnight(), Piece.createBlackKnight(),
                        Piece.createBlackBishop(), Piece.createBlackBishop(),
                        Piece.createBlackRook(), Piece.createBlackRook(),
                        Piece.createBlackQueen()
                );
    }

    @Test
    @DisplayName("체스 보드의 기물은 현재 위치에서 다른 위치로 이동할 수 있다.")
    public void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(Board.Position.convert(sourcePosition)), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(Board.Position.convert(targetPosition)), board.findPiece(targetPosition));
    }
}
