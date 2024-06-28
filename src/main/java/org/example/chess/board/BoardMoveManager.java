package org.example.chess.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Pawn;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.Piece.Color;
import org.example.chess.pieces.Piece.Type;
import org.example.chess.pieces.PieceFactory;

public class BoardMoveManager {

    private final Board board;
    private Color currentTurn;

    public BoardMoveManager(Board board) {
        this.board = board;
        this.currentTurn = Color.WHITE;
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
        Position from = new Position(source);
        Position to = new Position(destination);
        List<Position> positionInPath = Collections.emptyList();
        Piece piece = findPiece(source);

        validateMoveCommand(from, to);

        if (piece.getType() != Type.KNIGHT) {
            positionInPath = getPositionInPath(from, to);
        }

        boolean isEnemyAtDestination = !findPiece(to).equals(PieceFactory.createBlank()) && findPiece(to).getColor() != piece.getColor();

        if (piece.isPawn()) {
            if (!((Pawn) piece).isValidMove(from, to, positionInPath, isEnemyAtDestination)) {
                throw new IllegalArgumentException("폰은 해당 위치로 이동할 수 없습니다.");
            }
        } else {
            if (!piece.isValidMove(source, destination, positionInPath)) {
                throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
            }
        }

        if (piece.isPawn()) {
            ((Pawn) piece).switchFlagToFalse();
        }

        move(source, PieceFactory.createBlank());
        move(destination, piece);

        switchTurn();
    }

    private void switchTurn() {
        currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void validateMoveCommand(Position from, Position to) {
        validateTurn(findPiece(from));
        if (from.equals(to)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        if (findPiece(from).getColor() == findPiece(to).getColor()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

    }

    private void validateTurn(Piece piece) {
        if (piece.getColor() != currentTurn) {
            throw new IllegalArgumentException("현재 턴이 아닌 기물은 이동할 수 없습니다.");
        }
    }

    private List<Position> getPositionInPath(Position from, Position to) {
        List<Position> positions = new ArrayList<>();

        int deltaR = Integer.signum(to.getR() - from.getR());
        int deltaC = Integer.signum(to.getC() - from.getC());

        int r = from.getR() + deltaR;
        int c = from.getC() + deltaC;

        while (r != to.getR() || c != to.getC()) {
            Position position = new Position(r, c);
            if (findPiece(position).isWhite() || findPiece(position).isBlack()) {
                positions.add(new Position(r,c));
            }
            r += deltaR;
            c += deltaC;
        }
        return positions;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
