package com.woopaca.javachess.chess;

import com.woopaca.javachess.chess.pieces.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.woopaca.javachess.chess.utils.StringUtils.appendNewLine;

public class Board {

    public static final int BOARD_SIZE = 8;
    public static final int PAWNS_COUNT = 8;
    public static final int BLACK_PAWNS_ROW = 1;
    public static final int WHITE_PAWNS_ROW = 6;

    private final Piece[][] pieces = new Piece[BOARD_SIZE][BOARD_SIZE];

    public void add(Piece piece) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            if (pieces[i / BOARD_SIZE][i % BOARD_SIZE] == null) {
                pieces[i / BOARD_SIZE][i % BOARD_SIZE] = piece;
                break;
            }
        }
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            if (pieces[i / BOARD_SIZE][i % BOARD_SIZE] != null) {
                size++;
            }
        }
        return size;
    }

    public Piece findPiece(int index) {
        int count = 0;
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            Piece piece = pieces[i / BOARD_SIZE][i % BOARD_SIZE];
            if (piece != null) {
                count++;
            }
            if (count == index + 1) {
                return piece;
            }
        }
        return null;
    }

    public void initialize() {
        addPawns();
        addBlackMajorPieces();
        addWhiteMajorPieces();
    }

    private void addPawns() {
        for (int i = 0; i < PAWNS_COUNT; i++) {
            pieces[BLACK_PAWNS_ROW][i] = Piece.createBlackPawn();
            pieces[WHITE_PAWNS_ROW][i] = Piece.createWhitePawn();
        }
    }

    private void addBlackMajorPieces() {
        pieces[0][0] = Piece.createBlackRook();
        pieces[0][1] = Piece.createBlackKnight();
        pieces[0][2] = Piece.createBlackBishop();
        pieces[0][3] = Piece.createBlackQueen();
        pieces[0][4] = Piece.createBlackKing();
        pieces[0][5] = Piece.createBlackBishop();
        pieces[0][6] = Piece.createBlackKnight();
        pieces[0][7] = Piece.createBlackRook();
    }

    private void addWhiteMajorPieces() {
        pieces[7][0] = Piece.createWhiteRook();
        pieces[7][1] = Piece.createWhiteKnight();
        pieces[7][2] = Piece.createWhiteBishop();
        pieces[7][3] = Piece.createWhiteQueen();
        pieces[7][4] = Piece.createWhiteKing();
        pieces[7][5] = Piece.createWhiteBishop();
        pieces[7][6] = Piece.createWhiteKnight();
        pieces[7][7] = Piece.createWhiteRook();
    }

    public String getWhitePawnsResult() {
        List<Piece> whitePawns = Arrays.asList(pieces[WHITE_PAWNS_ROW]);
        return generatePiecesResult(whitePawns);
    }

    public String getBlackPawnsResult() {
        List<Piece> blackPawns = Arrays.asList(pieces[BLACK_PAWNS_ROW]);
        return generatePiecesResult(blackPawns);
    }

    public String print() {
        StringBuilder boardResult = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            List<Piece> row = Arrays.asList(pieces[i]);
            String rowResult = generatePiecesResult(row);
            boardResult.append(appendNewLine(rowResult));
        }

        return boardResult.toString();
    }

    private String generatePiecesResult(List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> piece == null ? "." : String.valueOf(piece.getRepresentation()))
                .collect(Collectors.joining());
    }

    public int pieceCount() {
        return size();
    }

    public String showBoard() {
        return print();
    }

}
