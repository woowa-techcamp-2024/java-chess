package org.example.chess.board;

import static org.example.chess.board.Board.BOARD_SIZE;

import java.util.List;
import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.PieceFactory;

public class BoardInitializeManger {

    // 하나만 존재하면 되고 생성할 필요 없음 -> 싱글톤 고려
    private static final int BLACK_INIT_ROW = 0;
    private static final int BLACK_PAWN_INIT_ROW = 1;
    private static final int WHITE_INIT_ROW = 7;
    private static final int WHITE_PAWN_INIT_ROW = 6;

    private final Board board;

    public BoardInitializeManger(Board board) {
        this.board = board;
    }

    public void initialize(Board board) {
        initializeEmpty(board);
        initBlackPiece(board);
        initWhitePiece(board);
    }

    private void initBlackPiece(Board board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece blackPawn = PieceFactory.createBlackPawn();
            board.getBoard().get(BLACK_PAWN_INIT_ROW).changePiece(i, blackPawn);
        }

        Rank blackPiecesExceptPawn = new Rank();
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackRook());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackQueen());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackKing());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.addPiece(PieceFactory.createBlackRook());

        board.getBoard().set(BLACK_INIT_ROW, blackPiecesExceptPawn);
    }

    private void initWhitePiece(Board board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece whitePawn = PieceFactory.createWhitePawn();
            board.getBoard().get(WHITE_PAWN_INIT_ROW).changePiece(i, whitePawn);
        }

        Rank whitePieceExceptPawn = new Rank();
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteRook());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteQueen());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteKing());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.addPiece(PieceFactory.createWhiteRook());

        board.getBoard().set(WHITE_INIT_ROW, whitePieceExceptPawn);
    }

    public void initializeEmpty(Board board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = new Rank();
            for (int j = 0; j < BOARD_SIZE; j++) {
                rank.addPiece(PieceFactory.createBlank());
            }
            board.getBoard().add(rank);
        }
    }
}
