package java_chess.application;

import static java_chess.application.properties.GUIProperties.BUTTON_FONT;
import static java_chess.application.properties.GUIProperties.DARK_SQUARE_COLOR;
import static java_chess.application.properties.GUIProperties.LABEL_FONT;
import static java_chess.application.properties.GUIProperties.LIGHT_SQUARE_COLOR;
import static java_chess.application.properties.GUIProperties.SELECTED_BORDER;
import static java_chess.chess.pieces.enums.Color.BLACK;
import static java_chess.chess.pieces.enums.Color.WHITE;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java_chess.chess.Board;
import java_chess.chess.pieces.values.Location;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ChessGUIView extends JFrame {

    private static final int BOARD_SIZE = 8;

    private final JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private final JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
    private final JPanel infoPanel = new JPanel(new GridLayout(1, 3));
    private final JLabel turnLabel = new JLabel();
    private final JLabel scoreLabel = new JLabel();

    private final Board board;
    private final ChessGame chessGame;
    private Location selectedLocation = null;

    public ChessGUIView(Board board) {
        this.board = board;
        this.chessGame = new ChessGame(board);
        chessGame.startGame();

        initFrame();
        initComponents();
        initializeBoard();
        initializeInfo();

        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.NORTH);
    }

    public void printBoard() {
        this.setVisible(true);
    }

    private void initFrame() {
        setTitle("Chess Game");
        setSize(800, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        turnLabel.setFont(LABEL_FONT);
        scoreLabel.setFont(LABEL_FONT);
    }

    private void initializeBoard() {
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = createButton(i, j);
                boardPanel.add(buttons[i][j]);
            }
        }
    }

    private void initializeInfo() {
        infoPanel.add(scoreLabel);
        infoPanel.add(turnLabel);
        infoPanel.add(generateResetButton());
        updateTurnLabel();
        updateScoreLabel();
    }

    private JButton createButton(int x, int y) {
        JButton btn = new JButton();
        btn.setBackground((x + y) % 2 == 0 ? DARK_SQUARE_COLOR : LIGHT_SQUARE_COLOR);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFont(BUTTON_FONT);
        btn.setBorder(SELECTED_BORDER);
        updateButtonLabel(btn, x, y);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(x, y);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e);
            }
        });
        return btn;
    }

    private void updateButtonLabel(JButton btn, int x, int y) {
        var piece = board.getPiece(Location.of(x + 1, (char) ('a' + y)));
        btn.setText(piece.isBlank() ? "" : piece.getSymbol().getValue());
    }

    private void updateTurnLabel() {
        turnLabel.setText("Turn: " + chessGame.getTurn().name());
    }

    private void updateScoreLabel() {
        scoreLabel.setText("(W) " + chessGame.calculateScoreByColor(WHITE) + " : "
            + chessGame.calculateScoreByColor(BLACK) + " (B)");
    }

    private void handleMousePress(int x, int y) {
        selectedLocation = Location.of(x + 1, (char) ('a' + y));
        highlightMoveLocations(selectedLocation);
    }

    private void handleMouseRelease(MouseEvent e) {
        var point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), boardPanel);
        var targetRow =
            Math.abs((point.y / (boardPanel.getHeight() / BOARD_SIZE)) - BOARD_SIZE) - 1;
        var targetCol = point.x / (boardPanel.getWidth() / BOARD_SIZE);
        var endLocation = Location.of(targetRow + 1, (char) ('a' + targetCol));
        handlePieceMove(selectedLocation, endLocation);
        selectedLocation = null;
    }

    private void highlightMoveLocations(Location location) {
        if (!board.getPiece(location).verifySameColor(chessGame.getTurn())) {
            return;
        }
        var moveableLocations = board.getLocationsThatPieceCanMoveByLocation(location);
        if (moveableLocations.isEmpty()) {
            selectedLocation = null;
            return;
        }
        resetAllButtonBorders();
        for (var loc : moveableLocations) {
            buttons[loc.getX() - 1][loc.getY() - 1].setBorderPainted(true);
        }
    }

    private void handlePieceMove(Location start, Location end) {
        if (start != null && board.getLocationsThatPieceCanMoveByLocation(start).contains(end)) {
            try {
                chessGame.movePiece(start, end);
                updateAllButtons();
                updateTurnLabel();
                updateScoreLabel();
                chessGame.verifyCheckMate();
            } catch (IllegalArgumentException e) {
                showErrorDialog(e.getMessage());
            } catch (IllegalStateException e) {
                resetAllButtonBorders();
                showEndGameDialog();
            }
        }
        resetAllButtonBorders();
    }

    private JButton generateResetButton() {
        var btn = new JButton("Restart Game");
        btn.addActionListener(e -> {
            chessGame.startGame();
            updateAllButtons();
            updateTurnLabel();
            updateScoreLabel();
        });
        return btn;
    }

    private void updateAllButtons() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                updateButtonLabel(buttons[i][j], i, j);
            }
        }
    }

    private void resetAllButtonBorders() {
        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                btn.setBorderPainted(false);
            }
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Wrong Choice",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showEndGameDialog() {
        var winner = chessGame.getTurn();
        var option = JOptionPane.showOptionDialog(this, "Game Over: " + winner.name() + " win!",
            "Game Over",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Restart"}, "Restart");
        if (option == 0) {
            chessGame.startGame();
            updateAllButtons();
            updateTurnLabel();
            updateScoreLabel();
        } else {
            System.exit(0);
        }
    }

}