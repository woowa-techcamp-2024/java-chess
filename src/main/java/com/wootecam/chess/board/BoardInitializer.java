package com.wootecam.chess.board;

import com.wootecam.chess.pieces.Piece;

public class BoardInitializer {

    public void initialize(Board board) {
        addBlackPieces(board);
        addBlackPawns(board);
        addWhitePieces(board);
        addWhitePawns(board);
    }

    private void addBlackPieces(Board board) {
        board.add(Piece.createBlackRook(), new Position("a8"));
        board.add(Piece.createBlackKnight(), new Position("b8"));
        board.add(Piece.createBlackBishop(), new Position("c8"));
        board.add(Piece.createBlackQueen(), new Position("d8"));
        board.add(Piece.createBlackKing(), new Position("e8"));
        board.add(Piece.createBlackBishop(), new Position("f8"));
        board.add(Piece.createBlackKnight(), new Position("g8"));
        board.add(Piece.createBlackRook(), new Position("h8"));
    }

    private void addBlackPawns(Board board) {
        for (char c = 'a'; c <= 'h'; c++) {
            board.add(Piece.createBlackPawn(), new Position(c + "7"));
        }
    }

    private void addWhitePieces(Board board) {
        board.add(Piece.createWhiteRook(), new Position("a1"));
        board.add(Piece.createWhiteKnight(), new Position("b1"));
        board.add(Piece.createWhiteBishop(), new Position("c1"));
        board.add(Piece.createWhiteQueen(), new Position("d1"));
        board.add(Piece.createWhiteKing(), new Position("e1"));
        board.add(Piece.createWhiteBishop(), new Position("f1"));
        board.add(Piece.createWhiteKnight(), new Position("g1"));
        board.add(Piece.createWhiteRook(), new Position("h1"));
    }

    private void addWhitePawns(Board board) {
        for (char c = 'a'; c <= 'h'; c++) {
            board.add(Piece.createWhitePawn(), new Position(c + "2"));
        }
    }
}