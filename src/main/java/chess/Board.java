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

        MAP[0][0] = Piece.Type.ROOK.getBlackRepresentation();
        MAP[0][1] = Piece.Type.KNIGHT.getBlackRepresentation();
        MAP[0][2] = Piece.Type.BISHOP.getBlackRepresentation();
        MAP[0][3] = Piece.Type.QUEEN.getBlackRepresentation();
        MAP[0][4] = Piece.Type.KING.getBlackRepresentation();
        MAP[0][5] = Piece.Type.BISHOP.getBlackRepresentation();
        MAP[0][6] = Piece.Type.KNIGHT.getBlackRepresentation();
        MAP[0][7] = Piece.Type.ROOK.getBlackRepresentation();

        MAP[7][0] = Piece.Type.ROOK.getWhiteRepresentation();
        MAP[7][1] = Piece.Type.KNIGHT.getWhiteRepresentation();
        MAP[7][2] = Piece.Type.BISHOP.getWhiteRepresentation();
        MAP[7][3] = Piece.Type.QUEEN.getWhiteRepresentation();
        MAP[7][4] = Piece.Type.KING.getWhiteRepresentation();
        MAP[7][5] = Piece.Type.BISHOP.getWhiteRepresentation();
        MAP[7][6] = Piece.Type.KNIGHT.getWhiteRepresentation();
        MAP[7][7] = Piece.Type.ROOK.getWhiteRepresentation();

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
            MAP[BLACK_PAWN_RANK][i] = Piece.Type.PAWN.getBlackRepresentation();
        }
    }

    private void initWhitePawn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            MAP[WHITE_PAWN_RANK][i] = Piece.Type.PAWN.getWhiteRepresentation();
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