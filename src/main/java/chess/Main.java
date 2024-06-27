package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
    private static String makeString(String ...str){
        StringBuilder sb = new StringBuilder();

        for(String s : str){
            sb.append(s);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("시작하고 싶으시다면 start를 입력해주세요 : ");
        String line = null;

        while(!"start".equals(line = br.readLine())){
            System.out.println("start를 입력해서 실행해주세요");
        }
        Board board = new Board(new PieceCreatorWithFactory());
        ChessGame game = new ChessGame(board);
        boolean isRunning = true;
        while(isRunning){
            System.out.println("""
            ========================
            명령어를 입력해주세요.
            1. move a1 b2 : 기물을 a1->b2로 이동
            2. end : 게임 종료
            ========================
            """);
            try {
                StringTokenizer opToken = new StringTokenizer(br.readLine());
                String operation = opToken.nextToken();

                switch(operation){
                    case "move" ->{
                        String source = opToken.nextToken();
                        String target = opToken.nextToken();

                        boolean moved = game.move(source, target);
                        if(!moved){
                            System.out.println(makeString(source," -> ",target," 으로 이동하지 못했습니다."));
                        }
                    }
                    case "end" ->{
                        game.finishGame();
                        isRunning = false;
                    }
                }
            }catch(NoSuchElementException nse){
                System.err.println("명령어를 제대로 입력해주세요.");
            }catch(Exception e){
                System.err.println(makeString("[ERROR] ", e.getMessage()));
            }
            game.showBoard();
        }
        br.close();
    }
}
