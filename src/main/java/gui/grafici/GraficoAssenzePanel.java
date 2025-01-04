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

            g2D.setColor(new Color(83, 106, 255));

            g2D.fillRect(x,y,larghezzaColonna-5,altezzaColonna);

            g2D.setColor(Color.BLACK);
            g2D.drawString(mesi[i],x+(larghezzaColonna-5)/2,height-5);
        }

        g2D.setColor(Color.BLACK);
        g2D.drawRect(margine,margine,width-margine*2, height-margine*2);
    }
}
