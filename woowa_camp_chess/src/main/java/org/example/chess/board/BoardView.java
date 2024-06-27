package org.example.chess.board;

abstract class BoardView {
    public static void viewBoard(Board board) {
        System.out.println(board.showBoard());
    }
}
