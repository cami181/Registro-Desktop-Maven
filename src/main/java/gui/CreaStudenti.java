package gui;

import Utenti.Classe;
import Utenti.Studente;
import Controllore.Controllore;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class CreaStudenti extends JFrame {
    private Controllore controllore;
    public CreaStudenti(Controllore controllore) {
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

        b_height = height / 12;
        b_width = width / 9;

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
        homeButton.setFont(CustomFont.getFont((float) width/68));
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

        JLabel titoloLabel = new JLabel("CREA STUDENTE");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(CustomFont.getFont((float) width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------


        // PANEL FORM 1 --------------------------------------
        JPanel formPanel1 = new JPanel();
        formPanel1.setLayout(new BoxLayout(formPanel1, BoxLayout.Y_AXIS));
        formPanel1.setBounds(width/5,height/3+50, width/3, height/3);
        formPanel1.setOpaque(false);

        // NOME
        JPanel nomePanel = new JPanel();
        nomePanel.setLayout(new BoxLayout(nomePanel, BoxLayout.X_AXIS));
        nomePanel.setOpaque(false);
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        nomeLabel.setForeground(Color.WHITE);
        JTextField nomeField = new JTextField();
        nomeField.setPreferredSize(new Dimension(200, 15));
        nomePanel.add(nomeLabel);
        nomePanel.add(Box.createHorizontalStrut(10));  // Spazio tra etichetta e campo
        nomePanel.add(nomeField);

        // COGNOME
        JPanel cognomePanel = new JPanel();
        cognomePanel.setLayout(new BoxLayout(cognomePanel, BoxLayout.X_AXIS));
        cognomePanel.setOpaque(false);
        JLabel cognomeLabel = new JLabel("Cognome:");
        cognomeLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        cognomeLabel.setForeground(Color.WHITE);
        JTextField cognomeField = new JTextField();
        cognomeField.setPreferredSize(new Dimension(200, 15));
        cognomePanel.add(cognomeLabel);
        cognomePanel.add(Box.createHorizontalStrut(10));
        cognomePanel.add(cognomeField);

        //ANNO DI NASCITA------------------------------------
        JPanel dataNascitaPanel = new JPanel();
        dataNascitaPanel.setLayout(new BoxLayout(dataNascitaPanel, BoxLayout.X_AXIS));
        dataNascitaPanel.setOpaque(false);
        JLabel dataNascitaLabel = new JLabel("Data di Nascita:");
        dataNascitaLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        dataNascitaLabel.setForeground(Color.WHITE);

        // ComboBox per i giorni
        JComboBox<String> giornoCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            giornoCombo.addItem(String.format("%02d", i));
        }

        // ComboBox per i mesi
        JComboBox<String> meseCombo = new JComboBox<>();
        String[] mesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        for (String mese : mesi) {
            meseCombo.addItem(mese);
        }

        // ComboBox per gli anni
        JComboBox<String> annoCombo = new JComboBox<>();
        for (int i = 1900; i <= 2024; i++) {
            annoCombo.addItem(String.valueOf(i));
        }

        // Listener per aggiornare i giorni in base a mese e anno selezionati
        ActionListener aggiornaGiorni = e -> {
            int giorniMax = 31;
            int meseSelezionato = meseCombo.getSelectedIndex(); // Ottieni l'indice del mese selezionato (0-based)
            int anno = Integer.parseInt((String) annoCombo.getSelectedItem());
            Calendar cal = Calendar.getInstance();
            cal.set(anno, meseSelezionato, 1);
            giorniMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

            // Rimuovi tutti i giorni precedenti
            giornoCombo.removeAllItems();

            // Aggiungi i giorni per il mese selezionato
            for (int i = 1; i <= giorniMax; i++) {
                giornoCombo.addItem(String.format("%02d", i));
            }
        };

        // Aggiorna i giorni quando cambiano mese o anno
        meseCombo.addActionListener(aggiornaGiorni);
        annoCombo.addActionListener(aggiornaGiorni);

        //label e combo nel panel separate da spazi
        dataNascitaPanel.add(dataNascitaLabel);
        dataNascitaPanel.add(Box.createHorizontalStrut(10));
        dataNascitaPanel.add(giornoCombo);
        dataNascitaPanel.add(Box.createHorizontalStrut(10));
        dataNascitaPanel.add(meseCombo);
        dataNascitaPanel.add(Box.createHorizontalStrut(10));
        dataNascitaPanel.add(annoCombo);

        // Aggiungi i pannelli di input al formPanel
        formPanel1.add(nomePanel);
        formPanel1.add(Box.createVerticalStrut(20));  // Spazio tra i campi
        formPanel1.add(cognomePanel);
        formPanel1.add(Box.createVerticalStrut(20));
        formPanel1.add(dataNascitaPanel);

        //PANEL 2--------------------------------------------------------------
        JPanel formPanel2 = new JPanel();
        formPanel2.setLayout(new BoxLayout(formPanel2, BoxLayout.Y_AXIS));
        formPanel2.setBounds(width*3/5,height/3+68, width/3, height/4);
        formPanel2.setOpaque(false);

        //CODICE FISCALE
        JPanel cfPanel = new JPanel();
        cfPanel.setLayout(new BoxLayout(cfPanel, BoxLayout.X_AXIS));
        cfPanel.setOpaque(false);

        JLabel cfLabel = new JLabel("Codice Fiscale:");
        cfLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        cfLabel.setForeground(Color.WHITE);

        JTextField cfField = new JTextField();
        cfField.setPreferredSize(new Dimension(200, 15));

        cfPanel.add(cfLabel);
        cfPanel.add(Box.createHorizontalStrut(10));  // Spazio tra etichetta e campo
        cfPanel.add(cfField);

        //CLASSE
        JPanel classPanel = new JPanel();
        classPanel.setLayout(new BoxLayout(classPanel, BoxLayout.X_AXIS));
        classPanel.setOpaque(false);

        JLabel classLabel = new JLabel("Classe:");
        classLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        classLabel.setForeground(Color.WHITE);

        JComboBox<String> classCombo = new JComboBox<>();
        //esempio
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(5,"inf", 'B'));
        classi.add(new Classe(5,"inf", 'A'));
        classi.add(new Classe(3,"tur", 'A'));

        classCombo.addItem(" ");
        for (Classe c : classi) {
            classCombo.addItem(c.toString());
        }

        classPanel.add(classLabel);
        classPanel.add(Box.createHorizontalStrut(10));
        classPanel.add(classCombo);

        //aggiunta al panel
        formPanel2.add(cfPanel);
        formPanel2.add(Box.createVerticalStrut(20));
        formPanel2.add(classPanel);

        //PULSANTE CONFERMA----------------------------------------------------------------------
        JPanel confermaPanel = new JPanel();
        confermaPanel.setLayout(new GridLayout(1,1));
        confermaPanel.setOpaque(false);
        confermaPanel.setBounds(width*3/4,height*4/5,b_width,b_height);

        JButton conferma = new JButton("CONFERMA");
        conferma.setFont(CustomFont.getFont((float) height /35));
        conferma.setBorder(new EtchedBorder());
        conferma.setBackground(new Color(189, 255, 136));
        conferma.setForeground(Color.DARK_GRAY);

        //listener x l'invio dello studente
        conferma.addActionListener(e ->{
            //nome e cognome
            if(nomeField.getText().isEmpty() || cognomeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Inserisci nome e cognome");
            }
            else if(Objects.equals(classCombo.getSelectedItem(), " ")){
                JOptionPane.showMessageDialog(null,"Inserire la classe");
            }
            else if(!controllore.checkCodiceFiscale(cfField.getText().trim())){
                JOptionPane.showMessageDialog(null,"Codice Fiscale invalido");
            }
            else{
                // CREA LO STUDENTE Studente studente = new Studente();
                JOptionPane.showMessageDialog(null,":)");
            }
        });

        confermaPanel.add(conferma);

        // Aggiungi i Panel allo sfondo
        sfondoLabel.add(formPanel1);
        sfondoLabel.add(formPanel2);
        sfondoLabel.add(confermaPanel);
        container.add(sfondoPanel);

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
