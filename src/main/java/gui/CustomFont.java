package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    public static Font getFont(float size){
        Font font = null;
        try {

            File file = new File("/ChunkFivePrint.otf");
            font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException ignore) {}
        return font;
    }
}
