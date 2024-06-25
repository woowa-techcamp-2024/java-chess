package com.wootecam.chess;

import static com.wootecam.chess.ChessBoard.MAX_COL;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final ChessBoard chessBoard;
    private final List<Piece> pieces;

    public Board() {
        chessBoard = new ChessBoard();
        this.pieces = new ArrayList<>();
    }

    public void initialize() {
        initializePieces(Color.BLACK, MAX_COL);
        initializePieces(Color.WHITE, chessBoard.size() - 2 * MAX_COL);
    }

    private void initializePieces(Color color, int startIndex) {
        for (int i = 0; i < MAX_COL; ++i) {
            Piece piece = new Piece(PieceType.PAWN, color);
            add(piece, startIndex + i);
        }
    }

    public void add(Piece piece) {
        chessBoard.add(piece);
        pieces.add(piece);
    }

    public void add(Piece piece, int index) {
        chessBoard.add(piece, index);
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(int index) {
        if (index < 0 || index >= pieces.size()) {
            throw new IllegalArgumentException("The specified pawn is not found");
        }
        return pieces.get(index);
    }

    public String getWhitePawnsResult() {
        return getPawnResult(Color.WHITE);
    }

    public String getBlackPawnsResult() {
        return getPawnResult(Color.BLACK);
    }

    private String getPawnResult(Color color) {
        return pieces.stream()
                .filter(p -> p.getColor() == color)
                .map(p -> p.getRepresentation().value)
                .collect(Collectors.joining());
    }

    public String print() {
        return chessBoard.print();
    }
}
