package chess.view.swing;

import javax.swing.*;
import java.awt.*;

public class SwingViewComponent extends JFrame {
    private JLabel turnLabel;
    private JPanel buttonPanel;

    public SwingViewComponent() throws HeadlessException {
        setTitle("Chess Game");
        setSize(1000, 1000); // 크기 조정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 최상단 턴 표시 라벨
        turnLabel = new JLabel("Game is not running", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(turnLabel, BorderLayout.NORTH);

        // 최하단 버튼 패널
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    protected void startGame() {
        turnLabel.setText("White's turn");
    }

    protected void endGame() {
        turnLabel.setText("Game Over");
    }

    public void setStartButton(JButton startButton) {
        buttonPanel.add(startButton);
    }

    public void setEndButton(JButton endButton) {
        buttonPanel.add(endButton);
    }

    public void setBoardPanel(JPanel boardPanel) {
        add(boardPanel, BorderLayout.CENTER);
    }

    public void changeTurnLabel(String turnMessage) {
        turnLabel.setText(turnMessage);
    }
}
