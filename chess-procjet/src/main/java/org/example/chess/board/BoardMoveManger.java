package org.example.chess.board;

import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceFactory;

public class BoardMoveManger {

    private final Board board;

    public BoardMoveManger(Board board) {
        this.board = board;
    }

    public Piece findPiece(String pos) {
        Position position = new Position(pos);
        int r = position.getR();
        int c = position.getC();

        return board.getBoard().get(r).getPieces().get(c);
    }

    public void move(String position, Piece piece) {
        Position pos = new Position(position);
        int r = pos.getR();
        int c = pos.getC();

        Rank row = board.getBoard().get(r);
        row.changePiece(c, piece);
    }

    public void move(String source, String destination) {
        //source 위치의 piece를 찾는다.
        Piece piece = findPiece(source);

        if (!piece.isValidMove(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        //source 위치를 빈 공간으로 변경
        move(source, PieceFactory.createBlank());

        //해당 위치에 from 위치의 piece를 놓는다.
        move(destination, piece);
    }
}
