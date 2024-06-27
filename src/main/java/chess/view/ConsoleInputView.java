package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputView implements GameInputView{
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String getOperation(){
        try{
            return br.readLine();
        }catch(Exception e){
            throw new RuntimeException("입력 오류났어요");
        }
    }
}
