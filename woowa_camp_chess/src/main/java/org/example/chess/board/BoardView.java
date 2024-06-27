package org.example.chess.board;

public abstract class BoardView {
    public static void viewBoard(Board board,PointManager pointManager) {
        System.out.println(board.showBoard());
        System.out.println(pointManager.showPoint());
    }
}
