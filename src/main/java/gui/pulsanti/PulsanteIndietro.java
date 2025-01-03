package gui.pulsanti;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PulsanteIndietro extends JButton {
    public PulsanteIndietro(int height){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/torna_indietro.png")));
        Image newImage = imageIcon.getImage().getScaledInstance(height, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);
        setIconTextGap(0);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
}
