package wootecamp.chess.game.client.swing;

import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.game.Game;
import wootecamp.chess.game.State;
import wootecamp.chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SquareButton extends JButton {
    private int y;
    private int x;
    private BoardPosition boardPosition;
    private Game game;
    private BoardPanel boardPanel;
    private boolean isHighlighted = false;

    public SquareButton(int y, int x, Game game, BoardPanel boardPanel) {
        this.y = y;
        this.x = x;
        this.boardPosition = new BoardPosition(x, y);
        this.game = game;
        this.boardPanel = boardPanel;
        setPreferredSize(new Dimension(100, 100));
        setOpaque(true);
        setContentAreaFilled(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        setBorderPainted(false);
        addActionListener(e -> handleClick());
    }

    public void update() {
        Piece piece = game.findPiece(new BoardPosition(x, y));
        setText(String.valueOf(piece.getRepresentation()));
        setBackground(isHighlighted ? Color.YELLOW : getDefaultColor());
    }

    private Color getDefaultColor() {
        return (y + x) % 2 == 0 ? Color.WHITE : Color.GRAY;
    }

    private void handleClick() {
        if (game.getState() == State.STANDBY_PICKING) {
            List<BoardPosition> possibleMoves = game.pick(boardPosition);
            boardPanel.clearHighlights();
            boardPanel.highlightPossibleMoves(possibleMoves);
        } else if (game.getState() == State.STANDBY_MOVING) {
            game.move(new BoardPosition(x, y));
            boardPanel.updateBoard();
            boardPanel.clearHighlights();
        }
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
        update();
    }
}