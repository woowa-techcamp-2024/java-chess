package wootecamp.chess.game.client.swing;

import wootecamp.chess.game.Game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel {
    private BoardPanel boardPanel;
    private Game game;

    public GameFrame(Game game) {
        this.game = game;
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel(game);
        add(boardPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            try {
                game.start();
                boardPanel.updateBoard();
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}