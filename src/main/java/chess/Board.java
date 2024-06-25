package chess;

import chess.pieces.Pawn;

public class Board {

    private final char[][] MAP;
    private final int BOARD_SIZE = 8;
    private final int BLACK_PAWN_RANK = 1;
    private final int WHITE_PAWN_RANK = 6;

    private final char EMPTY = '.';

    public Board() {
        MAP = new char[BOARD_SIZE][BOARD_SIZE];
    }

    public void initialize() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                MAP[i][j] = EMPTY;
            }
        }
        initBlackPawn();
        initWhitePawn();
    }

    public String getWhitePawnsResult(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < BOARD_SIZE; i++) {
            sb.append(MAP[WHITE_PAWN_RANK][i]);
        }
        return sb.toString();
    }

    public String getBlackPawnsResult(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < BOARD_SIZE; i++) {
            sb.append(MAP[BLACK_PAWN_RANK][i]);
        }
        return sb.toString();
    }

    private void initBlackPawn() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            MAP[BLACK_PAWN_RANK][i] = Pawn.BLACK_REPRESENTATION;
        }
    }

    private void initWhitePawn() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            MAP[WHITE_PAWN_RANK][i] = Pawn.WHITE_REPRESENTATION;
        }
    }



}
