package pe.goblin.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pe.goblin.chess.piece.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pe.goblin.chess.utils.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Board는 초기화할 때 흰색 Pawn과 검은색 Pawn을 생성해 저장할 수 있다.")
    void create() throws Exception {
        board.initialize();
        assertEquals(32, board.countAllPieces());
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
    @DisplayName("주어진 위치의 기물을 조회할 수 있다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        System.out.println(board.findPiece("h8").getRepresentation());

        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    public void move() throws Exception {
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);
        assertEquals(Piece.createBlank(), board.findPiece(sourcePosition));
        assertEquals(Piece.createWhitePawn(), board.findPiece(targetPosition));
    }

    @Test
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

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.0, board.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }
}
