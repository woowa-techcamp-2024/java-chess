package com.wootecam.chess.io;

import java.util.Scanner;

public class Reader {

    private final Scanner scanner = new Scanner(System.in);

    public String readLine() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
