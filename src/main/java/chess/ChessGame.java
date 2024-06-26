package chess;

import chess.pieces.ChessPiece;

import java.util.StringTokenizer;

public class ChessGame {
    private Board board;
    public ChessGame(Board board){
        this.board = board;
    }

    public void init(){
        board.initialize();
    }

    public void showBoard(){
        System.out.println("====================================");
        System.out.println(board.print());
        System.out.println("====================================");
    }

    public void startGame(){
        init();
        System.out.println("게임을 시작하지");
    }

    public boolean operation(String line){
        StringTokenizer st = new StringTokenizer(line);
        String op = st.nextToken();
        boolean isFinish = false;

        switch(op){
            case "start" -> startGame();
            case "move" -> {
                if(st.countTokens() != 2){
                    System.out.println("명령어를 잘못입력했다. `move a1 b1` 형식으로 입력해라.");
                    break;
                }
                String source = st.nextToken();
                String target = st.nextToken();
                board.move(source,target);
                System.out.printf("%s -> %s 이동 완료.\n",source,target);
            }
            case "end" -> isFinish = true;
        }
        return isFinish;
    }

    private void move(String source,String target){
        board.move(source,target);
    }

    public void finishGame(){
        System.out.println("게임을 종료하지");
    }
}
