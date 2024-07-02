package wootecamp.chess.game.client.swing;

import wootecamp.chess.board.Board;
import wootecamp.chess.game.Game;

import javax.swing.*;

public class SwingGameClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);

            Board board = new Board();
            Game game = new Game(board);
            GameFrame gameFrame = new GameFrame(game);
            frame.add(gameFrame);

            frame.setVisible(true);
        });
    }
}