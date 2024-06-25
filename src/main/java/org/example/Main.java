package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a command: ");
            String cmd = scanner.nextLine();

            if(cmd.equals("end")) {
                break;
            }else if (cmd.equals("start")) {
                // 게임 시작
                // 체스판 초기화

            }else{
                System.out.println("Invalid command");
            }
        }
    }
}