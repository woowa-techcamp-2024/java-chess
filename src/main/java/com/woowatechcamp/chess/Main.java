package com.woowatechcamp.chess;

import com.woowatechcamp.chess.view.ChessGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessGUI().setVisible(true));
    }
}
