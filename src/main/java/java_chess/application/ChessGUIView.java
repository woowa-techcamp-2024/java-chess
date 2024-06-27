package java_chess.application;

import static java_chess.chess.pieces.enums.Color.BLACK;
import static java_chess.chess.pieces.enums.Color.WHITE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.border.LineBorder;

public class ChessGUIView extends JFrame {

    private static final int BOARD_SIZE = 8;
    private static final String DEFAULT_FONT = "Arial";

    private final JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    private final JPanel boardPanel;
    private final JPanel infoPanel;
    private final JLabel turnLabel;
    private final JLabel scoreLabel;
    private final Board board;
    private final ChessGame chessGame;

    private Location selectedLocation = null;

    public ChessGUIView(Board board) {
        this.board = board;
        this.chessGame = new ChessGame(board);
        chessGame.startGame();

        initFrame();

        infoPanel = new JPanel(new GridLayout(2, 1));
        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        turnLabel = new JLabel();
        turnLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, 24));
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font(DEFAULT_FONT, Font.BOLD, 24));

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

    private void initializeBoard() {
        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = generateButton(i, j);
                boardPanel.add(buttons[i][j]);
            }
        }
    }

    private void initializeInfo() {
        infoPanel.add(turnLabel);
        infoPanel.add(scoreLabel);
        updateTurnLabel();
        updateScoreLabel();
    }

    private JButton generateButton(int x, int y) {
        var btn = new JButton();
        btn.setBackground((x + y) % 2 == 0 ? Color.lightGray : Color.getHSBColor(1.25f, 1, .46f));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        var piece = board.getPiece(Location.of(x + 1, (char) ('a' + y)));
        btn.setText(piece.isBlank() ? "" : piece.getSymbol().getValue());
        btn.setFont(new Font(DEFAULT_FONT, Font.BOLD, 44));
        btn.setBorder(new LineBorder(Color.RED, 3));
        btn.addMouseListener(new MouseAdapter() {
                                 @Override
                                 public void mousePressed(MouseEvent e) {
                                     selectedLocation = Location.of(x + 1, (char) ('a' + y));
                                     highlightMoveLocations(selectedLocation);
                                 }

                                 @Override
                                 public void mouseReleased(MouseEvent e) {
                                     var point = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), boardPanel);
                                     var targetRow = Math.abs(
                                         (point.y / (boardPanel.getHeight() / BOARD_SIZE) - BOARD_SIZE)) - 1;
                                     var targetCol = point.x / (boardPanel.getWidth() / BOARD_SIZE);
                                     var endLocation = Location.of(targetRow + 1, (char) ('a' + targetCol));
                                     handlePieceMove(selectedLocation, endLocation);
                                     selectedLocation = null;
                                 }
                             }
        );
        return btn;
    }

    private void updateTurnLabel() {
        turnLabel.setText("Turn: " + chessGame.getTurn());

    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + chessGame.calculateScoreByColor(WHITE) + " : "
            + chessGame.calculateScoreByColor(BLACK));
    }

    private void highlightMoveLocations(Location location) {
        var moveableLocations = board.getLocationsThatPieceCanMoveByLocation(location);
        if (moveableLocations.isEmpty()) {
            selectedLocation = null;
            return;
        }
        resetAllBtn();
        for (var loc : moveableLocations) {
            var btn = buttons[loc.getX() - 1][loc.getY() - 1];
            btn.setBorderPainted(true);
        }
    }

    private void handlePieceMove(Location start, Location end) {
        if (start != null && board.getLocationsThatPieceCanMoveByLocation(start).contains(end)) {
            try {
                chessGame.movePiece(start, end);
                updateBtnText();
                updateTurnLabel();
                updateScoreLabel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Check",
                    JOptionPane.INFORMATION_MESSAGE);
            } finally {
                resetAllBtn();
            }
        }
        resetAllBtn();
    }

    private void updateBtnText() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                var piece = board.getPiece(Location.of(i + 1, (char) ('a' + j)));
                buttons[i][j].setText(piece.isBlank() ? "" : piece.getSymbol().getValue());
            }
        }
    }

    private void resetAllBtn() {
        for (JButton[] jButtons : buttons) {
            for (JButton jButton : jButtons) {
                jButton.setBorderPainted(false);
            }
        }
    }

}
