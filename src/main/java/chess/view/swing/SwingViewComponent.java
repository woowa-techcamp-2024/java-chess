package chess.view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingViewComponent extends JFrame {
    private JLabel turnLabel;
    private JButton startButton;
    private JButton endButton;
    private JPanel boardPanel;
    private static final int BOARD_SIZE = 8;
    private static final int TILE_SIZE = 100; // 크기 조정
    private final String[][] initialBoard = {
            {"\u265C", "\u265E", "\u265D", "\u265B", "\u265A", "\u265D", "\u265E", "\u265C"},
            {"\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659"},
            {"\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2657", "\u2658", "\u2656"},
    };

    public SwingViewComponent() {
        setTitle("Chess Game");
        setSize(900, 1000); // 크기 조정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 최상단 턴 표시 라벨
        turnLabel = new JLabel("White's Turn", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(turnLabel, BorderLayout.NORTH);

        // 체스판
        boardPanel = new JPanel(new GridLayout(BOARD_SIZE + 2, BOARD_SIZE + 2));
        add(boardPanel, BorderLayout.CENTER);

        // 좌표 표시 및 체스판 타일 생성
        for (int row = 0; row <= BOARD_SIZE+1; row++) {
            for (int col = 0; col <= BOARD_SIZE+1; col++) {
                if ((row == 0 || row == BOARD_SIZE+1) && (col == 0 || col == BOARD_SIZE+1)) {
                    boardPanel.add(new JLabel(""));
                } else if (row == 0 || row == BOARD_SIZE+1) {
                    boardPanel.add(new JLabel(String.valueOf((char) ('A' + col - 1)), SwingConstants.CENTER));
                } else if (col == 0 || col == BOARD_SIZE+1) {
                    boardPanel.add(new JLabel(String.valueOf(BOARD_SIZE - row + 1), SwingConstants.CENTER));
                } else {
                    JPanel tile = new JPanel();
                    tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                    if ((row + col) % 2 == 0) {
                        tile.setBackground(Color.WHITE);
                    } else {
                        tile.setBackground(Color.GRAY);
                    }
                    boardPanel.add(tile);
                }
            }
        }

        // 최하단 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLabel.setText("White's Turn");
            }
        });
        buttonPanel.add(startButton);

        JButton endButton = new JButton("End Game");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLabel.setText("Game Over");
            }
        });
        buttonPanel.add(endButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void initializeBoardWithCoordinates() {
        GridBagConstraints gbc = new GridBagConstraints();
        boolean isWhite = true;

        // 좌측 숫자 라벨
        for (int row = 0; row < 8; row++) {
            gbc.gridx = 0;
            gbc.gridy = row + 1;
            JLabel label = new JLabel(String.valueOf(8 - row), SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            boardPanel.add(label, gbc);
        }

        // 우측 숫자 라벨
        for (int row = 0; row < 8; row++) {
            gbc.gridx = 9;
            gbc.gridy = row + 1;
            JLabel label = new JLabel(String.valueOf(8 - row), SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            boardPanel.add(label, gbc);
        }

        // 상단 문자 라벨
        for (int col = 0; col < 8; col++) {
            gbc.gridx = col + 1;
            gbc.gridy = 0;
            JLabel label = new JLabel(String.valueOf((char) ('a' + col)), SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            boardPanel.add(label, gbc);
        }

        // 하단 문자 라벨
        for (int col = 0; col < 8; col++) {
            gbc.gridx = col + 1;
            gbc.gridy = 9;
            JLabel label = new JLabel(String.valueOf((char) ('a' + col)), SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 24));
            boardPanel.add(label, gbc);
        }

        // 체스판 및 기물 추가
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gbc.gridx = col + 1;
                gbc.gridy = row + 1;
                JPanel square = new JPanel(new BorderLayout());
                if (isWhite) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.GRAY);
                }
                JLabel pieceLabel = new JLabel(initialBoard[row][col], SwingConstants.CENTER);
                pieceLabel.setFont(new Font("Serif", Font.PLAIN, 36));
                square.add(pieceLabel, BorderLayout.CENTER);
                boardPanel.add(square, gbc);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

    private void startGame() {
        turnLabel.setText("White's turn");
        // 게임 시작 로직 추가
    }

    private void endGame() {
        turnLabel.setText("Game Over");
        // 게임 종료 로직 추가
    }
}
