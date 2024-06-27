package chess;

import chess.pieces.PieceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {
    private PieceCreator pieceCreator;
    private ChessGame chessGame;
    private Board board;
    @BeforeEach
    void setUp(){
        pieceCreator = new PieceCreatorWithFactory();
        board = new Board(pieceCreator);
        chessGame = new ChessGame(board);
    }

    @Nested
    @DisplayName("[Move]")
    class MoveTest{
        interface PieceCommonMoveTestRules{
            void normalMove();
            void impossibleMove();
            void impossibleMoveAtSameColor();
            void impossibleOutOfBoard();
        }
        interface PieceRecursiveMoveTestRules{
            void recursiveMove();
        }

        private void verifyPieceWithPosition(String position,PieceTypes type){
            assertTrue(board.findPiece(position)
                    .map(p->type.getType().equals(p.getType()))
                    .orElse(false));
        }

        private void verifyPieceMoveSuccess(String source, String target, PieceTypes type){
            verifyPieceWithPosition(source,PieceTypes.NO_PIECE);
            verifyPieceWithPosition(target,type);
        }

        private void verifyPieceMoveFailure(String source, String target, PieceTypes sourceType, PieceTypes targetType){
            verifyPieceWithPosition(source,sourceType);
            verifyPieceWithPosition(target,targetType);
        }
        
        private boolean whiteMove(String source,String target){
            chessGame.setWhiteTurn(true);
            return chessGame.move(source,target);
        }
        
        private boolean blackMove(String source,String target){
            chessGame.setWhiteTurn(false);
            return chessGame.move(source,target);
        }

        @BeforeEach
        void setUp(){
            board.initializeEmpty();
        }

        @Nested
        @DisplayName("[Pawn]")
        class PawnTest implements PieceCommonMoveTestRules{
            private PieceTypes originWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes originBlackType = PieceTypes.BLACK_PAWN;

            private PieceTypes targetWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes targetBlackType = PieceTypes.BLACK_ROOK;
            @Test
            public void normalMove(){
                String ws = "a2";
                String we = "a3";

                String bs = "a7";
                String be = "a6";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                assertTrue(whiteMove(ws,we));
                verifyPieceMoveSuccess(ws,we,originWhiteType);
                assertTrue(blackMove(bs,be));
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }

            @Test
            public void impossibleMove(){
                String ws = "h2";
                String we = "h5";

                String bs = "h7";
                String be = "h4";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetWhiteType));

                assertFalse(whiteMove(ws,we));
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);
                assertFalse(blackMove(bs,be));
                verifyPieceMoveFailure(bs,be,originBlackType,targetWhiteType);
            }

            @Test
            public void impossibleMoveAtSameColor(){
                //given
                String ws = "b2";
                String we = "b3";

                String bs = "b7";
                String be = "h7";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(we,pieceCreator.createPiece(targetWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(PieceTypes.BLACK_ROOK));

                //when
                boolean movedWhite = whiteMove(ws,we);
                boolean movedBlack = blackMove(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,targetWhiteType);

                assertFalse(movedBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetBlackType);
            }

            @Test
            public void impossibleOutOfBoard(){
                //given
                String ws = "a8";
                String we = "a9";

                String bs = "a1";
                String be = "a0";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean movedWhite = whiteMove(ws,we);
                boolean movedBlack = blackMove(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(movedBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }
        }

        @Nested
        @DisplayName("[Rook]")
        class RookTest implements PieceCommonMoveTestRules,PieceRecursiveMoveTestRules{
            private PieceTypes originWhiteType = PieceTypes.WHITE_ROOK;
            private PieceTypes originBlackType = PieceTypes.BLACK_ROOK;

            private PieceTypes targetWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes targetBlackType = PieceTypes.BLACK_QUEEN;
            @Test
            public void normalMove() {
                //given
                String ws = "a1";
                String we ="a2";

                String bs = "b4";
                String be = "a4";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertTrue(moveWhite);
                verifyPieceMoveSuccess(ws,we,originWhiteType);

                assertTrue(moveBlack);
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }

            @Test
            public void impossibleMove() {
                //given
                String ws = "a1";
                String we ="b2";

                String bs = "h6";
                String be = "f8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetWhiteType));

                //when
                boolean moveWhite = whiteMove(ws,we);//대각선은 이동 불가
                boolean moveBlack = blackMove(bs,be);//대각선은 이동 불가

                //then
                assertFalse(moveWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);

                assertFalse(moveBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetWhiteType);
            }

            @Test
            public void impossibleMoveAtSameColor() {
                //given
                String ws = "a1";
                String we ="a4";

                String bs = "h7";
                String be = "h5";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(we,pieceCreator.createPiece(targetWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertFalse(moveWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,targetWhiteType);

                assertFalse(moveBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetBlackType);
            }

            @Test
            public void impossibleOutOfBoard() {
                //given
                String ws = "a1";
                String we ="a10";

                String bs = "h8";
                String be = "h0";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertFalse(moveWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(moveBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }

            @Override
            public void recursiveMove() {
                //given
                String ws = "a1";
                String we ="a4";

                String bs = "b4";
                String be = "a4";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertTrue(moveWhite);
                verifyPieceMoveSuccess(ws,we,originWhiteType);

                assertTrue(moveBlack);
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }
        }

        @Nested
        @DisplayName("[Knight]")
        class Knight implements PieceCommonMoveTestRules{
            private PieceTypes originWhiteType = PieceTypes.WHITE_KNIGHT;
            private PieceTypes originBlackType = PieceTypes.BLACK_KNIGHT;

            private PieceTypes targetWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes targetBlackType = PieceTypes.BLACK_ROOK;
            @Test
            public void normalMove(){
                String ws = "a2";
                String we = "c1";

                String bs = "a7";
                String be = "c6";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                assertTrue(whiteMove(ws,we));
                verifyPieceMoveSuccess(ws,we,originWhiteType);
                assertTrue(blackMove(bs,be));
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }

            @Test
            public void impossibleMove(){
                String ws = "a2";
                String we = "c2";

                String bs = "a7";
                String be = "a1";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetWhiteType));

                assertFalse(whiteMove(ws,we));
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);
                assertFalse(blackMove(bs,be));
                verifyPieceMoveFailure(bs,be,originBlackType,targetWhiteType);
            }

            @Test
            public void impossibleMoveAtSameColor(){
                //given
                String ws = "a2";
                String we = "c1";

                String bs = "b7";
                String be = "d8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(we,pieceCreator.createPiece(targetWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(PieceTypes.BLACK_ROOK));

                //when
                boolean movedWhite = whiteMove(ws,we);
                boolean movedBlack = blackMove(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,targetWhiteType);

                assertFalse(movedBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetBlackType);
            }

            @Test
            public void impossibleOutOfBoard(){
                //given
                String ws = "a8";
                String we = "c9";

                String bs = "a1";
                String be = "c0";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean movedWhite = whiteMove(ws,we);
                boolean movedBlack = blackMove(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(movedBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }
        }

        @Nested
        @DisplayName("[Bishop]")
        class BishopTest implements PieceCommonMoveTestRules,PieceRecursiveMoveTestRules{
            private PieceTypes originWhiteType = PieceTypes.WHITE_BISHOP;
            private PieceTypes originBlackType = PieceTypes.BLACK_BISHOP;

            private PieceTypes targetWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes targetBlackType = PieceTypes.BLACK_QUEEN;
            @Test
            public void normalMove() {
                //given
                String ws = "a1";
                String we ="b2";

                String bs = "b4";
                String be = "c3";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertTrue(moveWhite);
                verifyPieceMoveSuccess(ws,we,originWhiteType);

                assertTrue(moveBlack);
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }

            @Test
            public void impossibleMove() {
                //given
                String ws = "a1";
                String we ="a2";

                String bs = "h6";
                String be = "f7";
                board.move(ws,pieceCreator.createPiece(originWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetWhiteType));

                //when
                boolean moveWhite = whiteMove(ws,we);//직진 불가
                boolean moveBlack = blackMove(bs,be);//말처럼 이동 불가

                //then
                assertFalse(moveWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);

                assertFalse(moveBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetWhiteType);
            }

            @Test
            public void impossibleMoveAtSameColor() {
                //given
                String ws = "a1";
                String we ="b2";

                String bs = "h7";
                String be = "g8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(we,pieceCreator.createPiece(targetWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertFalse(moveWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,targetWhiteType);

                assertFalse(moveBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetBlackType);
            }

            @Test
            public void impossibleOutOfBoard() {
                //given
                String ws = "b1";
                String we ="a0";

                String bs = "g8";
                String be = "h9";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertFalse(moveWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(moveBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }

            @Override
            public void recursiveMove() {
                //given
                String ws = "a1";
                String we ="d5";

                String bs = "b4";
                String be = "e1";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertTrue(moveWhite);
                verifyPieceMoveSuccess(ws,we,originWhiteType);

                assertTrue(moveBlack);
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }
        }

        @Nested
        @DisplayName("[Queen]")
        class QueenTest implements PieceCommonMoveTestRules,PieceRecursiveMoveTestRules{
            private PieceTypes originWhiteType = PieceTypes.WHITE_QUEEN;
            private PieceTypes originBlackType = PieceTypes.BLACK_QUEEN;

            private PieceTypes targetWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes targetBlackType = PieceTypes.BLACK_QUEEN;
            @Test
            public void normalMove() {
                //given
                String ws = "a1";
                String we ="a2";

                String bs = "b4";
                String be = "c5";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertTrue(moveWhite);
                verifyPieceMoveSuccess(ws,we,originWhiteType);

                assertTrue(moveBlack);
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }

            @Test
            public void impossibleMove() {
                //given
                String ws = "a1";
                String we ="b3";

                String bs = "h6";
                String be = "g8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetWhiteType));

                //when
                boolean moveWhite = whiteMove(ws,we);//직진 불가
                boolean moveBlack = blackMove(bs,be);//말처럼 이동 불가

                //then
                assertFalse(moveWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);

                assertFalse(moveBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetWhiteType);
            }

            @Test
            public void impossibleMoveAtSameColor() {
                //given
                String ws = "a1";
                String we ="a2";

                String bs = "h7";
                String be = "g8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(we,pieceCreator.createPiece(targetWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertFalse(moveWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,targetWhiteType);

                assertFalse(moveBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetBlackType);
            }

            @Test
            public void impossibleOutOfBoard() {
                //given
                String ws = "a1";
                String we ="a0";

                String bs = "g8";
                String be = "h9";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertFalse(moveWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(moveBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }

            @Override
            public void recursiveMove() {
                //given
                String ws = "a1";
                String we ="a5";

                String bs = "b4";
                String be = "e1";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean moveWhite = whiteMove(ws,we);
                boolean moveBlack = blackMove(bs,be);

                //then
                assertTrue(moveWhite);
                verifyPieceMoveSuccess(ws,we,originWhiteType);

                assertTrue(moveBlack);
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }
        }

        @Nested
        @DisplayName("[King]")
        class King implements PieceCommonMoveTestRules{
            private PieceTypes originWhiteType = PieceTypes.WHITE_KING;
            private PieceTypes originBlackType = PieceTypes.BLACK_KING;

            private PieceTypes targetWhiteType = PieceTypes.WHITE_PAWN;
            private PieceTypes targetBlackType = PieceTypes.BLACK_ROOK;
            @Test
            public void normalMove(){
                String ws = "a2";
                String we = "a3";

                String bs = "a7";
                String be = "b8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                assertTrue(whiteMove(ws,we));
                verifyPieceMoveSuccess(ws,we,originWhiteType);
                assertTrue(blackMove(bs,be));
                verifyPieceMoveSuccess(bs,be,originBlackType);
            }

            @Test
            public void impossibleMove(){
                String ws = "a2";
                String we = "a4";

                String bs = "a7";
                String be = "c5";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(targetWhiteType));

                assertFalse(whiteMove(ws,we));
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);
                assertFalse(blackMove(bs,be));
                verifyPieceMoveFailure(bs,be,originBlackType,targetWhiteType);
            }

            @Test
            public void impossibleMoveAtSameColor(){
                //given
                String ws = "a2";
                String we = "a3";

                String bs = "b7";
                String be = "c8";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(we,pieceCreator.createPiece(targetWhiteType));

                board.move(bs,pieceCreator.createPiece(originBlackType));
                board.move(be,pieceCreator.createPiece(PieceTypes.BLACK_ROOK));

                //when
                boolean movedWhite = whiteMove(ws,we);
                boolean movedBlack = blackMove(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceMoveFailure(ws,we,originWhiteType,targetWhiteType);

                assertFalse(movedBlack);
                verifyPieceMoveFailure(bs,be,originBlackType,targetBlackType);
            }

            @Test
            public void impossibleOutOfBoard(){
                //given
                String ws = "a8";
                String we = "a9";

                String bs = "a1";
                String be = "b0";
                board.move(ws,pieceCreator.createPiece(originWhiteType));
                board.move(bs,pieceCreator.createPiece(originBlackType));

                //when
                boolean movedWhite = whiteMove(ws,we);
                boolean movedBlack = blackMove(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(movedBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }
        }

        @Test
        void canNotMoveOtherPieceWithWhiteTurn(){
            //given
            board.move("a2",pieceCreator.createPiece(PieceTypes.BLACK_PAWN));
            chessGame.setWhiteTurn(true);

            //when
            //then
            //하얀색 턴에 검은 기물을 움직여보기
            String message = assertThrows(IllegalArgumentException.class, () -> {
                chessGame.move("a2","a1");
            }).getMessage();
            assertEquals(message,"다른사람의 기물을 움직일 수 없습니다.");
        }

        @Test
        void canMoveOtherPieceWithBlackTurn(){
            //given
            board.move("a1",pieceCreator.createPiece(PieceTypes.WHITE_PAWN));
            board.move("h8",pieceCreator.createPiece(PieceTypes.BLACK_PAWN));
            chessGame.setWhiteTurn(false);

            //when
            //then
            String message = assertThrows(IllegalArgumentException.class, () -> {
                chessGame.move("a1", "a2");
            }).getMessage();
            assertEquals(message,"다른사람의 기물을 움직일 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("[Init]")
    class InitTest{
        @Test
        public void initSuccess() throws Exception {
            //given
            String success = """
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr
                """;

            //when
            chessGame.init();

            //then
            assertEquals(success,board.print());
        }
    }

    @Nested
    @DisplayName("[ShowBoard]")
    class ShowBoardTest{
        @Test
        public void showSuccess() throws Exception {
            //given
            String success = """
                RNBQKBNR
                PPPPPPPP
                ........
                ..p.....
                ........
                ........
                .ppppppp
                rnbqkbnr
                """;
            board.initialize();
            board.move("a2","c5");

            //when

            //then
            assertEquals(success,chessGame.showBoard());
        }
    }

    @Nested
    @DisplayName("[StartGame]")
    class StartGameTest{
        @Test
        public void startGameTest() throws Exception {
            //given
            String success = """
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr
                """;

            //when
            chessGame.startGame();

            //then
            assertEquals(success,board.print());
        }
    }

    @Nested
    @DisplayName("[FinishGame]")
    class FinishGameTest{
        @Test
        public void finishGameTest() throws Exception {
            //given
            board.initialize();
            String success = """
                ........
                ........
                ........
                ........
                ........
                ........
                ........
                ........
                """;

            //when
            chessGame.finishGame();

            //then
            assertEquals(success,board.print());
        }
    }
}
