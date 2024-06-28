package chess.view.swing;

import javax.swing.*;
import java.awt.*;

public class PieceTileComponent extends JButton {
    private int x, y;
    private final Color backgroundColor;
    private char representation;

    public PieceTileComponent(int tileSize, Color backgroundColor, int x, int y, char representation) {
        setPreferredSize(new Dimension(tileSize, tileSize));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setOpaque(true);
        this.backgroundColor = backgroundColor;
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

    public void selectTile(){
        setBackground(Color.CYAN);
        reDraw();
    }
    public void unSelectTile(){
        setBackground(this.backgroundColor);
        reDraw();
    }

    public char getRepresentation() {
        return representation;
    }

    public void setRepresentation(char representation) {
        this.representation = representation;
        reDraw();
    }

    public void reDraw() {
        this.setText(Character.toString(this.representation));
    }
}
