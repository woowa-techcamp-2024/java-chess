package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.pieces.PieceTypes.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChessPieceTest {

    private void verifyPiece(final ChessPiece chessPiece, final PieceTypes type) {
        verifyPiece(chessPiece, type.getColor(), type.getRepresentation(), type.getType(),type.getCourse());
    }

    private void verifyPiece(final ChessPiece chessPiece, final PieceTypes.Color color, final char representation, final PieceTypes.Type type, final Course course) {
        assertEquals(color, chessPiece.getColor());
        assertEquals(representation, chessPiece.getRepresentation());
        assertEquals(type, chessPiece.getType());
    }

    interface PieceCommonTestRules{
        void createTest();
        void isBlackAndIsWhiteTestWithBlackPiece();
        void isWhiteAndIsBlackTestWithWhitePiece();
        void courseTest();
    }

    @Nested
    @DisplayName("Pawn")
    class PawnTest implements PieceCommonTestRules{

        @Test
        public void createTest() {
            ChessPiece whitePawn = new Pawn(WHITE_PAWN);
            ChessPiece blackPawn = new Pawn(BLACK_PAWN);

            verifyPiece(whitePawn, WHITE_PAWN);
            verifyPiece(blackPawn, BLACK_PAWN);
        }

        @Test
        public void isBlackAndIsWhiteTestWithBlackPiece() {
            ChessPiece blackPawn = new Pawn(BLACK_PAWN);

            assertTrue(blackPawn.isBlack());
            assertFalse(blackPawn.isWhite());
        }

        @Test
        public void isWhiteAndIsBlackTestWithWhitePiece() {
            ChessPiece whitePawn = new Pawn(WHITE_PAWN);

            assertTrue(whitePawn.isWhite());
            assertFalse(whitePawn.isBlack());
        }

        @Test
        public void courseTest(){
            ChessPiece whitePawn = new Pawn(WHITE_PAWN);
            ChessPiece blackPawn = new Pawn(BLACK_PAWN);

            assertEquals(Direction.blackPawnDirection(),blackPawn.getCourse().getDirections());
            assertEquals(Direction.whitePawnDirection(),whitePawn.getCourse().getDirections());
        }
    }

    @Nested
    @DisplayName("Knight")
    class KnightTest implements PieceCommonTestRules{

        @Test
        public void createTest() {
            ChessPiece whiteKnight = new Knight(WHITE_KNIGHT);
            ChessPiece blackKnight = new Knight(BLACK_KNIGHT);

            verifyPiece(whiteKnight, WHITE_KNIGHT);
            verifyPiece(blackKnight, BLACK_KNIGHT);
        }

        @Test
        public void isBlackAndIsWhiteTestWithBlackPiece() {
            ChessPiece blackKnight = new Knight(BLACK_KNIGHT);

            assertTrue(blackKnight.isBlack());
            assertFalse(blackKnight.isWhite());
        }

        @Test
        public void isWhiteAndIsBlackTestWithWhitePiece() {
            ChessPiece whiteKnight = new Knight(WHITE_KNIGHT);

            assertTrue(whiteKnight.isWhite());
            assertFalse(whiteKnight.isBlack());
        }

        @Test
        public void courseTest(){
            ChessPiece whiteKnight = new Knight(WHITE_KNIGHT);
            ChessPiece blackKnight = new Knight(BLACK_KNIGHT);

            assertEquals(Direction.knightDirection(),whiteKnight.getCourse().getDirections());
            assertEquals(Direction.knightDirection(),blackKnight.getCourse().getDirections());
            assertFalse(whiteKnight.getCourse().isRecursive());
            assertFalse(blackKnight.getCourse().isRecursive());
        }
    }

    @Nested
    @DisplayName("Rook")
    class RookTest implements PieceCommonTestRules{

        @Test
        public void createTest() {
            ChessPiece whiteRook = new Rook(WHITE_ROOK);
            ChessPiece blackRook = new Rook(BLACK_ROOK);

            verifyPiece(whiteRook, WHITE_ROOK);
            verifyPiece(blackRook, BLACK_ROOK);
        }

        @Test
        public void isBlackAndIsWhiteTestWithBlackPiece() {
            ChessPiece blackRook = new Rook(BLACK_ROOK);

            assertTrue(blackRook.isBlack());
            assertFalse(blackRook.isWhite());
        }

        @Test
        public void isWhiteAndIsBlackTestWithWhitePiece() {
            ChessPiece whiteRook = new Rook(WHITE_ROOK);

            assertTrue(whiteRook.isWhite());
            assertFalse(whiteRook.isBlack());
        }

        @Test
        public void courseTest(){
            ChessPiece whiteRook = new Rook(WHITE_ROOK);
            ChessPiece blackRook = new Rook(BLACK_ROOK);

            assertEquals(Direction.linearDirection(),whiteRook.getCourse().getDirections());
            assertEquals(Direction.linearDirection(),blackRook.getCourse().getDirections());
            assertTrue(whiteRook.getCourse().isRecursive());
            assertTrue(blackRook.getCourse().isRecursive());
        }
    }

    @Nested
    @DisplayName("Bishop")
    class BishopTest implements PieceCommonTestRules{

        @Test
        public void createTest() {
            ChessPiece whiteBishop = new Bishop(WHITE_BISHOP);
            ChessPiece blackBishop = new Bishop(BLACK_BISHOP);

            verifyPiece(whiteBishop, WHITE_BISHOP);
            verifyPiece(blackBishop, BLACK_BISHOP);
        }

        @Test
        public void isBlackAndIsWhiteTestWithBlackPiece() {
            ChessPiece blackBishop = new Bishop(BLACK_BISHOP);

            assertTrue(blackBishop.isBlack());
            assertFalse(blackBishop.isWhite());
        }

        @Test
        public void isWhiteAndIsBlackTestWithWhitePiece() {
            ChessPiece whiteBishop = new Bishop(WHITE_BISHOP);

            assertTrue(whiteBishop.isWhite());
            assertFalse(whiteBishop.isBlack());
        }

        @Test
        public void courseTest(){
            ChessPiece whiteBishop = new Rook(WHITE_BISHOP);
            ChessPiece blackBishop = new Rook(BLACK_BISHOP);

            assertEquals(Direction.diagonalDirection(),whiteBishop.getCourse().getDirections());
            assertEquals(Direction.diagonalDirection(),blackBishop.getCourse().getDirections());
            assertTrue(whiteBishop.getCourse().isRecursive());
            assertTrue(blackBishop.getCourse().isRecursive());
        }
    }

    @Nested
    @DisplayName("Queen")
    class QueenTest implements PieceCommonTestRules{

        @Test
        public void createTest() {
            ChessPiece whiteQueen = new Queen(WHITE_QUEEN);
            ChessPiece blackQueen = new Queen(BLACK_QUEEN);

            verifyPiece(whiteQueen, WHITE_QUEEN);
            verifyPiece(blackQueen, BLACK_QUEEN);
        }

        @Test
        public void isBlackAndIsWhiteTestWithBlackPiece() {
            ChessPiece blackQueen = new Queen(BLACK_QUEEN);

            assertTrue(blackQueen.isBlack());
            assertFalse(blackQueen.isWhite());
        }

        @Test
        public void isWhiteAndIsBlackTestWithWhitePiece() {
            ChessPiece whiteQueen = new Queen(WHITE_QUEEN);

            assertTrue(whiteQueen.isWhite());
            assertFalse(whiteQueen.isBlack());
        }

        @Test
        public void courseTest(){
            ChessPiece whiteQueen = new Rook(WHITE_QUEEN);
            ChessPiece blackQueen = new Rook(BLACK_QUEEN);

            assertEquals(Direction.linearDirection(),whiteQueen.getCourse().getDirections());
            assertEquals(Direction.linearDirection(),blackQueen.getCourse().getDirections());
            assertTrue(whiteQueen.getCourse().isRecursive());
            assertTrue(blackQueen.getCourse().isRecursive());
        }
    }

    @Nested
    @DisplayName("King")
    class KingTest implements PieceCommonTestRules{

        @Test
        public void createTest() {
            ChessPiece whiteKing = new King(WHITE_KING);
            ChessPiece blackKing = new King(BLACK_KING);

            verifyPiece(whiteKing, WHITE_KING);
            verifyPiece(blackKing, BLACK_KING);
        }

        @Test
        public void isBlackAndIsWhiteTestWithBlackPiece() {
            ChessPiece blackKing = new King(BLACK_KING);

            assertTrue(blackKing.isBlack());
            assertFalse(blackKing.isWhite());
        }

        @Test
        public void isWhiteAndIsBlackTestWithWhitePiece() {
            ChessPiece whiteKing = new King(WHITE_KING);

            assertTrue(whiteKing.isWhite());
            assertFalse(whiteKing.isBlack());
        }

        @Test
        public void courseTest(){
            ChessPiece whiteKing = new King(WHITE_KING);
            ChessPiece blackKing = new King(BLACK_KING);

            assertEquals(Direction.everyDirection(),whiteKing.getCourse().getDirections());
            assertEquals(Direction.everyDirection(),blackKing.getCourse().getDirections());

            assertFalse(whiteKing.getCourse().isRecursive());
            assertFalse(blackKing.getCourse().isRecursive());
        }
    }

    @Test
    public void createBlank() {
        ChessPiece blank = PieceFactory.createPiece(NO_PIECE);

        assertEquals(Type.NO_PIECE, blank.getType());
        assertFalse(blank.isBlack());
        assertFalse(blank.isWhite());

        assertEquals(0,blank.getCourse().getDirections().size());
        assertFalse(blank.getCourse().isRecursive());
    }
}
