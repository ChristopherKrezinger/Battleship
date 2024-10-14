package Battleship_1_0;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


//Customfont by Qualitype
public class CustomFont {
    private final Font QTMilitary;


    //Constructor
    public CustomFont() {
        try {
            //file path
            InputStream is = CustomFont.class.getResourceAsStream("resources/QTMilitary.otf");
            if (is == null) {
                throw new RuntimeException("Font file not found.");
            }

            QTMilitary = Font.createFont(Font.TRUETYPE_FONT, is);


            //registers font in collection
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(QTMilitary);

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException("Failed to load the custom font.", e);
        }
    }

    /**
     * creates Font in desired size
     * @param size font size
     * @return font in desired size
     */
    public Font getQTMilitary(float size) {
        return QTMilitary.deriveFont(size);
    }
}