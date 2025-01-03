package gui.grafici;

import javax.swing.*;
import java.awt.*;

public class GraficoMedieMensiliPanel extends JPanel {
    private final int[] medie_raddoppiate = new int[10];
    private final String[] mesi = {"set", "ott", "nov", "dic", "gen", "feb", "mar", "apr", "mag", "giu"};

    public GraficoMedieMensiliPanel(double[] medie_mensili) {
        for(int i=0;i<10;i++){
            medie_raddoppiate[i] = (int) (medie_mensili[i]*2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int width = this.getWidth();
        int height = this.getHeight();
        int margine = 30;
        int larghezzaColonna = (width-margine*2)/10; //--> mesi scolastici

        for (int i=0;i<10;i++){
            int altezzaColonna =(int) (((double) medie_raddoppiate[i]/20)*(height-margine*2));
            int x = margine + i*larghezzaColonna;
            int y = height - margine - altezzaColonna;

            if(medie_raddoppiate[i]<=9){ //0<voto<=4,5 -> rosso
                g2D.setColor(new Color(196, 0, 0));
            } else if(medie_raddoppiate[i]<12){ //4,5<voto<6 -> arancione
                g2D.setColor(new Color(234, 123, 0));
            }else{ //voto>=6 -> verde
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
