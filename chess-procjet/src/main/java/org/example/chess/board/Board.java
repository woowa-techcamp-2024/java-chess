package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Color;
import org.example.chess.pieces.Piece.PieceFactory;
import org.example.chess.pieces.Piece.Type;

public class Board {

    protected static final int BOARD_SIZE = 8;
    private static final int MAX_PIECES = 32;
    private static final int BLACK_INIT_ROW = 0;
    private static final int BLACK_PAWN_INIT_ROW = 1;
    private static final int WHITE_INIT_ROW = 7;
    private static final int WHITE_PAWN_INIT_ROW = 6;

    private final List<Rank> board = new ArrayList<>();

    public void initialize() {
        initializeEmpty();
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
            board.get(BLACK_PAWN_INIT_ROW).changePiece(i, blackPawn);
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

        board.set(BLACK_INIT_ROW, blackPiecesExceptPawn);
    }

    private void initWhitePiece() {
        // 폰 8개, 루크2개, 나이트 2개, 비숍2개, 킹1개, 퀸 1개
        for (int i = 0; i < BOARD_SIZE; i++) {
            Piece whitePawn = PieceFactory.createWhitePawn();
            board.get(WHITE_PAWN_INIT_ROW).changePiece(i, whitePawn);
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

        board.set(WHITE_INIT_ROW, whitePieceExceptPawn);
    }

    public void print() {
        System.out.println(showBoard());
    }

    public Board() {
    }

    public int pieceCount() {
        return (int) board.stream()
                .flatMap(rank -> rank.getPieces().stream())
                .filter(p -> (p.isBlack() || p.isWhite()))
                .count();
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (Rank row : board) {
            sb.append(appendNewLine(row.getPieces().stream()
                    .map(Piece::getRepresentation)
                    .collect(Collectors.joining())));
        }
        return sb.toString();
    }

    public int countPiecesByColorAndType(Color color, Type type) {
        int count = 0;
        for (Rank rank : board) {
            if (color == Color.BLACK) {
                count += rank.countBlackPiecesWithType(type);
                continue;
            }

            if (color == Color.WHITE) {
                count += rank.countWhitePiecesWithType(type);
                continue;
            }
        }

        return count;
    }

    public Piece findPiece(String pos) {
        Position position = new Position(pos);
        int r = position.getR();
        int c = position.getC();

        return board.get(r).getPieces().get(c);
    }

    public void initializeEmpty() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Rank rank = new Rank();
            for (int j = 0; j < BOARD_SIZE; j++) {
                rank.addPiece(PieceFactory.createBlank());
            }
            board.add(rank);
        }
    }

    public void move(String position, Piece piece) {
        Position pos = new Position(position);
        int r = pos.getR();
        int c = pos.getC();

        Rank row = board.get(r);
        row.changePiece(c, piece);
    }
}
