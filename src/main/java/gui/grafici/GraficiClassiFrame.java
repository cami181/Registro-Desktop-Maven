package gui.grafici;

import Controllore.Controllore;
import Utenti.*;
import gui.home.HomeFrame;
import gui.pulsanti.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;

public class GraficiClassiFrame extends JFrame {
    private String pulsanteScelto = ""; //mese/materia
    private JPanel graphPanel;
    private final JLabel sfondoLabel;
    private final Controllore controllore;
    private final int width, height, b_height;
    private final JButton medieMensiliButton, medieMaterieButton;
    private final JComboBox<String> classiCombo;

    /**
     * Funzione che crea la finestra con il grafico delle varie classi.
     *
     * @param controllore Controllore che gestisce: classi e dati studenti.
     */
    public GraficiClassiFrame(Controllore controllore){
        this.controllore = new Controllore();

        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);

        Container container = this.getContentPane();

        Dimension screenSize = getSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        b_height = height / 12;

        JPanel sfondoPanel = new JPanel();

        // SFONDO ROSSO------------------------------------------------------------------------------------
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/red.jpg")));
        Image sfondo = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sfondo);
        sfondoLabel = new JLabel(icon);
        sfondoPanel.add(sfondoLabel);
        // SFONDO ROSSO------------------------------------------------------------------------------------

        //HOME--------------------------------------------------------
        JPanel homePanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(homePanel);
        homePanel.setBounds(0,0,b_height,b_height);
        homePanel.setOpaque(false);

        PulsanteHome homeButton = new PulsanteHome(b_height);
        homeButton.setFont(new Font("Arial", Font.BOLD, width/35));
        homeButton.setBorder(new EtchedBorder());
        homeButton.setBackground(Color.WHITE);
        homeButton.setForeground(Color.DARK_GRAY);
        homePanel.add(homeButton);

        homeButton.addActionListener(e->{
            new HomeFrame(controllore);
            dispose();
        });
        //HOME--------------------------------------------------------

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/6,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("GRAFICI STUDENTI");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------

        // PANEL 1 --------------------------------------
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,1));
        panel1.setBounds(width/5,height/3+50, width/6, b_height*3+20);
        panel1.setOpaque(false);

        //PANEL 2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        panel2.setBounds(width*2/5,height*2/5, width/6, b_height*3/2);
        panel2.setOpaque(false);

        JLabel seleziona = new JLabel("Seleziona la classe");
        seleziona.setFont(new Font("Arial", Font.BOLD, height/39));
        seleziona.setForeground(Color.WHITE);
        ArrayList<Classe> classi = controllore.getClassi();
        classiCombo = new JComboBox<>();
        classiCombo.addItem(" ");
        for (Classe c: classi) {
            classiCombo.addItem(c.toString());
        }
        classiCombo.addActionListener(e->{
            if(Objects.equals(classiCombo.getSelectedItem(), " ")){
                sfondoLabel.remove(graphPanel);
                revalidate();
                repaint();
            }
            else{
                if(pulsanteScelto.equals("mese")){
                    paintGraficoMesi();
                }
                else if(pulsanteScelto.equals("materia")){
                    paintGraficoMaterie();
                }
            }
        });

        panel2.add(seleziona);
        panel2.add(Box.createVerticalStrut(20));
        panel2.add(classiCombo);
        sfondoLabel.add(panel2);

        //GRAFICI-------------------------------------------------------------------
        medieMensiliButton = new JButton("MEDIE MENSILI");
        medieMensiliButton.setBackground(Color.WHITE);
        medieMaterieButton = new JButton("MEDIE PER MATERIA");
        medieMaterieButton.setBackground(Color.WHITE);

        medieMensiliButton.addActionListener(e ->{
            pulsanteScelto = "mese";
            medieMensiliButton.setBackground(new Color(243, 118, 118));
            medieMaterieButton.setBackground(Color.WHITE);
            if(Objects.equals(classiCombo.getSelectedItem(), " ")){
                sfondoLabel.remove(graphPanel);
                revalidate();
                repaint();
            }
            else{
                paintGraficoMesi();
            }
        });
        panel1.add(medieMensiliButton);

        medieMaterieButton.addActionListener(e ->{
            pulsanteScelto = "materia";
            medieMaterieButton.setBackground(new Color(243, 118, 118));
            medieMensiliButton.setBackground(Color.WHITE);
            if(Objects.equals(classiCombo.getSelectedItem(), " ")){
                sfondoLabel.remove(graphPanel);
                revalidate();
                repaint();
            }
            else{
                paintGraficoMaterie();
            }
        });
        panel1.add(medieMaterieButton);

        //PANEL GRAFICI--------------------------------------------------------------
        graphPanel = new JPanel();
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        graphPanel.setBounds(width*3/5,height/3+68, width/3, height/4);
        graphPanel.setOpaque(false);

        sfondoLabel.add(panel1);
        sfondoLabel.add(graphPanel);
        container.add(sfondoPanel);

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Funzione che mostra il grafico delle medie per ogni mese
     */
    private void paintGraficoMesi(){
        medieMensiliButton.setBackground(new Color(255, 109, 109));
        medieMaterieButton.setBackground(Color.WHITE);
        sfondoLabel.remove(graphPanel);

        String classe = classiCombo.getSelectedItem().toString().toLowerCase();
        //trovo la classe
        Classe tmp = null;
        for (Classe c: controllore.getClassi()) {
            if(c.toString().equals(Objects.requireNonNull(classe))){
                tmp = c;
                break;
            }
        }
        for (Studente s: controllore.getStudenti()) {
            if(s.getClasse().toString().equals(classe)){
                tmp.getStudenti().add(s);
            }
        }
        //calcolo medie dei mesi
        double[] medieMensili = new double[10];
        //set -- dic
        for(int i=8;i<12;i++){
            medieMensili[i-8] = tmp.getMediaMensile(i);
        }
        //gen-- giu
        for(int i=0;i<6;i++){
            medieMensili[i+4] = tmp.getMediaMensile(i);
        }
        //grafico
        graphPanel = new GraficoMedieMensiliPanel(medieMensili);
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        graphPanel.setBounds(width*3/5,height/3+68, width/3, height/4);
        sfondoLabel.add(graphPanel);
        revalidate();
        repaint();
    }

    /**
     * Funzione che mostra il grafico delle medie per materia
     */
    private void paintGraficoMaterie(){
        medieMaterieButton.setBackground(new Color(255, 109, 109));
        medieMensiliButton.setBackground(Color.WHITE);
        sfondoLabel.remove(graphPanel);

        String classe = classiCombo.getSelectedItem().toString().toLowerCase();

        Classe tmp = null;
        //trovo la classe
        for (Classe c: controllore.getClassi()) {
            if(c.toString().equals(Objects.requireNonNull(classe.toLowerCase()))){
                tmp = c;
                break;
            }
        }
        for (Studente s: controllore.getStudenti()) {
            if(s.getClasse().toString().equals(classe)){
                tmp.getStudenti().add(s);
            }
        }

        //medie x materia
        double[] medieMaterie = new double[5];
        int i=0;
        for (String s: controllore.getMaterie()){
            medieMaterie[i] = tmp.getMediaMateria(s);
            i++;
        }

        //grafico
        sfondoLabel.remove(graphPanel);
        graphPanel = new GraficoMedieMateriePanel(medieMaterie);
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        graphPanel.setBounds(width*3/5,height/3+68, width/3, height/4);
        sfondoLabel.add(graphPanel);
        revalidate();
        repaint();
    }
}
