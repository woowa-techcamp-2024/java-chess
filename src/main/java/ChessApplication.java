import chess.ChessView;
import chess.ChessManager;

public class ChessApplication {

    public static void main(String[] args) {
        new ChessManager(new ChessView()).go();
    }
}
