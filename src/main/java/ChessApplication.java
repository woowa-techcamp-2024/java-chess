import javax.swing.SwingUtilities;
import view.ChessGUI;

public class ChessApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ChessGUI gui = new ChessGUI();
			gui.setVisible(true);
		});
	}
}
