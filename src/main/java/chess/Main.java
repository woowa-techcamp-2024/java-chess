package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("시작하고 싶으시다면 start를 입력해주세요 : ");
        Board board = new Board();
        ChessGame game = new ChessGame(board);

        while(true){
            String operation = br.readLine();
            boolean isFinish = game.operation(operation);
            if(isFinish){
                game.finishGame();
                break;
            }
            game.showBoard();
        }
        br.close();
    }
}
