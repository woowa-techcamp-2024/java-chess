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

    public String showBoard() {
        return board.showBoard();
    }

    public List<Piece> showSortedOrder(Order order) {
        return board.sort(order);
    }
}
