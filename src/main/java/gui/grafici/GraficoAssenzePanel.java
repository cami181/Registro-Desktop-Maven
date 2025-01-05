package gui.grafici;

import javax.swing.*;
import java.awt.*;

public class GraficoAssenzePanel extends JPanel {
    private final int[] assenze;
    private final String[] mesi = {"set", "ott", "nov", "dic", "gen", "feb", "mar", "apr", "mag", "giu"};

    /**
     * Funzione che crea la finestra con il grafico delle assenze in base al mese fornito.
     *
     * @param assenze Array di interi dove, ogni elemento, rappresenta il numero di assenze per mese.
     *                L'array deve contenere 10 valori, uno per ogni mese.
     */
    public GraficoAssenzePanel(int[] assenze) {
        this.assenze = assenze;
    }

    /**
     * Disegna un grafico a barre per rappresentare il numero di assenze per ogni mese.
     *
     * @param g Graphics utilizzato per disegnare il grafico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int width = this.getWidth();
        int height = this.getHeight();
        int margine = 30;
        int larghezzaColonna = (width-margine*2)/10;

        for (int i=0;i<10;i++){
            int altezzaColonna =(int) (((double) assenze[i]/31)*(height-margine*2)); // uso 31 come giorni totali
            int x = margine + i*larghezzaColonna;
            int y = height - margine - altezzaColonna;

            g2D.setColor(new Color(83, 106, 255));

            g2D.fillRect(x,y,larghezzaColonna-5,altezzaColonna);

            g2D.setColor(Color.BLACK);
            g2D.drawString(mesi[i],x+(larghezzaColonna-5)/2,height-5);
        }

        g2D.setColor(Color.BLACK);
        g2D.drawRect(margine,margine,width-margine*2, height-margine*2);
    }
}
