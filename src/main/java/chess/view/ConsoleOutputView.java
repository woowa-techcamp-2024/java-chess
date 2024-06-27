package chess.view;

public class ConsoleOutputView implements GameOutputView{
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.err.println(error);
    }
}
