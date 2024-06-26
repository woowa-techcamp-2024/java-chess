package chess;

import chess.pieces.Piece;

import static utils.StringUtils.appendNewLine;

public class Board {

    private final char[][] MAP;
    private final int BOARD_SIZE = 8;
    private final int BLACK_PAWN_RANK = 1;
    private final int WHITE_PAWN_RANK = 6;

    private final char EMPTY = '.';
    private int pieceNumber;

    public Board() {
        MAP = new char[BOARD_SIZE][BOARD_SIZE];
    }

    public int pieceCount() {
        return pieceNumber;
    }

    public void initialize() {
        pieceNumber = 32;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                MAP[i][j] = EMPTY;
            }
        }

        MAP[0][0] = Piece.BLACK_ROOK_REPRESENTATION;
        MAP[0][1] = Piece.BLACK_KNIGHT_REPRESENTATION;
        MAP[0][2] = Piece.BLACK_BISHOP_REPRESENTATION;
        MAP[0][3] = Piece.BLACK_QUEEN_REPRESENTATION;
        MAP[0][4] = Piece.BLACK_KING_REPRESENTATION;
        MAP[0][5] = Piece.BLACK_BISHOP_REPRESENTATION;
        MAP[0][6] = Piece.BLACK_KNIGHT_REPRESENTATION;
        MAP[0][7] = Piece.BLACK_ROOK_REPRESENTATION;

        MAP[7][0] = Piece.WHITE_ROOK_REPRESENTATION;
        MAP[7][1] = Piece.WHITE_KNIGHT_REPRESENTATION;
        MAP[7][2] = Piece.WHITE_BISHOP_REPRESENTATION;
        MAP[7][3] = Piece.WHITE_QUEEN_REPRESENTATION;
        MAP[7][4] = Piece.WHITE_KING_REPRESENTATION;
        MAP[7][5] = Piece.WHITE_BISHOP_REPRESENTATION;
        MAP[7][6] = Piece.WHITE_KNIGHT_REPRESENTATION;
        MAP[7][7] = Piece.WHITE_ROOK_REPRESENTATION;

        initBlackPawn();
        initWhitePawn();
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(MAP[WHITE_PAWN_RANK][i]);
        }
        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            sb.append(MAP[BLACK_PAWN_RANK][i]);
        }
        return sb.toString();
    }

    private void initBlackPawn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            MAP[BLACK_PAWN_RANK][i] = Piece.BLACK_PAWN_REPRESENTATION;
        }
    }

    private void initWhitePawn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            MAP[WHITE_PAWN_RANK][i] = Piece.WHITE_PAWN_REPRESENTATION;
        }
    }

    public String showBoard() {
        StringBuilder board = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            StringBuilder rank = new StringBuilder();
            for (int j = 0; j < BOARD_SIZE; j++) {
                rank.append(MAP[i][j]);
            }
            board.append(appendNewLine(rank.toString()));
        }
        return board.toString();
    }

}