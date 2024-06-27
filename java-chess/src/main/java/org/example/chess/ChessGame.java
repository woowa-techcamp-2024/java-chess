package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Position;

public class ChessGame {
    private Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void moveTo(Position from, Position to) throws RuntimeException{
        // board.getPiece()
        Piece piece = board.findPiece(from);
//        if (piece.isBlank()) {
//            throw new RuntimeException("시작 칸에 piece 가 없습니다." + from.toString());
//        }

        // 해당 Piece 의 Strategy 는 무엇?
        // 기본전략 = piece.getStrategy()

        // 전략에 맞게 움직일 수 있는지 검증

        // board 의 move 는 정말 순수하게 move 만, move
    }

    private void validateNotEmpty(Piece piece) {
        if (piece.isBlank()) {
            throw new RuntimeException("시작 자리에 말이 존재하지 않습니다.");
        }
    }

    public void setPiece(Piece piece, Position position) {
        board.setPiece(position, piece);
    }

    public void initializeBoard() {
        board.initialize();
    }

    public void emptyBoard() {
        board.initializeEmpty();
    }
}
