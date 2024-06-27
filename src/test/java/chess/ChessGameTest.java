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

                assertTrue(chessGame.move(ws,we));
                verifyPieceMoveSuccess(ws,we,originWhiteType);
                assertTrue(chessGame.move(bs,be));
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

                assertFalse(chessGame.move(ws,we));
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);
                assertFalse(chessGame.move(bs,be));
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
                boolean movedWhite = chessGame.move(ws,we);
                boolean movedBlack = chessGame.move(bs,be);

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
                boolean movedWhite = chessGame.move(ws,we);
                boolean movedBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);//대각선은 이동 불가
                boolean moveBlack = chessGame.move(bs,be);//대각선은 이동 불가

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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

                assertTrue(chessGame.move(ws,we));
                verifyPieceMoveSuccess(ws,we,originWhiteType);
                assertTrue(chessGame.move(bs,be));
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

                assertFalse(chessGame.move(ws,we));
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);
                assertFalse(chessGame.move(bs,be));
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
                boolean movedWhite = chessGame.move(ws,we);
                boolean movedBlack = chessGame.move(bs,be);

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
                boolean movedWhite = chessGame.move(ws,we);
                boolean movedBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);//직진 불가
                boolean moveBlack = chessGame.move(bs,be);//말처럼 이동 불가

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);//직진 불가
                boolean moveBlack = chessGame.move(bs,be);//말처럼 이동 불가

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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
                boolean moveWhite = chessGame.move(ws,we);
                boolean moveBlack = chessGame.move(bs,be);

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

                assertTrue(chessGame.move(ws,we));
                verifyPieceMoveSuccess(ws,we,originWhiteType);
                assertTrue(chessGame.move(bs,be));
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

                assertFalse(chessGame.move(ws,we));
                verifyPieceMoveFailure(ws,we,originWhiteType,PieceTypes.NO_PIECE);
                assertFalse(chessGame.move(bs,be));
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
                boolean movedWhite = chessGame.move(ws,we);
                boolean movedBlack = chessGame.move(bs,be);

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
                boolean movedWhite = chessGame.move(ws,we);
                boolean movedBlack = chessGame.move(bs,be);

                //then
                assertFalse(movedWhite);
                verifyPieceWithPosition(ws,originWhiteType);

                assertFalse(movedBlack);
                verifyPieceWithPosition(bs,originBlackType);
            }
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

}
