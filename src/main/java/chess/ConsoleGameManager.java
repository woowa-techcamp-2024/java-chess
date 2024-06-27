package chess;

import chess.view.GameInputView;
import chess.view.GameOutputView;
import chess.view.GameView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleGameManager implements GameManager {
    private final GameView gameView;
    private final ChessGame chessGame;
    public ConsoleGameManager(GameView gameView, ChessGame chessGame){
        this.gameView = gameView;
        this.chessGame = chessGame;
        runningGame();
    }
    private void showGuideMessage(){
        gameView.showMessage("""
                명령어를 입력해주세요.
                1. move a1 b1 : a1 -> b1 으로 이동시킵니다.
                2. end : 게임을 끝냅니다.
                ======================================
                """);
    }
    private void showBoard(){
        gameView.showMessage("=======게임판 현황=======");
        gameView.showMessage(chessGame.showBoard());
        gameView.showMessage("======================");
    }
    @Override
    public void runningGame() {
        gameView.showMessage("게임을 시작하려면 start를 입력해주세요");

        while(true){
            try{
                gameView.showMessage("명령어를 입력해주세요.");
                String operation = gameView.getOperation();
                if("start".equals(operation)){
                    startGame();
                    break;
                }
            }catch(Exception e){
                gameView.showError("입력중 에러가 발생했습니다. 다시 시도해주세요");
            }
        }

        while(true){
            try{
                showGuideMessage();
                showBoard();
                String operation = gameView.getOperation();
                if("end".equals(operation)){
                    gameView.showMessage("게임을 종료합니다.");
                    break;
                }
                action(operation);
            }catch(Exception e){
                gameView.showError("입력중 에러가 발생했습니다. 다시 시도해주세요.");
            }
        }
    }

    @Override
    public void startGame() {
        chessGame.init();
        chessGame.startGame();
        gameView.showMessage("게임을 시작하겠습니다.");
    }

    private String[] seperateString(String operation){
        return operation.split(" ");
    }

    private static String makeString(String ...str){
        StringBuilder sb = new StringBuilder();

        for(String s : str){
            sb.append(s);
        }
        return sb.toString();
    }
    
    @Override
    public void action(String operation) {
        String[] ops = seperateString(operation);
        String op = ops[0];
        try {
            switch (op) {
                case "move" -> {
                    String p1 = ops[1];
                    String p2 = ops[2];
                    if (chessGame.move(p1, p2)) {
                        gameView.showMessage(makeString(p1, " -> ", p2, " 이동을 성공했습니다."));
                    } else {
                        gameView.showError(makeString(p1, " -> ", p2, " 이동을 실패했습니다."));
                    }
                }
            }
        }catch(IndexOutOfBoundsException e){
            gameView.showError("명령어를 제대로 입력해주세요.");
        }catch(Exception e){
            gameView.showMessage(e.getMessage());
        }
    }

    @Override
    public void stopGame() {
        gameView.showMessage("게임을 종료합니다.");
        chessGame.finishGame();
    }
}
