package gui.grafici;

import javax.swing.*;
import java.awt.*;

public class GraficoMedieMateriePanel extends JPanel {
    private final int[] medie_raddoppiate;
    private final String[] materie = {"Ita", "Mat", "Sto", "Inf", "Sis"};

    public GraficoMedieMateriePanel(double[] medie) {
        medie_raddoppiate = new int[medie.length];
        for(int i=0;i<medie.length;i++){
            medie_raddoppiate[i] = (int) (medie[i]*2);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int width = this.getWidth();
        int height = this.getHeight();
        int margine = 30;
        int larghezzaColonna = (width-margine*2)/medie_raddoppiate.length;

        for (int i = 0; i < medie_raddoppiate.length; i++) {
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

        g2D.setColor(Color.BLACK);
        g2D.drawRect(margine, margine, width-2*margine, height-2*margine);
    }
}