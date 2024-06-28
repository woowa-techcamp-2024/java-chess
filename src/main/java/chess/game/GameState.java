package chess.game;

public enum GameState {
    READY, PLAYING, BLACK_WIN, WHITE_WIN, DRAW;

    public static GameState getWinner(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return GameState.WHITE_WIN;
        } else if (whiteScore < blackScore) {
            return GameState.BLACK_WIN;
        } else {
            return GameState.DRAW;
        }
    }

    public boolean isEnd() {
        return this == GameState.BLACK_WIN || this == GameState.WHITE_WIN || this == GameState.DRAW;
    }
}
