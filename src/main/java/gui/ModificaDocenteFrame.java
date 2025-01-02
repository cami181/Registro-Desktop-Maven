package gui;

import Controllore.Controllore;
import Utenti.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

public class ModificaDocenteFrame extends JFrame {
    private Controllore controllore;
    private ArrayList<Classe> classiDocente;
    private ArrayList<String> materieDocente;
    public ModificaDocenteFrame(Controllore controllore, Docente docente) {
        this.controllore = controllore;
        int width, height, b_height, b_width;
        Docente tmp = docente;

        classiDocente = docente.getClassi();
        materieDocente = docente.getMaterie();

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
            controllore.registraDocente(tmp);
            JOptionPane.showMessageDialog(null,"docente salvato come prima");
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
            controllore.registraDocente(tmp);
            JOptionPane.showMessageDialog(null,"docente salvato come prima");
            new DocentiFrame(controllore);
            dispose();
        });
        //INDIETRO-------------------------------------------

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
            controllore.registraDocente(tmp);
            JOptionPane.showMessageDialog(null,"docente salvato come prima");
            dispose();
        });
        //EXIT---------------------------------------------------------

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/6,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("MODIFICA DOCENTE");
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
        nomeField.setText(docente.getNome());
        nomePanel.add(nomeLabel);
        nomePanel.add(Box.createHorizontalStrut(10));
        nomePanel.add(nomeField);

        //CODICE FISCALE PANEL
        JPanel cfpanel = new JPanel();
        cfpanel.setLayout(new BoxLayout(cfpanel, BoxLayout.X_AXIS));
        cfpanel.setOpaque(false);
        JLabel cfLabel = new JLabel("Codice Fiscale:");
        cfLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        cfLabel.setForeground(Color.WHITE);
        JTextField cfField = new JTextField();
        cfField.setPreferredSize(new Dimension(200, 15));
        cfField.setText(docente.getCF());
        cfpanel.add(cfLabel);
        cfpanel.add(Box.createHorizontalStrut(10));
        cfpanel.add(cfField);

        // COGNOME
        JPanel cognomePanel = new JPanel();
        cognomePanel.setLayout(new BoxLayout(cognomePanel, BoxLayout.X_AXIS));
        cognomePanel.setOpaque(false);
        JLabel cognomeLabel = new JLabel("Cognome:");
        cognomeLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        cognomeLabel.setForeground(Color.WHITE);
        JTextField cognomeField = new JTextField();
        cognomeField.setPreferredSize(new Dimension(200, 15));
        cognomeField.setText(docente.getCognome());
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
        calendar.setTime(docente.getDataDiNascita());

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
        formPanel1.add(Box.createVerticalStrut(50));  // Spazio tra i campi
        formPanel1.add(cognomePanel);
        formPanel1.add(Box.createVerticalStrut(50));
        formPanel1.add(cfpanel);
        formPanel1.add(Box.createVerticalStrut(50));
        formPanel1.add(dataNascitaPanel);

        //"MATERIE"
        JPanel titoloMaterie = new JPanel();
        titoloMaterie.setBounds(width*3/5,height/4, width/4, height/12);
        titoloMaterie.setOpaque(false);
        JLabel tm = new JLabel("Gestione materie");
        tm.setFont(new Font("Arial",Font.BOLD,width/42));
        tm.setForeground(Color.WHITE);
        titoloMaterie.add(tm);

        //MATERIE DEL DOCENTE PANEL-------------------------------------------
        JPanel materiePanel = new JPanel();
        materiePanel.setLayout(new GridLayout(2,2));
        materiePanel.setBounds(width*3/5,height/3, width/4, height/6);
        materiePanel.setOpaque(false);

        //AGGIUNGI
        JComboBox<String> materieBox = new JComboBox<>();
        materieBox.setOpaque(false);
        //PRENDI TUTTE LE MATERIE ESISTENTI
        ArrayList<String> materie = new ArrayList<>();
        materie.add("Matematica");
        materie.add("Italiano");
        materie.add("Storia");
        materie.add("Informatica");
        //togli quelle del docente
        materie.removeIf(s -> materieDocente.contains(s));

        materieBox.addItem("");
        for (String m: materie) {
            materieBox.addItem(m);
        }

        //RIMUOVI
        JComboBox<String> profMaterieBox = new JComboBox<>();
        profMaterieBox.setOpaque(false);
        //PRENDI LE MATERIE DEL DOCENTE
        profMaterieBox.addItem("");
        for (String m: materieDocente) {
            profMaterieBox.addItem(m);
        }

        JButton rimuovi2 = new JButton("RIMUOVI");
        rimuovi2.addActionListener(e ->{
            if(Objects.requireNonNull(profMaterieBox.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                try{
                    for (String s: materieDocente) {
                        if(s.equals(Objects.requireNonNull(profMaterieBox.getSelectedItem()).toString())){
                            materieDocente.remove(s);
                            materie.add(s);
                            materieBox.addItem(s);
                            profMaterieBox.removeItem(s);
                        }
                    }
                }catch(ConcurrentModificationException ignore){}
            }
        });

        JButton aggiungi2 = new JButton("AGGIUNGI");
        aggiungi2.addActionListener(e ->{
            if(Objects.requireNonNull(materieBox.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                try{
                    for (String m: materie) {
                        if(m.equals(Objects.requireNonNull(materieBox.getSelectedItem()).toString())){
                            materieDocente.add(m);
                            materie.remove(m);
                            profMaterieBox.addItem(m);
                            materieBox.removeItem(m);
                        }
                    }
                }catch (ConcurrentModificationException ignore){}
            }
        });

        materiePanel.add(materieBox);
        materiePanel.add(aggiungi2);
        materiePanel.add(profMaterieBox);
        materiePanel.add(rimuovi2);

        //"CLASSI"
        JPanel titoloClassi = new JPanel();
        titoloClassi.setBounds(width*3/5,height/2, width/4, height/12);
        titoloClassi.setOpaque(false);
        JLabel tc = new JLabel("Gestione classi");
        tc.setFont(new Font("Arial",Font.BOLD,width/42));
        tc.setForeground(Color.WHITE);
        titoloClassi.add(tc);

        //CLASSI DEL DOCENTE PANEL----------------------------------------------------
        JPanel classPanel = new JPanel();
        classPanel.setLayout(new GridLayout(2,2));
        classPanel.setBounds(width*3/5,height*7/12, width/4, height/6);
        //classPanel.setOpaque(false);

        //AGGIUNGI
        JComboBox<String> allClassesBox = new JComboBox<>();
        allClassesBox.setOpaque(false);
        //PRENDI TUTTE LE CLASSI ESISTENTI
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(5,"inf",'b'));
        classi.add(new Classe(4,"inf",'b'));
        //togli quelle del docente
        for(Classe c:classiDocente) {
            classi.removeIf(c2 -> c2.toString().equals(c.toString()));
        }
        classi.removeIf(s -> classiDocente.contains(s));

        allClassesBox.addItem("");
        for (Classe c: classi) {
            allClassesBox.addItem(c.toString());
        }

        //RIMUOVI
        JComboBox<String> profClassesBox = new JComboBox<>();
        profClassesBox.setOpaque(false);
        //PRENDI LE CLASSI DEL DOCENTE
        profClassesBox.addItem("");
        for (Classe c: classiDocente) {
            profClassesBox.addItem(c.toString());
        }

        JButton rimuovi = new JButton("RIMUOVI");
        rimuovi.addActionListener(e ->{
            if(Objects.requireNonNull(profClassesBox.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                try{
                    for (Classe c: classiDocente) {
                        if(c.toString().equals(Objects.requireNonNull(profClassesBox.getSelectedItem()).toString())){
                            classi.add(c);
                            classiDocente.remove(c);
                            allClassesBox.addItem(c.toString());
                            profClassesBox.removeItem(c.toString());
                        }
                    }
                }catch(ConcurrentModificationException ignore){}
            }
        });

        JButton aggiungi = new JButton("AGGIUNGI");
        aggiungi.addActionListener(e ->{
            if(Objects.requireNonNull(allClassesBox.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                try{
                    for (Classe c: classi) {
                        if(c.toString().equals(Objects.requireNonNull(allClassesBox.getSelectedItem()).toString())){
                            classiDocente.add(c);
                            classi.remove(c);
                            profClassesBox.addItem(c.toString());
                            allClassesBox.removeItem(c.toString());
                        }
                    }
                }catch(ConcurrentModificationException ignore){}
            }
        });

        classPanel.add(allClassesBox);
        classPanel.add(aggiungi);
        classPanel.add(profClassesBox);
        classPanel.add(rimuovi);

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

        //listener x l'invio del docente
        conferma.addActionListener(e ->{
            //nome e cognome
            if(nomeField.getText().isEmpty() || cognomeField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Inserisci nome e cognome");
            }
            else if(controllore.codiceFiscaleInvalido(cfField.getText().trim().toLowerCase())){
                JOptionPane.showMessageDialog(null,"Codice Fiscale invalido");
            }
            else{
                //data di nascita
                int anno = Integer.parseInt(Objects.requireNonNull(annoCombo.getSelectedItem()).toString());
                int mese = meseCombo.getSelectedIndex();
                int giorno = Integer.parseInt(Objects.requireNonNull(giornoCombo.getSelectedItem()).toString());
                Date data = new GregorianCalendar(anno,mese,giorno).getTime();

                docente.setNome(nomeField.getText());
                docente.setCognome(cognomeField.getText());
                docente.setDataDiNascita(data);
                docente.setCF(cfField.getText());
                docente.setClassi(classiDocente);
                docente.setMaterie(materieDocente);

                controllore.registraDocente(docente);
                JOptionPane.showMessageDialog(null,":)");

                new DocentiFrame(controllore);
                dispose();
            }
        });

        confermaPanel.add(conferma);

        // Aggiungi i Panel allo sfondo
        sfondoLabel.add(formPanel1);
        sfondoLabel.add(titoloMaterie);
        sfondoLabel.add(titoloClassi);
        sfondoLabel.add(materiePanel);
        sfondoLabel.add(classPanel);
        sfondoLabel.add(confermaPanel);
        container.add(sfondoPanel);

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
