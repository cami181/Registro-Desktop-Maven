package gui.pulsanti;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PulsanteIndietro extends JButton {

    /**
     * Funzione che crea il pulsante "indietro".
     * Inizializza il pulsante con un'icona personalizzata.
     * L'icona viene caricata, scalata in base all'altezza assegnata e gli attribusice il pulsante.
     *
     * @param height Altezza dell'icona che servirà per ridimensionare l'immagine.
     */
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
