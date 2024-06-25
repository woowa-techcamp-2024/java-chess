package com.wootecam.chess.game;

import com.wootecam.chess.Reader;

public class ChessGameManager {

    public static void main(String[] args) {
        Reader reader = new Reader();

        try {
            playGame(reader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            reader.close();
        }
    }

    private static void playGame(Reader reader) {
        ChessGame chessGame = null;
        boolean isFinished = false;

        while (!isFinished) {
            String cmd = reader.readLine();

            switch (cmd) {
                case "start":
                    chessGame = startGame();
                    break;
                case "end":
                    endGame(chessGame);
                    isFinished = true;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command: " + cmd);
            }
        }
    }

    private static ChessGame startGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        return chessGame;
    }

    private static void endGame(ChessGame chessGame) {
        if (chessGame == null) {
            throw new IllegalStateException("You should start chessGame first");
        }
        chessGame.end();
    }
}
