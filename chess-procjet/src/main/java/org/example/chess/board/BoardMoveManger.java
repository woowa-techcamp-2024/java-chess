package org.example.chess.board;

import java.util.List;
import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.PieceFactory;

public class BoardMoveManger {

    private final Board board;

    public BoardMoveManger(Board board) {
        this.board = board;
    }

    public Piece findPiece(Board board, String pos) {
        Position position = new Position(pos);
        int r = position.getR();
        int c = position.getC();

        return board.getBoard().get(r).getPieces().get(c);
    }

    public void move(Board board, String position, Piece piece) {
        //TODO: 해당 말이 해당 위치로 이동이 가능한 말인지 확인하는 로직 필요.
        Position pos = new Position(position);
        int r = pos.getR();
        int c = pos.getC();

        Rank row = board.getBoard().get(r);
        row.changePiece(c, piece);
    }

    public void move(Board board, String source, String destination) {
        //source 위치의 piece를 찾는다.
        Piece piece = findPiece(board, source);

        //source 위치를 빈 공간으로 변경
        move(board, source, PieceFactory.createBlank());

        //해당 위치에 from 위치의 piece를 놓는다.
        move(board, destination, piece);
    }
}
