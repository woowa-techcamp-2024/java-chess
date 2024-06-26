package chess;

import chess.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.utils.StringUtils.appendNewLine;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드엔 폰이 추가되고 폰에 대한 정보를 얻을 수 있다.")
    public void create() throws Exception {
        Position whitePosition = Position.of(1, 2);
        Pawn white = addPawn(board, whitePosition, PieceColor.WHITE);
        assertEquals(1, board.pieceCount());
        assertEquals(white, board.findPiece(whitePosition));

        Position blackPosition = Position.of(2, 7);
        Pawn black = addPawn(board, blackPosition, PieceColor.BLACK);
        assertEquals(2, board.pieceCount());
        assertEquals(black, board.findPiece(blackPosition));
    }

    private Pawn addPawn(final Board board, final Position position, final PieceColor color) {
        Pawn pawn = Pawn.create(color);
        board.add(position, pawn);

        return pawn;
    }

    @Test
    @DisplayName("보드 초기화시 기물이 각 개수에 알맞게 생성된다.")
    public void initializePawn() throws Exception {
        expectedPiece(Type.PAWN, 8);
        expectedPiece(Type.QUEEN, 1);
        expectedPiece(Type.ROOK, 2);
        expectedPiece(Type.KING, 1);
        expectedPiece(Type.BISHOP, 2);
        expectedPiece(Type.KNIGHT, 2);
    }

    private void expectedPiece(Type type, int expected) {
        board.initialize();

        assertEquals(expected, board.getPieceResult(PieceColor.WHITE, type));
        assertEquals(expected, board.getPieceResult(PieceColor.BLACK, type));
    }

    @Test
    @DisplayName("보드 출력시 기물이 정상적으로 출력된다.")
    public void print() {
        board.initialize();
        board.print();

        assertEquals(32, board.pieceCount());

        assertEquals(
                appendNewLine("♜♞♝♛♚♝♞♜8") +
                        appendNewLine("♟♟♟♟♟♟♟♟7") +
                        appendNewLine("........6") +
                        appendNewLine("........5") +
                        appendNewLine("........4") +
                        appendNewLine("........3") +
                        appendNewLine("♙♙♙♙♙♙♙♙2") +
                        appendNewLine("♖♘♗♕♔♗♘♖1") +
                        appendNewLine("abcdefgh"),
                board.showBoard());
    }

    @Test
    @DisplayName("포지션에 해당하는 기물을 찾는다.")
    public void findPiece() throws Exception {
        board.initialize();

        verifyRookPiece("a8", PieceColor.BLACK);
        verifyRookPiece("h8", PieceColor.BLACK);
        verifyRookPiece("a1", PieceColor.WHITE);
        verifyRookPiece("h1", PieceColor.WHITE);
        verifyKingPiece("e8", PieceColor.BLACK);
        verifyKingPiece("e1", PieceColor.WHITE);
    }

    private void verifyRookPiece(final String point, final PieceColor pieceColor) {
        assertEquals(Rook.create(pieceColor).getType(), board.findPiece(point).getType());
        assertEquals(Rook.create(pieceColor).getColor(), board.findPiece(point).getColor());
    }

    private void verifyKingPiece(final String point, final PieceColor pieceColor) {
        assertEquals(King.create(pieceColor).getType(), board.findPiece(point).getType());
        assertEquals(King.create(pieceColor).getColor(), board.findPiece(point).getColor());
    }

    @Test
    @DisplayName("빈 체스판에서 임의의 기물을 추가한다.")
    public void move() throws Exception {
        String position = "b5";
        Piece piece = Rook.create(PieceColor.BLACK);
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("색별로 기물의 점수를 계산한다.")
    public void caculcatePoint() throws Exception {
        addPiece("b6", Pawn.create(PieceColor.BLACK));
        addPiece("e6", Queen.create(PieceColor.BLACK));
        addPiece("b8", King.create(PieceColor.BLACK));
        addPiece("c8", Rook.create(PieceColor.BLACK));

        addPiece("f2", Pawn.create(PieceColor.WHITE));
        addPiece("g2", Pawn.create(PieceColor.WHITE));
        addPiece("e1", Rook.create(PieceColor.WHITE));
        addPiece("f1", King.create(PieceColor.WHITE));

        assertEquals(15.0, board.calculatePoint(PieceColor.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(PieceColor.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("색별로 높은 점수의 기물을 반환한다.")
    public void orderPieceWithScore() {
        addPiece("b6", Pawn.create(PieceColor.BLACK));
        addPiece("e6", Queen.create(PieceColor.BLACK));
        addPiece("b8", King.create(PieceColor.BLACK));
        addPiece("c8", Rook.create(PieceColor.BLACK));

        addPiece("f2", Pawn.create(PieceColor.WHITE));
        addPiece("g2", Pawn.create(PieceColor.WHITE));
        addPiece("e1", Rook.create(PieceColor.WHITE));
        addPiece("f1", King.create(PieceColor.WHITE));

        List<Piece> blackPieces = board.orderPieceWithScore(PieceColor.BLACK);
        List<Piece> whitePieces = board.orderPieceWithScore(PieceColor.WHITE);

        assertEquals(Type.QUEEN, blackPieces.get(0).getType());
        assertEquals(Type.ROOK, blackPieces.get(1).getType());

        assertEquals(Type.ROOK, whitePieces.get(0).getType());
        assertEquals(Type.KING, whitePieces.get(3).getType());
    }

    @Test
    @DisplayName("기물을 이동한다.")
    public void move_pieces() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);

        assertEquals(Type.NO_PIECE, board.findPiece(sourcePosition).getType());
        assertEquals(Type.PAWN, board.findPiece(targetPosition).getType());
        assertEquals(PieceColor.WHITE, board.findPiece(targetPosition).getColor());
    }
}
