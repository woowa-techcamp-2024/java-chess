package chess.view.swing;

import javax.swing.*;
import java.awt.*;

public class PieceTileComponent extends JButton {
    private int x, y;
    private char representation;

    public PieceTileComponent(int tileSize, Color backgroundColor, int x, int y, char representation) {
        setPreferredSize(new Dimension(tileSize, tileSize));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.x = x;
        this.y = y;
        this.representation = representation;
        setText(Character.toString(representation));
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public String getPosition() {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ('a' + y)).append((char) ('8' - x));
        return sb.toString();
    }

    public char getRepresentation() {
        return representation;
    }

    public void setRepresentation(char representation) {
        this.representation = representation;
    }

    public void reDraw() {
        this.setText(Character.toString(this.representation));
    }
}
