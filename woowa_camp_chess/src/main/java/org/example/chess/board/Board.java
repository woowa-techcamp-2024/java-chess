package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.List;

import static org.example.chess.pieces.Piece.*;
import static org.example.chess.pieces.Piece.createBlackPawn;
import static org.example.utils.StringUtils.*;

public class Board {

    static final String NEWLINE = System.lineSeparator();
    static final int size = 8;
    static final int EMPTY_INDEX = -1;
    static char EMPTY_CHAR = '.';
    int[][] board = new int[size][size];
    List<Piece> pieceList = new ArrayList<>();
    List<Piece> whitePieceList = new ArrayList<>();
    List<Piece> blackPieceList = new ArrayList<>();

    public void add(Piece piece) {
        pieceList.add(piece);
    }

    public int pieceCount() {
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

        // Pawn 기물 초기화
        for (int i = 0; i < size; i++) {
            Piece whitePiece = createWhitePawn();
            whitePieceList.add(whitePiece);
            pieceList.add(whitePiece);
            board[1][i] = pieceList.size() - 1;

            Piece blackPiece = createBlackPawn();
            blackPieceList.add(blackPiece);
            pieceList.add(blackPiece);
            board[6][i] = pieceList.size() - 1;
        }

        // Knight 기물 초기화
        Piece whiteKnight1 = createWhiteKnight();
        Piece whiteKnight2 = createWhiteKnight();
        Piece blackKnight1 = createBlackKnight();
        Piece blackKnight2 = createBlackKnight();
        pieceList.add(whiteKnight1);
        pieceList.add(whiteKnight2);
        pieceList.add(blackKnight1);
        pieceList.add(blackKnight2);
        board[0][1] = pieceList.size() - 4;
        board[0][6] = pieceList.size() - 3;
        board[7][1] = pieceList.size() - 2;
        board[7][6] = pieceList.size() - 1;

        // Bishop 기물 초기화
        Piece whiteBishop1 = createWhiteBishop();
        Piece whiteBishop2 = createWhiteBishop();
        Piece blackBishop1 = createBlackBishop();
        Piece blackBishop2 = createBlackBishop();
        pieceList.add(whiteBishop1);
        pieceList.add(whiteBishop2);
        pieceList.add(blackBishop1);
        pieceList.add(blackBishop2);
        board[0][2] = pieceList.size() - 4;
        board[0][5] = pieceList.size() - 3;
        board[7][2] = pieceList.size() - 2;
        board[7][5] = pieceList.size() - 1;

        // Rook 기물 초기화
        Piece whiteRook1 = createWhiteRook();
        Piece whiteRook2 = createWhiteRook();
        Piece blackRook1 = createBlackRook();
        Piece blackRook2 = createBlackRook();
        pieceList.add(whiteRook1);
        pieceList.add(whiteRook2);
        pieceList.add(blackRook1);
        pieceList.add(blackRook2);
        board[0][0] = pieceList.size() - 4;
        board[0][7] = pieceList.size() - 3;
        board[7][0] = pieceList.size() - 2;
        board[7][7] = pieceList.size() - 1;

        // Queen 기물 초기화
        Piece whiteQueen = createWhiteQueen();
        Piece blackQueen = createBlackQueen();
        pieceList.add(whiteQueen);
        pieceList.add(blackQueen);
        board[0][3] = pieceList.size() - 2;
        board[7][3] = pieceList.size() - 1;

        // King 기물 초기화
        Piece whiteKing = createWhiteKing();
        Piece blackKing = createBlackKing();
        pieceList.add(whiteKing);
        pieceList.add(blackKing);
        board[0][4] = pieceList.size() - 2;
        board[7][4] = pieceList.size() - 1;

    }
    
    public String showBoard() {
        StringBuilder print = new StringBuilder();

        for (int i = 0; i < size; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < size; j++) {
                int index = board[i][j];
                row.append(index != -1 ? findPawn(index).getRepresentation() : EMPTY_CHAR);
            }
            print.append(appendNewLine(row.toString()));
        }

        return print.toString();
    }


    public String getWhitePawnsResult() {
        return getPawnResult(WHITE_REPRESENTATION, whitePieceList);
    }

    public String getBlackPawnsResult() {
        return getPawnResult(BLACK_REPRESENTATION, blackPieceList);
    }

    private static String getPawnResult(char representation, List<Piece> pieceList) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(representation).repeat(pieceList.size()));
        return sb.toString();
    }
}
