package gui.studenti;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

import Controllore.Controllore;
import Utenti.Studente;
import gui.home.HomeFrame;
import gui.pulsanti.*;

public class StudentiFrame extends JFrame {
    private ArrayList<Studente> studenti;

    /**
     * Funzione che costruisce la finestra degli studenti.
     * Imposta: dimensione finestra, visibilità, layout e gestisce vari componenti.
     *
     * @param controllore Controllore che gestisce la logica.
     */
    public StudentiFrame(Controllore controllore){
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

        //HOME--------------------------------------------------------
        JPanel homePanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(homePanel);
        homePanel.setBounds(0,0,b_height,b_height);
        homePanel.setOpaque(false);

        PulsanteHome homeButton = new PulsanteHome(b_height);
        homeButton.setFont(new Font("Arial", Font.BOLD, width/40));
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
        titlePanel.setBounds(width/6,height/4,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("STUDENTI");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------

        //PANEL LISTA NOMI-------------------------------------------------
        JPanel elencoPanel = new JPanel();
        elencoPanel.setLayout(new BoxLayout(elencoPanel,BoxLayout.Y_AXIS));
        sfondoLabel.add(elencoPanel);
        elencoPanel.setBounds(width*2/3-50,height/2,b_width*5/2,b_height*7/2);
        elencoPanel.setOpaque(false);

        JLabel listaLabel = new JLabel("SELEZIONA");
        listaLabel.setFont(new Font("Arial", Font.BOLD, width/68));
        listaLabel.setForeground(Color.WHITE);

        //nome cognome cerca------------------------------------------
        JPanel cercaPanel = new JPanel();
        cercaPanel.setLayout(new BoxLayout(cercaPanel,BoxLayout.X_AXIS));
        cercaPanel.setBounds(width*2/3,height*5/6,b_width*5/2,b_height/2);
        cercaPanel.setOpaque(false);

        JLabel nome = new JLabel("NOME: ");
        JLabel cognome = new JLabel("COGNOME: ");
        nome.setFont(new Font("Arial", Font.BOLD, height/60));
        nome.setForeground(Color.WHITE);
        cognome.setFont(new Font("Arial", Font.BOLD, height/60));
        cognome.setForeground(Color.WHITE);

        JTextField nomeField = new JTextField();
        JTextField cognomeField = new JTextField();

        JComboBox<String> listaStudenti = new JComboBox<>();
        listaStudenti.addItem("");
        studenti = controllore.getStudenti();
        for (Studente s: studenti) {
            listaStudenti.addItem(s.getNome() + " " + s.getCognome() + " " + s.getCF());
        }

        cercaPanel.add(nome);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(nomeField);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(cognome);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(cognomeField);
        //nome cognome cerca------------------------------------------

        //PULSANTI------------------------------------------------------
        JButton crea = new JButton("CREA");
        crea.setFont(new Font("Arial", Font.BOLD, width/40));
        crea.setBorder(new EtchedBorder());
        crea.setBackground(Color.WHITE);
        crea.setForeground(Color.DARK_GRAY);

        crea.addActionListener(e -> {
            new CreaStudentiFrame(controllore);
            dispose();
        });

        JButton modifica = new JButton("MODIFICA");
        modifica.setFont(new Font("Arial", Font.BOLD, width/40));
        modifica.setBorder(new EtchedBorder());
        modifica.setBackground(Color.WHITE);
        modifica.setForeground(Color.DARK_GRAY);

        JButton elimina = new JButton("ELIMINA");
        elimina.setFont(new Font("Arial", Font.BOLD, width/40));
        elimina.setBorder(new EtchedBorder());
        elimina.setBackground(Color.WHITE);
        elimina.setForeground(Color.DARK_GRAY);

        modifica.addActionListener(e ->{
            if(Objects.requireNonNull(listaStudenti.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona uno studente");
            }
            else{
                String cf = Objects.requireNonNull(listaStudenti.getSelectedItem()).toString().split(" ")[2];
                try{
                    for (Studente s: controllore.getStudenti()) {
                        if(s.getCF().equalsIgnoreCase(cf)){
                            new ModificaStudenteFrame(controllore,s);
                            dispose();
                        }
                    }
                }catch (ConcurrentModificationException ignore){}
            }
        });
        elimina.addActionListener(e ->{
            if(Objects.requireNonNull(listaStudenti.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona uno studente");
            }
            else{
                String cf = Objects.requireNonNull(listaStudenti.getSelectedItem()).toString().split(" ")[2];
                try{
                    for (Studente s: controllore.getStudenti()) {
                        if(s.getCF().equalsIgnoreCase(cf)){
                            controllore.eliminaStudente(s);
                            listaStudenti.removeAllItems();
                            listaStudenti.addItem("");
                            for (Studente st: controllore.getStudenti()) {
                                listaStudenti.addItem(st.getNome() + " " + st.getCognome() + " " + st.getCF());
                            }
                        }
                    }
                }catch (ConcurrentModificationException ignore){}
            }
        });
        //PULSANTI------------------------------------------------------

        //cerca
        JPanel confermaPanel = new JPanel();
        confermaPanel.setLayout(new BoxLayout(confermaPanel,BoxLayout.X_AXIS));
        confermaPanel.setBounds(width*2/3,height*5/6+b_height/2,b_width*5/2,b_height);
        confermaPanel.setOpaque(false);
        JButton conferma = new JButton("CERCA");
        conferma.addActionListener(e ->{
            listaStudenti.removeAllItems();
            if(nomeField.getText().isEmpty() && cognomeField.getText().isEmpty()){
                studenti = controllore.getStudenti();
            }
            else{
                studenti = controllore.cercaStudente(nomeField.getText(), cognomeField.getText());
            }
            for (Studente s: studenti) {
                listaStudenti.addItem(s.getNome() + " " + s.getCognome() + " " + s.getCF());
            }
        });
        confermaPanel.add(conferma);
        //conferma

        elencoPanel.add(listaLabel);
        elencoPanel.add(Box.createVerticalStrut(50));
        elencoPanel.add(listaStudenti);
        elencoPanel.add(Box.createVerticalStrut(50));
        elencoPanel.add(cercaPanel);
        elencoPanel.add(Box.createVerticalStrut(50));
        elencoPanel.add(conferma);
        //PANEL LISTA NOMI-------------------------------------------------

        //PANEL PULSANTI-------------------------------------------------------
        JPanel opzioniPanel = new JPanel(new GridLayout(3,1));
        sfondoLabel.add(opzioniPanel);
        opzioniPanel.setBounds(width/4,height/2,b_width*2,b_height*3);
        opzioniPanel.setOpaque(false);

        opzioniPanel.add(crea);
        opzioniPanel.add(modifica);
        opzioniPanel.add(elimina);
        //PANEL PULSANTI-------------------------------------------------------

        container.add(sfondoPanel);
        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
