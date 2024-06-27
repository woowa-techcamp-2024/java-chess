package com.woowatechcamp.chess.view;

import com.woowatechcamp.chess.game.ChessGame;
import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ChessGUI extends JFrame {
    private ChessGame game;
    private JPanel chessBoard;
    private JLabel[][] pieceLabels;
    private JLabel draggedPieceLabel;
    private Position dragSource;
    private JPanel glassPane;
    private JLabel whiteScoreLabel;
    private JLabel blackScoreLabel;
    private JLabel timerLabel;
    private Timer gameTimer;
    private int secondsElapsed;
    private JButton startButton;
    private JButton restartButton;
    private static final Map<String, String> PIECE_SYMBOLS = new HashMap<>();

    static {
        PIECE_SYMBOLS.put("WHITE_KING", "♔");
        PIECE_SYMBOLS.put("WHITE_QUEEN", "♕");
        PIECE_SYMBOLS.put("WHITE_ROOK", "♖");
        PIECE_SYMBOLS.put("WHITE_BISHOP", "♗");
        PIECE_SYMBOLS.put("WHITE_KNIGHT", "♘");
        PIECE_SYMBOLS.put("WHITE_PAWN", "♙");
        PIECE_SYMBOLS.put("BLACK_KING", "♚");
        PIECE_SYMBOLS.put("BLACK_QUEEN", "♛");
        PIECE_SYMBOLS.put("BLACK_ROOK", "♜");
        PIECE_SYMBOLS.put("BLACK_BISHOP", "♝");
        PIECE_SYMBOLS.put("BLACK_KNIGHT", "♞");
        PIECE_SYMBOLS.put("BLACK_PAWN", "♟");
    }

    public ChessGUI() {
        game = new ChessGame();
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pieceLabels = new JLabel[8][8];

        JPanel mainPanel = new JPanel(new BorderLayout());

        // 체스판 생성
        chessBoard = new JPanel(new GridLayout(8, 8));
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                pieceLabels[row][col] = new JLabel("", SwingConstants.CENTER);
                pieceLabels[row][col].setFont(new Font("Serif", Font.PLAIN, 40));
                square.add(pieceLabels[row][col], BorderLayout.CENTER);
                square.addMouseListener(new PieceListener(row, col));
                square.addMouseMotionListener(new PieceMotionListener());
                chessBoard.add(square);
            }
        }

        // 점수 및 타이머 패널
        JPanel infoPanel = new JPanel(new FlowLayout());
        whiteScoreLabel = new JLabel("White: 0");
        blackScoreLabel = new JLabel("Black: 0");
        timerLabel = new JLabel("Time: 00:00");
        startButton = new JButton("Start Game");
        restartButton = new JButton("Restart Game");

        startButton.addActionListener(e -> startGame());
        restartButton.addActionListener(e -> restartGame());

        infoPanel.add(whiteScoreLabel);
        infoPanel.add(blackScoreLabel);
        infoPanel.add(timerLabel);
        infoPanel.add(startButton);
        infoPanel.add(restartButton);

        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // 왼쪽 좌표 패널
        JPanel leftCoordinates = new JPanel(new GridLayout(8, 1));
        for (int i = 8; i >= 1; i--) {
            leftCoordinates.add(new JLabel(String.valueOf(i), SwingConstants.CENTER));
        }

        // 아래쪽 좌표 패널
        JPanel bottomCoordinates = new JPanel(new GridLayout(1, 8));
        for (char c = 'a'; c <= 'h'; c++) {
            bottomCoordinates.add(new JLabel(String.valueOf(c), SwingConstants.CENTER));
        }

        // 패널들을 메인 패널에 추가
        mainPanel.add(leftCoordinates, BorderLayout.WEST);
        mainPanel.add(chessBoard, BorderLayout.CENTER);
        mainPanel.add(bottomCoordinates, BorderLayout.SOUTH);

        add(mainPanel);

        glassPane = new JPanel();
        glassPane.setOpaque(false);
        glassPane.setLayout(null);
        setGlassPane(glassPane);

        gameTimer = new Timer(1000, e -> updateTimer());

        updateBoard();
    }

    private void startGame() {
        gameTimer.start();
        startButton.setEnabled(false);
    }

    private void restartGame() {
        game = new ChessGame();
        updateBoard();
        resetTimer();
        startButton.setEnabled(true);
        gameTimer.stop();
    }

    private void resetTimer() {
        secondsElapsed = 0;
        updateTimerLabel();
    }

    private void updateTimer() {
        secondsElapsed++;
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }

    private void updateBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String positionString = String.format("%c%c", (char)('a' + col), (char)('1' + (7 - row)));
                Position position = new Position(positionString);
                Piece piece = game.getPieceAt(position);
                if (piece != null) {
                    String symbolKey = piece.getColor() + "_" + piece.getType();
                    pieceLabels[row][col].setText(PIECE_SYMBOLS.get(symbolKey));
                } else {
                    pieceLabels[row][col].setText("");
                }
            }
        }
        updateScores();
    }

    private void updateScores() {
        double whiteScore = game.calculateScore(Piece.Color.WHITE);
        double blackScore = game.calculateScore(Piece.Color.BLACK);
        whiteScoreLabel.setText(String.format("White: %.1f", whiteScore));
        blackScoreLabel.setText(String.format("Black: %.1f", blackScore));
    }

    private class PieceListener extends MouseAdapter {
        private int row, col;

        public PieceListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            String positionString = String.format("%c%c", (char)('a' + col), (char)('1' + (7 - row)));
            dragSource = new Position(positionString);
            Piece piece = game.getPieceAt(dragSource);
            if (piece != null) {
                draggedPieceLabel = new JLabel(pieceLabels[row][col].getText(), SwingConstants.CENTER);
                draggedPieceLabel.setFont(new Font("Serif", Font.PLAIN, 40));
                draggedPieceLabel.setSize(60, 60);
                draggedPieceLabel.setForeground(piece.getColor() == Piece.Color.WHITE ? Color.BLACK : Color.WHITE);
                glassPane.add(draggedPieceLabel);
                glassPane.setVisible(true);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (draggedPieceLabel != null) {
                Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), chessBoard);
                int targetRow = p.y / (chessBoard.getHeight() / 8);
                int targetCol = p.x / (chessBoard.getWidth() / 8);
                String targetPosString = String.format("%c%c", (char)('a' + targetCol), (char)('1' + (7 - targetRow)));
                Position targetPos = new Position(targetPosString);

                try {
                    game.move(dragSource, targetPos);
                    updateBoard();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ChessGUI.this, ex.getMessage(), "Invalid Move", JOptionPane.ERROR_MESSAGE);
                }

                glassPane.remove(draggedPieceLabel);
                glassPane.setVisible(false);
                draggedPieceLabel = null;
                dragSource = null;
            }
        }
    }

    private class PieceMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (draggedPieceLabel != null) {
                Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), glassPane);
                draggedPieceLabel.setLocation(p.x - draggedPieceLabel.getWidth() / 2, p.y - draggedPieceLabel.getHeight() / 2);
                glassPane.repaint();
            }
        }
    }
}
