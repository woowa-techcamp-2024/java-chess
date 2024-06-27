package chess.view;

public interface GameView {
    String getOperation();
    void showMessage(String message);
    void showError(String error);
}
