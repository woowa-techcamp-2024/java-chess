package java_chess.application.properties;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class GUIProperties {

    public static final String DEFAULT_FONT = "Arial";
    public static final Color LIGHT_SQUARE_COLOR = Color.white;
    public static final Color DARK_SQUARE_COLOR = Color.gray;
    public static final Font BUTTON_FONT = new Font(DEFAULT_FONT, Font.BOLD, 44);
    public static final Font LABEL_FONT = new Font(DEFAULT_FONT, Font.BOLD, 24);
    public static final LineBorder SELECTED_BORDER = new LineBorder(Color.RED, 3);

    private GUIProperties() {

    }

}
