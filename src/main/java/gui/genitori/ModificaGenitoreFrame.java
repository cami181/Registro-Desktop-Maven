package gui.genitori;

import Credenziali.Credenziali;
import Utenti.*;
import Controllore.Controllore;
import gui.home.HomeFrame;
import gui.pulsanti.PulsanteExit;
import gui.pulsanti.PulsanteHome;
import gui.pulsanti.PulsanteIndietro;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
public class ModificaGenitoreFrame extends JFrame {
    Date data = new GregorianCalendar(2002, Calendar.DECEMBER,20).getTime(); //PROVA

    /**
     * Funzione che crea la finestra per modificare i genitori.
     *
     * @param controllore Controllore che gestisce la logica.
     * @param genitore Genitore che si deve modificare.
     */
    public ModificaGenitoreFrame(Controllore controllore, Genitore genitore) {
        controllore.eliminaGenitore(genitore);
        Genitore tmp = genitore;

        int width, height, b_height, b_width;

        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
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
        homeButton.setFont(new Font("Arial", Font.BOLD, width/35));
        homeButton.setBorder(new EtchedBorder());
        homeButton.setBackground(Color.WHITE);
        homeButton.setForeground(Color.DARK_GRAY);
        homePanel.add(homeButton);

        homeButton.addActionListener(e->{
            controllore.registraGenitore(tmp);
            JOptionPane.showMessageDialog(null,"genitore salvato come prima");
            new HomeFrame(controllore);
            dispose();
        });
        //HOME--------------------------------------------------------

        //CREDENZIALI--------------------------------------
        JPanel credPanel = new JPanel();
        sfondoLabel.add(credPanel);
        credPanel.setBounds(0,b_height,b_height,b_height);
        credPanel.setOpaque(false);

        //CREDENZIALI--------------------------------------

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
            controllore.registraGenitore(tmp);
            JOptionPane.showMessageDialog(null,"genitore salvato come prima");
            new GenitoriFrame(controllore);
            dispose();
        });
        //INDIETRO---------------------------------------------

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/6,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("MODIFICA GENITORE");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, width/20));

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
        nomeField.setText(genitore.getNome());
        nomePanel.add(nomeLabel);
        nomePanel.add(Box.createHorizontalStrut(10));
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
        cognomeField.setText(genitore.getCognome());
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

        Calendar calendar = Calendar. getInstance();
        calendar.setTime(genitore.getDataDiNascita());

        // ComboBox per i giorni
        JComboBox<String> giornoCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            giornoCombo.addItem(String.format("%02d", i));
        }
        giornoCombo.setSelectedIndex(calendar.get(Calendar.DAY_OF_MONTH)-1);

        // ComboBox per i mesi
        JComboBox<String> meseCombo = new JComboBox<>();
        String[] mesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        for (String mese : mesi) {
            meseCombo.addItem(mese);
        }
        meseCombo.setSelectedIndex(calendar.get(Calendar.MONTH));

        // ComboBox per gli anni
        JComboBox<String> annoCombo = new JComboBox<>();
        for (int i = 1900; i <= 2024; i++) {
            annoCombo.addItem(String.valueOf(i));
        }
        annoCombo.setSelectedIndex(calendar.get(Calendar.YEAR)-1900);

        // Listener per aggiornare i giorni in base a mese e anno
        ActionListener aggiornaGiorni = e -> {
            int giorniMax = 31;
            int meseSelezionato = meseCombo.getSelectedIndex();
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
        dataNascitaPanel.add(annoCombo);
        dataNascitaPanel.add(Box.createHorizontalStrut(10));
        dataNascitaPanel.add(meseCombo);
        dataNascitaPanel.add(Box.createHorizontalStrut(10));
        dataNascitaPanel.add(giornoCombo);

        // Aggiungi i panel di input al formPanel
        formPanel1.add(nomePanel);
        formPanel1.add(Box.createVerticalStrut(60));
        formPanel1.add(cognomePanel);
        formPanel1.add(Box.createVerticalStrut(60));
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
        cfField.setText(genitore.getCF());

        cfPanel.add(cfLabel);
        cfPanel.add(Box.createHorizontalStrut(10));
        cfPanel.add(cfField);

        //FIGLIO
        JPanel figlioPanel = new JPanel();
        figlioPanel.setLayout(new BoxLayout(figlioPanel, BoxLayout.X_AXIS));
        figlioPanel.setOpaque(false);

        JLabel classLabel = new JLabel("CF del figlio:");
        classLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        classLabel.setForeground(Color.WHITE);

        JComboBox<String> studentiCombo = new JComboBox<>();

        ArrayList<Studente> studenti = controllore.getStudenti();

        studentiCombo.addItem(" ");
        for (Studente s : studenti) {
            studentiCombo.addItem(s.getCF());
            try{
                if(s.getCF().equals(genitore.getFiglio().getCF())){
                    studentiCombo.setSelectedItem(s);
                }
            }catch (NullPointerException e){
                studentiCombo.setSelectedItem(" ");
            }
        }
        if(Objects.requireNonNull(studentiCombo.getSelectedItem()).toString().isEmpty()){
            JOptionPane.showMessageDialog(null,"Account del figlio non associato");
        }

        figlioPanel.add(classLabel);
        figlioPanel.add(Box.createHorizontalStrut(10));
        figlioPanel.add(studentiCombo);

        //aggiunta al panel
        formPanel2.add(cfPanel);
        formPanel2.add(Box.createVerticalStrut(80));
        formPanel2.add(figlioPanel);

        //PULSANTE CONFERMA----------------------------------------------------------------------
        JPanel confermaPanel = new JPanel();
        confermaPanel.setLayout(new GridLayout(1,1));
        confermaPanel.setOpaque(false);
        confermaPanel.setBounds(width*3/4,height*4/5,b_width,b_height);

        JButton conferma = new JButton("CONFERMA");
        conferma.setFont(new Font("Arial", Font.BOLD, width/68));
        conferma.setBorder(new EtchedBorder());
        conferma.setBackground(new Color(189, 255, 136));
        conferma.setForeground(Color.DARK_GRAY);

        //listener x l'invio dello studente
        conferma.addActionListener(e ->{
            //nome e cognome
            if(nomeField.getText().isEmpty() || cognomeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Compila tutti i campi");
            }
            else if(Objects.equals(studentiCombo.getSelectedItem(), " ")){
                JOptionPane.showMessageDialog(null,"Selezionare il CF del proprio figlio");
            }
            else if(controllore.codiceFiscaleInvalido(cfField.getText().trim())){
                JOptionPane.showMessageDialog(null,"Codice Fiscale invalido");
            }
            else if(controllore.alreadyExistentCf(cfField.getText())){
                JOptionPane.showMessageDialog(null,"Codice Fiscale già esistente");
            }
            else{
                //data di nascita
                int anno = Integer.parseInt(Objects.requireNonNull(annoCombo.getSelectedItem()).toString());
                int mese = meseCombo.getSelectedIndex();
                int giorno = Integer.parseInt(Objects.requireNonNull(giornoCombo.getSelectedItem()).toString());
                Date data = new GregorianCalendar(anno,mese,giorno).getTime();

                Studente figlio = null;
                for (Studente s: studenti) {
                    if(Objects.requireNonNull(studentiCombo.getSelectedItem()).toString().equals(s.getCF())){
                        figlio = s;
                    }
                }
                genitore.setNome(nomeField.getText());
                genitore.setCognome(cognomeField.getText());
                genitore.setDataDiNascita(data);
                genitore.setCF(cfField.getText());
                genitore.setFiglio(figlio);
                String user = "g" + genitore.getNome() + "." + genitore.getCognome();
                String password = "";
                for(int i=0;i<user.length();i++) {
                    if (user.charAt(i) != 'a' && user.charAt(i) != 'e' && user.charAt(i) != 'i' && user.charAt(i) != 'o' && user.charAt(i) != 'u' && user.charAt(i) != '.') {
                        password += user.charAt(i);
                    }
                }
                genitore.setCredenziali(new Credenziali(user,password));
                controllore.registraGenitore(genitore);
                JOptionPane.showMessageDialog(null,"Genitore modificato con successo");
                new GenitoriFrame(controllore);
                dispose();
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