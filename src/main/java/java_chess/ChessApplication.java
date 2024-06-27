package java_chess;

import java_chess.application.ChessGUIView;
import java_chess.chess.Board;
import javax.swing.SwingUtilities;

public class ChessApplication {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var board = new Board();
            var chessGui = new ChessGUIView(board);
            chessGui.printBoard();
        });
    }
}
