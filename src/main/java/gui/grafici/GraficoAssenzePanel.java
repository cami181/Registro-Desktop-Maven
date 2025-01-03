package gui.grafici;

import javax.swing.*;
import java.awt.*;

public class GraficoAssenzePanel extends JPanel {
    private final int[] assenze;
    private final String[] mesi = {"set", "ott", "nov", "dic", "gen", "feb", "mar", "apr", "mag", "giu"};

    public GraficoAssenzePanel(int[] assenze) {
        this.assenze = assenze;
    }

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

            if(assenze[i]<=9){ // voto<=4,5 -> rosso
                g2D.setColor(new Color(196, 0, 0));
            } else if(assenze[i]<12){ // 4,5<voto<6 -> arancione
                g2D.setColor(new Color(234, 123, 0));
            }else{ // voto>=6 -> verde
                g2D.setColor(new Color(56, 255, 0));
            }

            g2D.fillRect(x,y,larghezzaColonna-5,altezzaColonna);

            g2D.setColor(Color.BLACK);
            g2D.drawString(mesi[i],x+(larghezzaColonna-5)/2,height-5);
        }

        g2D.setColor(Color.BLACK);
        g2D.drawRect(margine,margine,width-margine*2, height-margine*2);
    }
}
