package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.PieceFactory;

public class Board {

    private static final int BOARD_SIZE = 8;
    private static final int MAX_PIECES = 32;
    private static final int BLACK_INIT_ROW = 0;
    private static final int BLACK_PAWN_INIT_ROW = 1;
    private static final int WHITE_INIT_ROW = 7;
    private static final int WHITE_PAWN_INIT_ROW = 6;

    private final List<List<Piece>> board = new ArrayList<>();

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Piece> row = new ArrayList<>();
            for (int j = 0; j < BOARD_SIZE; j++) {
                row.add(PieceFactory.createBlank());
            }
            board.add(row);
        }
        addPieceToBoard();
    }

    private void addPieceToBoard() {
        initBlackPiece();
        initWhitePiece();
    }

    private void initBlackPiece() {
        // 폰 8개, 루크2개, 나이트 2개, 비숍2개, 킹1개, 퀸 1개
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece blackPawn = PieceFactory.createBlackPawn();
            board.get(BLACK_PAWN_INIT_ROW).set(i, blackPawn);
        }

        List<Piece> blackPiecesExceptPawn = new ArrayList<>();
        blackPiecesExceptPawn.add(PieceFactory.createBlackRook());
        blackPiecesExceptPawn.add(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.add(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.add(PieceFactory.createBlackQueen());
        blackPiecesExceptPawn.add(PieceFactory.createBlackKing());
        blackPiecesExceptPawn.add(PieceFactory.createBlackBishop());
        blackPiecesExceptPawn.add(PieceFactory.createBlackKnight());
        blackPiecesExceptPawn.add(PieceFactory.createBlackRook());

        board.set(BLACK_INIT_ROW, blackPiecesExceptPawn);
    }

    private void initWhitePiece() {
        // 폰 8개, 루크2개, 나이트 2개, 비숍2개, 킹1개, 퀸 1개
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece whitePawn = PieceFactory.createWhitePawn();
            board.get(WHITE_PAWN_INIT_ROW).set(i, whitePawn);
        }

        List<Piece> whitePieceExceptPawn = new ArrayList<>();
        whitePieceExceptPawn.add(PieceFactory.createWhiteRook());
        whitePieceExceptPawn.add(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.add(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.add(PieceFactory.createWhiteQueen());
        whitePieceExceptPawn.add(PieceFactory.createWhiteKing());
        whitePieceExceptPawn.add(PieceFactory.createWhiteBishop());
        whitePieceExceptPawn.add(PieceFactory.createWhiteKnight());
        whitePieceExceptPawn.add(PieceFactory.createWhiteRook());

        board.set(WHITE_INIT_ROW, whitePieceExceptPawn);
    }

    public void print() {
        System.out.println(showBoard());
    }

    public Board() {
    }

    public int pieceCount() {
        return (int) board.stream()
                .flatMap(List::stream)
                .filter(p -> (p.isBlack() || p.isWhite()))
                .count();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (List<Piece> row : board) {
            sb.append(appendNewLine(row.stream()
                    .map(Piece::getRepresentation)
                    .collect(Collectors.joining())));
        }
        return sb.toString();
    }
}
