package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Piece.createBlackPawn;
import static org.example.utils.StringUtils.*;

public class Board {

    static final String NEWLINE = System.lineSeparator();
    static final int size = 8;
    static final int EMPTY_INDEX = -1;
    static char EMPTY_CHAR = '.';
    static int[][] board = new int[size][size];
    static List<Piece> pieceList = new ArrayList<>();
    static List<Piece> whitePieceList = new ArrayList<>();
    static List<Piece> blackPieceList = new ArrayList<>();

    public void add(Piece piece) {
        pieceList.add(piece);
    }

    public int size() {
        return pieceList.size();
    }

    public Piece findPawn(int i) {
        return pieceList.get(i);
    }

    public void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = EMPTY_INDEX;
            }    
        }
        
        for (int i = 0; i < size; i++) {
            Piece whitePiece = new Piece(Piece.WHITE_COLOR, PieceType.PAWN);
            whitePieceList.add(whitePiece);
            pieceList.add(whitePiece);
            // Todo : 임의로 pawnList 의 Index 를 가지게 하였다
            board[1][i] = pieceList.size() - 1;

            Piece blackPiece = createBlackPawn();
            blackPieceList.add(blackPiece);
            pieceList.add(blackPiece);
            // Todo : ''
            board[6][i] = pieceList.size() - 1;
        }
    }
    
    public String print() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int index = board[i][j];
                sb.append(index != -1 ? findPawn(index).getRepresentation() : EMPTY_CHAR);
            }
            appendNewLine(sb);
        }

        return sb.toString();
    }


    public String getWhitePawnsResult() {
        return getPawnResult(Piece.WHITE_REPRESENTATION, whitePieceList);
    }

    public String getBlackPawnsResult() {
        return getPawnResult(Piece.BLACK_REPRESENTATION, blackPieceList);
    }

    private static String getPawnResult(char representation, List<Piece> pieceList) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(representation).repeat(pieceList.size()));
        return sb.toString();
    }
}
