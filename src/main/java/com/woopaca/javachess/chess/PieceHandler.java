package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Direction;
import com.woopaca.javachess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class PieceHandler {

    private final Board board;

    public PieceHandler(Board board) {
        this.board = board;
    }

    public void movePiece(MoveCommand moveCommand) {
        Position sourcePosition = moveCommand.getSourcePosition();
        Position targetPosition = moveCommand.getTargetPosition();
        Piece piece = board.findPiece(sourcePosition);
        if (!isPossibleMovePosition(piece, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 기물을 이동할 수 없습니다!");
        }
        board.move(moveCommand.getSourcePosition(), moveCommand.getTargetPosition());
    }

    public boolean isPossibleMovePosition(Piece piece, Position targetPosition) {
        List<Position> possibleMovePositions = getPossibleMovePositions(piece);
        return possibleMovePositions.contains(targetPosition);
    }

    public List<Position> getPossibleMovePositions(Piece piece) {
        List<Position> possiblePositions = new ArrayList<>();
        List<Direction> directions = piece.getDirections();
        for (Direction direction : directions) {
            Position position = piece.getPosition();
            while (true) {
                position = position.moveIn(direction);
                if (!position.isValid()) {
                    break;
                }

                Piece findPiece = board.findPiece(position);
                if (findPiece.getColor() == piece.getColor()) {
                    break;
                }
                possiblePositions.add(position);
                if (findPiece.getColor() != Color.NOCOLOR) {
                    break;
                }
            }
        }
        return possiblePositions;
    }

}
