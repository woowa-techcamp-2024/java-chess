package chess;

import java.util.Scanner;

public class BoardGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String operation = sc.nextLine();

        if("start".equals(operation)){
            Board board = new Board();
            board.initialize();
            System.out.println("체스 게임을 시작합니다.");
            while(!"end".equals(operation)){
                System.out.println("=======체스판 현황=======");
                System.out.println(board.print());
                System.out.println("======================");
                System.out.print("명령어를 입력해주세요 : ");
                operation = sc.nextLine();
                //추후 operation 분기문 추가
            }
            System.out.println("체스 게임을 종료합니다.");
        }
        else{
            System.out.println("체스 게임을 종료합니다.");
        }
    }
}
