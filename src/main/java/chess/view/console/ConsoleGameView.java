package chess.view.console;

import chess.view.GameInputView;
import chess.view.GameOutputView;
import chess.view.GameView;

public class ConsoleGameView implements GameView {
    private final GameInputView inputView;
    private final GameOutputView outputView;

    public ConsoleGameView(GameInputView inputView, GameOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
    @Override
    public String getOperation() {
        return inputView.getOperation();
    }
    @Override
    public void showMessage(String message) {
        outputView.showMessage(message);
    }
    @Override
    public void showError(String error) {
        outputView.showError(error);
    }
}
