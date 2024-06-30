package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Move;
import org.example.chess.pieces.global.MoveSeq;
import org.example.chess.pieces.global.Position;

import java.util.List;

public class MoveVerifier {
    private Board board;

    public MoveVerifier(Board board) {
        this.board = board;
    }

    public boolean isMovable(Position from, Position to) {
        Piece piece = board.findPiece(from);

        validateNotEmpty(piece);

        List<MoveSeq> moveSeqs = piece.getMoveSeqs();
        for(MoveSeq moveSeq: moveSeqs) {
            if (isReachable(from, moveSeq, to)) {
                System.out.println("move success with "+ from + " -> " + to);
                return true;
            }
        }

        System.out.println("move fail with "+ from + " -> " + to);
        return false;
    }

    private void validateNotEmpty(Piece piece) {
        if (piece.isBlank()) {
            throw new RuntimeException("시작 자리에 말이 존재하지 않습니다.");
        }
    }

    private boolean isReachable(Position from, MoveSeq moveSeq, Position to) {
        Position cur = from.copy();
        for (Move move : moveSeq.getMoves()) {
            if ( !isOnceMovable(cur, move)) {
                return false;
            }
            cur = cur.move(move.getDir());
            if (cur.equals(to)) {
                return true;
            }
        }

        return false;
    }

    private boolean isOnceMovable(Position from, Move move){
        if(!from.movable(move.getDir())) {
            return false;
        }

        Piece startPiece = board.findPiece(from);
        Piece destPiece = board.findPiece(from.move(move.getDir()));

        if (destPiece.getName() == Piece.Type.NO_PIECE) {
            return true;
        }

        return move.isJumpable() || startPiece.isSameColor(destPiece.getColor());
    }
}
