package wootecamp.chess.game.client.swing;


import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoardPanel extends JPanel {
    private SquareButton[][] squares;
    private Game game;

    public BoardPanel(Game game) {
        this.game = game;
        setLayout(new GridLayout(8, 8));
        squares = new SquareButton[8][8];

        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                squares[y][x] = new SquareButton(y, x, game, this);
                add(squares[y][x]);
            }
        }
    }

    public void updateBoard() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].update();
            }
        }
        revalidate();
        repaint();
    }

    public void highlightPossibleMoves(List<BoardPosition> positions) {
        for (BoardPosition pos : positions) {
            squares[pos.getY()][pos.getX()].setHighlighted(true);
        }
    }

    public void clearHighlights() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setHighlighted(false);
            }
        }
    }
}