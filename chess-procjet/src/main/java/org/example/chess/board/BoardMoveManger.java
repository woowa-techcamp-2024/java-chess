package org.example.chess.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Type;
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

    public Piece findPiece(Position position) {
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
        Position from = new Position(source);
        Position to = new Position(destination);
        List<Position> positionInPath = Collections.emptyList();
        Piece piece = findPiece(source);

        if (piece.getType() != Type.KNIGHT) {
            positionInPath = getPositionInPath(from, to);
        }

        if (!piece.isValidMove(source, destination, positionInPath)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        if (isPathBlocked(positionInPath)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }

        //source 위치를 빈 공간으로 변경
        move(source, PieceFactory.createBlank());

        //해당 위치에 from 위치의 piece를 놓는다.
        move(destination, piece);
    }

    private boolean isPathBlocked(List<Position> path) {
        Piece blank = PieceFactory.createBlank();
        for (Position position : path) {
            if (!findPiece(position).equals(blank)) {
                return true; //경로에 기물 존재
            }
        }
        return false;
    }

    private List<Position> getPositionInPath(Position from, Position to) {
        List<Position> positions = new ArrayList<>();

        int deltaR = Integer.signum(to.getR() - from.getR());
        int deltaC = Integer.signum(to.getC() - from.getC());

        int r = from.getR() + deltaR;
        int c = from.getC() + deltaC;

        while (r != to.getR() || c != to.getC()) {
            positions.add(new Position(r,c));
            r += deltaR;
            c += deltaC;
        }
        return positions;
    }

}
