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
        setUndecorated(true);
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
        titoloLabel.setFont(new Font("Arial", Font.BOLD, width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------
        
        //EXIT-------------------------------------------------------------
        JPanel exitPanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(exitPanel);
        exitPanel.setBounds(0,0,b_height,b_height);
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

        //PANEL PULSANTI---------------------------------------------
        JPanel utentiPanel = new JPanel(new GridLayout(1,1));
        JPanel graficiPanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(utentiPanel);
        sfondoLabel.add(graficiPanel);

        utentiPanel.setBounds(width/3-b_width,height/2,b_width*4,b_height);
        utentiPanel.setOpaque(false);

        graficiPanel.setBounds(width/3,height/2+250,b_width*2,b_height);
        graficiPanel.setOpaque(false);
        //PANEL PULSANTI----------------------------------------------

        //PANEL TITOLI--------------------------------------
        JPanel gestioneUtenti = new JPanel(new GridLayout(1,1));
        gestioneUtenti.setBounds(width/3,height/2-50,b_width*3,b_height);
        gestioneUtenti.setOpaque(false);
        JPanel gestioneGrafici = new JPanel(new GridLayout(1,1));
        gestioneGrafici.setBounds(width/3+b_width/2,height/2+200,b_width*3,b_height);
        gestioneGrafici.setOpaque(false);

        sfondoLabel.add(gestioneUtenti);
        sfondoLabel.add(gestioneGrafici);

        JLabel utentiLabel = new JLabel("GESTIONE UTENTI");
        utentiLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        utentiLabel.setFont(new Font("Arial", Font.BOLD, width/40));
        utentiLabel.setForeground(Color.white);

        JLabel graficiLabel = new JLabel("GRAFICI");
        graficiLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        graficiLabel.setFont(new Font("Arial", Font.BOLD, width/40));
        graficiLabel.setForeground(Color.white);

        gestioneUtenti.add(utentiLabel);
        gestioneGrafici.add(graficiLabel);
        //PANEL TITOLI--------------------------------------

        //PULSANTI--------------------------------
        JButton studente = new JButton("STUDENTI");
        studente.setFont(new Font("Arial", Font.BOLD, width/68));
        studente.setBorder(new EtchedBorder());
        studente.setBackground(Color.WHITE);
        studente.setForeground(Color.DARK_GRAY);
        studente.addActionListener(e -> {
            new StudentiFrame(controllore);
            dispose();
        });

        JButton docente = new JButton("DOCENTE");
        docente.setFont(new Font("Arial", Font.BOLD, width/68));
        docente.setBorder(new EtchedBorder());
        docente.setBackground(Color.WHITE);
        docente.setForeground(Color.DARK_GRAY);
        docente.addActionListener(e -> {
            new DocentiFrame(controllore);
            dispose();
        });

        JButton genitore = new JButton("GENITORE");
        genitore.setFont(new Font("Arial", Font.BOLD, width/68));
        genitore.setBorder(new EtchedBorder());
        genitore.setBackground(Color.WHITE);
        genitore.setForeground(Color.DARK_GRAY);
        genitore.addActionListener(e -> {
            new GenitoriFrame(controllore);
            dispose();
        });

        JButton classe = new JButton("CLASSE");
        classe.setFont(new Font("Arial", Font.BOLD, width/68));
        classe.setBorder(new EtchedBorder());
        classe.setBackground(Color.WHITE);
        classe.setForeground(Color.DARK_GRAY);
        classe.addActionListener(e -> {
            new ClassFrame(controllore);
            dispose();
        });

        JButton graficiClassi = new JButton("CLASSI");
        graficiClassi.setFont(new Font("Arial", Font.BOLD, width/68));
        graficiClassi.setBorder(new EtchedBorder());
        graficiClassi.setBackground(Color.WHITE);
        graficiClassi.setForeground(Color.DARK_GRAY);

        graficiClassi.addActionListener(e -> {
            new GraficiClassiFrame();
            dispose();
        });

        JButton graficiStudenti = new JButton("STUDENTI");
        graficiStudenti.setFont(new Font("Arial", Font.BOLD, width/68));
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
        utentiPanel.add(classe);

        graficiPanel.add(graficiStudenti);
        graficiPanel.add(graficiClassi);

        container.add(sfondoPanel);
        //AGGIUNTA PULSANTI AI PANEL-------------

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
