package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Color;
import com.woopaca.javachess.pieces.Direction;
import com.woopaca.javachess.pieces.Piece;
import com.woopaca.javachess.pieces.PieceFactory;

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
        Piece targetPositionPiece = board.findPiece(targetPosition);
        if (!isPossibleMovePosition(piece, sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 기물을 이동할 수 없습니다!");
        }

        if (isCapturing(targetPositionPiece, piece)) {
            capturing(targetPosition, piece, sourcePosition);
            return;
        }

        board.placePiece(sourcePosition, targetPositionPiece);
        board.placePiece(targetPosition, piece);
    }

    private static boolean isCapturing(Piece targetPositionPiece, Piece piece) {
        return !targetPositionPiece.isBlank() && targetPositionPiece.getColor() != piece.getColor();
    }

    private void capturing(Position targetPosition, Piece piece, Position sourcePosition) {
        board.placePiece(targetPosition, piece);
        board.placePiece(sourcePosition, PieceFactory.createBlank());
    }

    public boolean isPossibleMovePosition(Piece piece, Position sourcePosition, Position targetPosition) {
        List<Position> possibleMovePositions = getPossibleMovePositions(piece, sourcePosition);
        return possibleMovePositions.contains(targetPosition);
    }

    public List<Position> getPossibleMovePositions(Piece piece, Position sourcePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        List<Direction> directions = piece.getDirections();
        for (Direction direction : directions) {
            Position position = sourcePosition;
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
