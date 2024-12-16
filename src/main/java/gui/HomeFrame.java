package gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Objects;

import Controllore.Controllore;

public class HomeFrame extends JFrame {
    private Controllore controllore;
    public HomeFrame(Controllore controllore) {
        this.controllore = controllore;
        int width, height, b_height, b_width;

        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true); //--> toglie la barra in alto
        setVisible(true);

        Container container = this.getContentPane();

        Dimension screenSize = getSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        b_height = height/12;
        b_width = width/9;

        JPanel sfondoPanel = new JPanel();

        //SFONDO ROSSO------------------------------------------------------------------------------------
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/red.jpg")));
        Image sfondo = icon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        icon = new ImageIcon(sfondo);
        JLabel sfondoLabel = new JLabel(icon);
        sfondoPanel.add(sfondoLabel);
        //SFONDO ROSSO------------------------------------------------------------------------------------

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/4,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("REGISTRO ELETTRONICO");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(CustomFont.getFont((float) width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------

        //PANEL PULSANTI---------------------------------------------
        JPanel utentiPanel = new JPanel(new GridLayout(1,1));
        JPanel graficiPanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(utentiPanel);
        sfondoLabel.add(graficiPanel);

        utentiPanel.setBounds(width/2 - 275,height/2+50,b_width*3,b_height);
        utentiPanel.setOpaque(false);

        graficiPanel.setBounds(width/2 - 175,height*2/3+100,b_width*2,b_height);
        graficiPanel.setOpaque(false);
        //PANEL PULSANTI----------------------------------------------

        //PANEL TITOLI--------------------------------------
        JPanel gestioneUtenti = new JPanel(new GridLayout(1,1));
        gestioneUtenti.setBounds(width/2 - 90,height/2-50,b_width*3,b_height);
        gestioneUtenti.setOpaque(false);
        JPanel gestioneGrafici = new JPanel(new GridLayout(1,1));
        gestioneGrafici.setBounds(width/2 - 50,height/2+200,b_width*3,b_height);
        gestioneGrafici.setOpaque(false);

        sfondoLabel.add(gestioneUtenti);
        sfondoLabel.add(gestioneGrafici);

        JLabel utentiLabel = new JLabel("GESTIONE UTENTI");
        utentiLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        utentiLabel.setFont(CustomFont.getFont((float) width/65));
        utentiLabel.setForeground(Color.white);

        JLabel graficiLabel = new JLabel("GRAFICI");
        graficiLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        graficiLabel.setFont(CustomFont.getFont((float) width/65));
        graficiLabel.setForeground(Color.white);

        gestioneUtenti.add(utentiLabel);
        gestioneGrafici.add(graficiLabel);
        //PANEL TITOLI--------------------------------------

        //PULSANTI--------------------------------
        JButton studente = new JButton("STUDENTI");
        studente.setFont(CustomFont.getFont((float) width/68));
        studente.setBorder(new EtchedBorder());
        studente.setBackground(Color.WHITE);
        studente.setForeground(Color.DARK_GRAY);


        studente.addActionListener(e -> {
            new StudentiFrame(controllore);
            dispose();
        });
        JButton docente = new JButton("DOCENTE");
        docente.setFont(CustomFont.getFont((float) width/68));
        docente.setBorder(new EtchedBorder());
        docente.setBackground(Color.WHITE);
        docente.setForeground(Color.DARK_GRAY);

        docente.addActionListener(e -> {
            new DocentiFrame(controllore);
            dispose();
        });

        JButton genitore = new JButton("GENITORE");
        genitore.setFont(CustomFont.getFont((float) width/68));
        genitore.setBorder(new EtchedBorder());
        genitore.setBackground(Color.WHITE);
        genitore.setForeground(Color.DARK_GRAY);


        genitore.addActionListener(e -> {
            new GenitoriFrame(controllore);
            dispose();
        });

        JButton graficiClassi = new JButton("CLASSI");
        graficiClassi.setFont(CustomFont.getFont((float) width/68));
        graficiClassi.setBorder(new EtchedBorder());
        graficiClassi.setBackground(Color.WHITE);
        graficiClassi.setForeground(Color.DARK_GRAY);

        graficiClassi.addActionListener(e -> {
            new GraficiClassiFrame();
            dispose();
        });

        JButton graficiStudenti = new JButton("STUDENTI");
        graficiStudenti.setFont(CustomFont.getFont((float) width/68));
        graficiStudenti.setBorder(new EtchedBorder());
        graficiStudenti.setBackground(Color.WHITE);
        graficiStudenti.setForeground(Color.DARK_GRAY);

        graficiStudenti.addActionListener(e -> {
            new GraficiStudentiFrame();
            dispose();
        });
        //PULSANTI------------------------------------


        //AGGIUNTA PULSANTI AI PANEL-------------
        utentiPanel.add(studente);
        utentiPanel.add(docente);
        utentiPanel.add(genitore);

        graficiPanel.add(graficiStudenti);
        graficiPanel.add(graficiClassi);

        container.add(sfondoPanel);
        //AGGIUNTA PULSANTI AI PANEL-------------

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
