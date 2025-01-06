package gui.grafici;

import Altro.Voto;
import Controllore.Controllore;
import Utenti.Studente;
import gui.home.HomeFrame;
import gui.pulsanti.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;

public class GraficiStudentiFrame extends JFrame {
    private JPanel graphPanel;

    /**
     * Funzione che crea la finestra con il grafico dei vari studenti.
     *
     * @param controllore Controllore che gestisce: dati studenti e materie.
     */
    public GraficiStudentiFrame(Controllore controllore){
        int width, height, b_height;

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
        JLabel sfondoLabel = new JLabel(icon);
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

        //INDIETRO-----------------------------------------
        JPanel indietroPanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(indietroPanel);
        indietroPanel.setBounds(0,b_height,b_height,b_height);
        indietroPanel.setOpaque(false);

        PulsanteIndietro indietroButton = new PulsanteIndietro(b_height);
        indietroButton.setFont(new Font("Arial", Font.BOLD, width/35));
        indietroButton.setBorder(new EtchedBorder());
        indietroButton.setBackground(Color.WHITE);
        indietroButton.setForeground(Color.DARK_GRAY);
        indietroPanel.add(indietroButton);

        indietroButton.addActionListener(e->{
            new HomeFrame(controllore);
            dispose();
        });

        //EXIT-------------------------------------------------------------
        JPanel exitPanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(exitPanel);
        exitPanel.setBounds(0,b_height*2,b_height,b_height);
        exitPanel.setOpaque(false);

        PulsanteExit exitButton = new PulsanteExit(b_height);
        exitButton.setFont(new Font("Arial", Font.BOLD, width/68));
        exitButton.setBorder(new EtchedBorder());
        exitButton.setBackground(Color.WHITE);
        exitButton.setForeground(Color.DARK_GRAY);
        exitPanel.add(exitButton);

        exitButton.addActionListener(e->{
            dispose();
        });
        //EXIT---------------------------------------------------------

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

        JLabel seleziona = new JLabel("Seleziona lo studente");
        seleziona.setFont(new Font("Arial", Font.BOLD, height/52));
        seleziona.setForeground(Color.WHITE);
        ArrayList<Studente> studenti = controllore.getStudenti();

        JComboBox<String> studentiCombo = new JComboBox<>();
        studentiCombo.addItem("");
        for (Studente s: studenti) {
            studentiCombo.addItem(s.getCF());
        }

        panel2.add(seleziona);
        panel2.add(Box.createVerticalStrut(20));
        panel2.add(studentiCombo);
        sfondoLabel.add(panel2);

        //GRAFICI-------------------------------------------------------------------
        JButton medieMensiliButton = new JButton("MEDIE MENSILI");
        medieMensiliButton.setBackground(Color.WHITE);
        JButton medieMaterieButton = new JButton("MEDIE X MATERIA");
        medieMaterieButton.setBackground(Color.WHITE);
        JButton assenzeButton = new JButton("ASSENZE MENSILI");
        assenzeButton.setBackground(Color.WHITE);

        medieMensiliButton.addActionListener(e ->{
            if(Objects.equals(studentiCombo.getSelectedItem(), "")){
                JOptionPane.showMessageDialog(null,"Seleziona uno studente");
                medieMaterieButton.setBackground(Color.WHITE);
                medieMensiliButton.setBackground(Color.WHITE);
                assenzeButton.setBackground(Color.WHITE);
                sfondoLabel.remove(graphPanel);
                revalidate();
                repaint();
            }
            else{
                medieMensiliButton.setBackground(new Color(255, 109, 109));
                medieMaterieButton.setBackground(Color.WHITE);
                assenzeButton.setBackground(Color.WHITE);
                sfondoLabel.remove(graphPanel);

                //trovo lo studente in base al cf
                Studente tmp = null;
                for (Studente s: controllore.getStudenti()) {
                    if(s.getCF().equals(studentiCombo.getSelectedItem())){
                        tmp = s;
                        break;
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
        });
        panel1.add(medieMensiliButton);

        medieMaterieButton.addActionListener(e ->{
            if(Objects.equals(studentiCombo.getSelectedItem(), "")){
                JOptionPane.showMessageDialog(null,"Seleziona uno studente");
                medieMaterieButton.setBackground(Color.WHITE);
                medieMensiliButton.setBackground(Color.WHITE);
                assenzeButton.setBackground(Color.WHITE);
                sfondoLabel.remove(graphPanel);
                revalidate();
                repaint();
            }
            else{
                medieMaterieButton.setBackground(new Color(255, 109, 109));
                medieMensiliButton.setBackground(Color.WHITE);
                assenzeButton.setBackground(Color.WHITE);
                sfondoLabel.remove(graphPanel);

                //trovo lo studente in base al cf
                Studente tmp = null;
                for (Studente s: controllore.getStudenti()) {
                    if(s.getCF().equals(studentiCombo.getSelectedItem())){
                        tmp = s;
                        break;
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
        });
        panel1.add(medieMaterieButton);

        assenzeButton.addActionListener(e ->{
            if(Objects.equals(studentiCombo.getSelectedItem(), "")){
                JOptionPane.showMessageDialog(null,"Seleziona uno studente");
                medieMaterieButton.setBackground(Color.WHITE);
                medieMensiliButton.setBackground(Color.WHITE);
                assenzeButton.setBackground(Color.WHITE);
                sfondoLabel.remove(graphPanel);
                revalidate();
                repaint();
            }
            else{
                assenzeButton.setBackground(new Color(255, 109, 109));
                medieMaterieButton.setBackground(Color.WHITE);
                medieMensiliButton.setBackground(Color.WHITE);
                sfondoLabel.remove(graphPanel);

                //trovo lo studente in base al cf
                Studente tmp = null;
                for (Studente s: controllore.getStudenti()) {
                    if(s.getCF().equals(studentiCombo.getSelectedItem())){
                        tmp = s;
                        break;
                    }
                }
                //calcolo assenze dei mesi
                int[] assenzeMensili = new int[10];
                //set -- dic
                for(int i=8;i<12;i++){
                    assenzeMensili[i-8] = tmp.getAssenzeMensili(i);
                }
                //gen-- giu
                for(int i=0;i<6;i++){
                    assenzeMensili[i+4] = tmp.getAssenzeMensili(i);
                }
                sfondoLabel.remove(graphPanel);
                graphPanel = new GraficoAssenzePanel(assenzeMensili);
                graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
                graphPanel.setBounds(width*3/5,height/3+68, width/3, height/4);
                sfondoLabel.add(graphPanel);
                revalidate();
                repaint();
            }
        });
        panel1.add(assenzeButton);

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
}
