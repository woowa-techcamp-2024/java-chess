package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.chess.board.Board;
import org.example.chess.board.BoardInitializeManager;
import org.example.chess.board.BoardMoveManager;
import org.example.chess.board.BoardScoreManager;
import org.example.chess.board.BoardView;
import org.example.chess.pieces.Piece;

public class GameGUI extends JFrame {
    private static BoardMoveManager boardMoveManager;
    private static BoardInitializeManager boardInitializeManager;
    private static BoardScoreManager boardScoreManager;
    private static BoardView boardView;
    private static Board board;
    private JPanel boardPanel;
    private JTextArea console;
    private JButton[][] boardButtons;
    private JLabel turnLabel;
    private String source;
    private JButton selectedButton;

    public GameGUI() {
        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(8, 8));
        boardButtons = new JButton[8][8];

        JScrollPane boardScrollPane = new JScrollPane(boardPanel);
        boardScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        boardScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(boardScrollPane, BorderLayout.CENTER);

        console = new JTextArea();
        console.setEditable(false);
        JScrollPane consoleScrollPane = new JScrollPane(console);
        consoleScrollPane.setPreferredSize(new Dimension(800, 200));
        add(consoleScrollPane, BorderLayout.SOUTH);

        turnLabel = new JLabel("Current Turn: WHITE");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel, BorderLayout.NORTH);

        initializeGame();
        drawBoard();
    }

    private void initializeGame() {
        board = new Board();
        boardInitializeManager = new BoardInitializeManager(board);
        boardMoveManager = new BoardMoveManager(board);
        boardScoreManager = new BoardScoreManager(board);
        boardView = new BoardView(board);
        boardInitializeManager.initialize();
    }

    private void drawBoard() {
        boardPanel.removeAll();
        char[][] boardRepresentation = boardView.getBoardRepresentation();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = new JButton(String.valueOf(boardRepresentation[i][j]));
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setVerticalAlignment(SwingConstants.CENTER);
                button.setOpaque(true);
                button.setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                button.addActionListener(new MoveActionListener(i, j));
                boardButtons[i][j] = button;
                boardPanel.add(button);
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private class MoveActionListener implements ActionListener {
        private int row;
        private int col;

        public MoveActionListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (source == null) {
                source = getPosition(row, col);
                selectedButton = button;
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                console.append("Source selected: " + source + "\n");
            } else {
                String destination = getPosition(row, col);
                handleMoveCommand(source, destination);
                source = null;
                if (selectedButton != null) {
                    selectedButton.setBorder(null);
                }
                selectedButton = null;
            }
        }

        private String getPosition(int row, int col) {
            char column = (char) ('a' + col);
            int r = 8 - row;
            return String.valueOf(column) + r;
        }
    }

    private void handleMoveCommand(String source, String destination) {
        try {
            boardMoveManager.move(source, destination);
            drawBoard();
            console.append("Moved from " + source + " to " + destination + "\n");
            updateTurnLabel();
        } catch (IllegalArgumentException ex) {
            console.append(ex.getMessage() + "\n");
        }
    }

    private void updateTurnLabel() {
        String currentTurn = boardMoveManager.getCurrentTurn() == Piece.Color.WHITE ? "WHITE" : "BLACK";
        turnLabel.setText("Current Turn: " + currentTurn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI game = new GameGUI();
            game.setVisible(true);
        });
    }
}
