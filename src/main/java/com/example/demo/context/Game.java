package com.example.demo.context;

import com.example.demo.piece.Piece;
import com.example.demo.piece.Type;

import static com.example.demo.context.Board.Location;

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

    /**
     * from에서 to로 체스의 말을 이동하도록 명령합니다.
     * @param from 이동할 말이 위치한 좌표
     * @param to 이동할 목표 좌표
     * @throws RuntimeException 이동할 수 없는 경우
     */
    public void move(Location from, Location to) {

        Piece piece = board.getPiece(from.rank(), from.file());
        Type type = piece.getType();

        if(!accept(type, from, to)){
            throw new RuntimeException("이동할 수 없습니다.");
        }

        board.setPiece(from, null);
        board.setPiece(to, piece);
    }

    public boolean accept(Type type, Location from, Location to){
        return true;
    }
}
