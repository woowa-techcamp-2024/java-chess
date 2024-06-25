package com.example.demo.context;

public class Game {
    private final Board board;
    private boolean isEnd = false;

    public Game(Board board){
        this.board = board;
    }

    public void start() {
    }

    public void end(){
        isEnd = true;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void printBoard() {
        System.out.println(board);
    }
}
