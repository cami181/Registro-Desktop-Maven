package gui.grafici;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;

public class GraficoAssenzePanel extends JPanel {

    /**
     * Funzione che crea la finestra con il grafico a torta di assenze fatte e assenze restanti.
     *
     * @param assenze numero di assenze fatte dallo studente.
     */
    public GraficoAssenzePanel(double assenze) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("assenze", assenze);
        double maxAssenze = 41;
        String titolo = "";
        if(assenze>=maxAssenze){
            dataset.setValue("restanti", 0);
            titolo = "Assenze: " + (int) assenze + "     Restanti: 0";
        }
        else{
            dataset.setValue("restanti", maxAssenze-assenze);
            titolo = "Assenze: " + (int) assenze + "     Restanti: " + (int) (maxAssenze - assenze);
        }

        JFreeChart chart = ChartFactory.createPieChart(titolo, dataset,true,true,false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("assenze", new Color(255, 0, 0));
        plot.setSectionPaint("restanti", new Color(63, 255, 0));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
    }
}
