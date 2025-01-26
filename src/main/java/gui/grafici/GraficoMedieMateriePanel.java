package gui.grafici;

import javax.swing.*;
import java.awt.*;

public class GraficoMedieMateriePanel extends JPanel {
    private final int[] medie_raddoppiate = new int[5];
    private final String[] materie = {"Mat", "Ita", "Sto", "Inf", "Sis"};

    /**
     * Funzione che crea un grafico a barre per rappresentare la media dei voti.
     * Nei grafici degli studenti: crea un grafico con le medie di ogni materia relative allo studente selezionato.
     * Nei grafici della classe: crea un grafico con le medie di ogni materia relative alla classe selezionata
     *
     * @param medie Array di 5 valori di tipo double ognuno del quale rappresenta la media di una materia.
     *              L'array deve contenere tutti valori: Matematica, Italiano, Storia, Informatica e Sistemi.
     */
    public GraficoMedieMateriePanel(double[] medie) {
        for(int i=0;i<5;i++){
            medie_raddoppiate[i] = (int) (medie[i]*2);
            if(medie_raddoppiate[i]<0) medie_raddoppiate[i] = 0;
        }
    }

    /**
     * Disegna un grafico a barre per rappresentare la media per ogni materia.
     * Colori utilizzati:
     *                    - Rosso: valori inferiori al 10.
     *                    - Arancione: per valori compresi tra 10 e 12.
     *                    -Verde: per valori superiori a 12.
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
        int larghezzaColonna = (width-margine*2)/5;

        for(int i=0; i<5; i++) {
            int altezzaColonna =(int) (((double) medie_raddoppiate[i]/20)*(height-margine*2));
            int x = margine + i*larghezzaColonna;
            int y = height - margine - altezzaColonna;

            if(medie_raddoppiate[i]<=9){
                g2D.setColor(new Color(196, 0, 0));
            } else if(medie_raddoppiate[i]<12){
                g2D.setColor(new Color(234, 123, 0));
            }else{
                g2D.setColor(new Color(56, 255, 0));
            }

            g2D.fillRect(x, y, larghezzaColonna-5,altezzaColonna);

            g2D.setColor(Color.BLACK);
            g2D.drawString(materie[i], x +(larghezzaColonna-5)/2, height-5);
        }

        g2D.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i <= 10; i++) {
            int alt = height - margine - (i * (height - margine * 2) / 10);
            g2D.drawString(String.valueOf(i), margine - 20, alt + 5);
        }

        g2D.setColor(Color.BLACK);
        g2D.drawRect(margine, margine, width-2*margine, height-2*margine);
    }
}