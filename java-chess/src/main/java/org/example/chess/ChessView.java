package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Order;
import org.example.chess.pieces.global.Position;

import java.util.List;

public class ChessView {
    private Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public int countPieces() {
        return board.countPieces();
    }

    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }

    //TODO representation 리팩토링
    public String getRepresentation() {
        return board.getRepresentation();
    }

    public String showBoard() {
        return board.showBoard();
    }

    public double calculatePoint(Piece.Color color) {
        return board.calculatePoint(color);
    }

    public List<Piece> showSortedOrder(Order order) {
        return board.sort(order);
    }
}
