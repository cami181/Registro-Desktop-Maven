package gui.studenti;

import Credenziali.Credenziali;
import Utenti.*;
import Controllore.Controllore;
import gui.home.HomeFrame;
import gui.pulsanti.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
public class ModificaStudenteFrame extends JFrame {
    private ArrayList<Classe> classi;

    /**
     * Funzione che crea la finestra per modificare gli studenti.
     *
     * @param controllore Controllore che gestisce la logica.
     * @param studente Studente che si deve modificare.
     */
    public ModificaStudenteFrame(Controllore controllore, Studente studente) {
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
            controllore.eliminaStudente(studente);
            controllore.registraStudente(studente);
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
            new StudentiFrame(controllore);
            dispose();
        });
        //INDIETRO---------------------------------------------

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/6,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("MODIFICA STUDENTE");
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
        nomeField.setText(studente.getNome());
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
        cognomeField.setText(studente.getCognome());
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
        calendar.setTime(studente.getDataDiNascita());

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
        formPanel1.add(Box.createVerticalStrut(60));  // Spazio tra i campi
        formPanel1.add(cognomePanel);
        formPanel1.add(Box.createVerticalStrut(60));
        formPanel1.add(dataNascitaPanel);

        //PANEL 2--------------------------------------------------------------
        JPanel formPanel2 = new JPanel();
        formPanel2.setLayout(new BoxLayout(formPanel2, BoxLayout.Y_AXIS));
        formPanel2.setBounds(width*3/5,height/3+68, width/3, height/3);
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
        cfField.setText(studente.getCF());

        cfPanel.add(cfLabel);
        cfPanel.add(Box.createHorizontalStrut(10));
        cfPanel.add(cfField);

        //CLASSE------------------------------------------------------------
        JPanel elencoPanel = new JPanel();
        elencoPanel.setLayout(new BoxLayout(elencoPanel,BoxLayout.Y_AXIS));
        sfondoLabel.add(elencoPanel);
        elencoPanel.setBounds(width*2/3-50,height/2-b_height,b_width*5/2,b_height*5);
        elencoPanel.setOpaque(false);

        JLabel listaLabel = new JLabel("CLASSE");
        listaLabel.setFont(new Font("Arial", Font.BOLD, width/68));
        listaLabel.setForeground(Color.WHITE);

        JPanel cercaPanel = new JPanel();
        cercaPanel.setLayout(new BoxLayout(cercaPanel,BoxLayout.X_AXIS));
        cercaPanel.setBounds(width*2/3,height/2+b_height,b_width*5/2,b_height/2);
        cercaPanel.setOpaque(false);

        JLabel annoClasse = new JLabel("ANNO: ");
        JLabel indirizzo = new JLabel("INDIRIZZO: ");
        annoClasse.setFont(new Font("Arial", Font.BOLD, height/60));
        annoClasse.setForeground(Color.WHITE);
        indirizzo.setFont(new Font("Arial", Font.BOLD, height/60));
        indirizzo.setForeground(Color.WHITE);

        JComboBox<String> annoCercaCombo = new JComboBox<>();
        annoCercaCombo.addItem("");
        for(int i=1;i<6;i++){
            annoCercaCombo.addItem(String.valueOf(i));
        }
        JComboBox<String> indirizzoCercaCombo = new JComboBox<>();
        indirizzoCercaCombo.addItem("");
        for (String s: controllore.getIndirizzi()) {
            indirizzoCercaCombo.addItem(s);
        }

        classi = controllore.getClassi();
        JComboBox<String> listaClassi = new JComboBox<>();
        listaClassi.addItem("");

        for (Classe s: classi) {
            listaClassi.addItem(s.toString());
            if(studente.getClasse().toString().equals(s.toString())){
                listaClassi.setSelectedItem(s.toString());
            }
        }

        cercaPanel.add(annoClasse);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(annoCercaCombo);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(indirizzo);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(indirizzoCercaCombo);

        //cerca
        JPanel confermaCercaPanel = new JPanel();
        confermaCercaPanel.setLayout(new BoxLayout(confermaCercaPanel,BoxLayout.X_AXIS));
        confermaCercaPanel.setBounds(width*2/3,height*5/6,b_width*5/2,b_height);
        confermaCercaPanel.setOpaque(false);
        JButton cercaPulsante = new JButton("CERCA");
        cercaPulsante.addActionListener(e ->{
            listaClassi.removeAllItems();
            listaClassi.addItem("");
            if(annoCercaCombo.getSelectedItem().toString().isEmpty() && indirizzoCercaCombo.getSelectedItem().toString().isEmpty()){
                classi = controllore.getClassi();
            }
            else{
                try{
                    classi = controllore.cercaClasse(Integer.parseInt(annoCercaCombo.getSelectedItem().toString()), indirizzoCercaCombo.getSelectedItem().toString());
                }catch (NumberFormatException ex){
                    classi = controllore.cercaClasse(0, indirizzoCercaCombo.getSelectedItem().toString());
                }
            }
            for (Classe s: classi) {
                listaClassi.addItem(s.toString());
            }
        });
        confermaCercaPanel.add(cercaPulsante);
        //conferma

        elencoPanel.add(listaLabel);
        elencoPanel.add(Box.createVerticalStrut(30));
        elencoPanel.add(cercaPanel);
        elencoPanel.add(Box.createVerticalStrut(30));
        elencoPanel.add(confermaCercaPanel);
        elencoPanel.add(Box.createVerticalStrut(30));
        elencoPanel.add(listaClassi);
        //classi

        //aggiunta al panel
        formPanel2.add(cfPanel);
        formPanel2.add(Box.createVerticalStrut(20));
        formPanel2.add(elencoPanel);

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
            else if(Objects.equals(listaClassi.getSelectedItem(), "")){
                JOptionPane.showMessageDialog(null,"Inserire la classe");
            }
            else if(controllore.codiceFiscaleInvalido(cfField.getText())){
                JOptionPane.showMessageDialog(null,"Codice Fiscale invalido");
            }
            else if(controllore.alreadyExistentCf(cfField.getText(),studente.getCredenziali())){
                JOptionPane.showMessageDialog(null,"Codice Fiscale già esistente");
            }
            else{
                //data di nascita
                int anno = Integer.parseInt(Objects.requireNonNull(annoCombo.getSelectedItem()).toString());
                int mese = meseCombo.getSelectedIndex();
                int giorno = Integer.parseInt(Objects.requireNonNull(giornoCombo.getSelectedItem()).toString());
                Date data = new GregorianCalendar(anno,mese,giorno).getTime();

                Classe classe = null;
                for (Classe c: classi) {
                    if(Objects.requireNonNull(listaClassi.getSelectedItem()).toString().equals(c.toString())){
                        classe = c;
                        break;
                    }
                }
                controllore.eliminaStudente(studente);

                String nome = nomeField.getText().replace(" ","");
                String cognome = cognomeField.getText().replace(" ","");
                studente.setNome(nome);
                studente.setCognome(cognome);
                studente.setDataDiNascita(data);
                studente.setCF(cfField.getText().trim().toLowerCase());
                studente.setClasse(classe);

                String user = "s" + studente.getNome() + "." + studente.getCognome();
                String tmp = "s" + studente.getNome() + "." + studente.getCognome();;
                int n = 0;
                while(controllore.alreadyExistentUser(tmp)){
                    tmp = user + String.valueOf(n);
                    n++;
                }
                user = tmp;
                Credenziali credenziali = new Credenziali(user,studente.getCredenziali().getPassword());
                studente.setCredenziali(credenziali);
                controllore.registraStudente(studente);
                new StudentiFrame(controllore);
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