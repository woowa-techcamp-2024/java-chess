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
        putBlackPieces();
        putWhitePieces();
    }

    private void putBlackPieces() {
        add(Piece.createBlackRook());
        add(Piece.createBlackKnight());
        add(Piece.createBlackBishop());
        add(Piece.createBlackQueen());
        add(Piece.createBlackKing());
        add(Piece.createBlackBishop());
        add(Piece.createBlackKnight());
        add(Piece.createBlackRook());
        for (int i = 0; i < MAX_COL; ++i) {
            add(Piece.createBlackPawn());
        }
    }

    private void putWhitePieces() {
        int startIdx = ChessBoard.TOTAL_CELLS - 2 * MAX_COL;

        for (int i = 0; i < MAX_COL; ++i) {
            add(Piece.createWhitePawn(), startIdx++);
        }
        add(Piece.createWhiteRook(), startIdx++);
        add(Piece.createWhiteKnight(), startIdx++);
        add(Piece.createWhiteBishop(), startIdx++);
        add(Piece.createWhiteQueen(), startIdx++);
        add(Piece.createWhiteKing(), startIdx++);
        add(Piece.createWhiteBishop(), startIdx++);
        add(Piece.createWhiteKnight(), startIdx++);
        add(Piece.createWhiteRook(), startIdx);
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
                .filter(p -> p.getColor() == color && p.getType() == PieceType.PAWN)
                .map(p -> p.getRepresentation().value)
                .collect(Collectors.joining());
    }

    public String print() {
        return chessBoard.print();
    }
}
