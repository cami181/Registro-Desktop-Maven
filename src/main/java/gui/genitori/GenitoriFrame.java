package gui.genitori;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;

import Controllore.Controllore;
import Utenti.*;
import gui.home.HomeFrame;
import gui.pulsanti.*;

public class GenitoriFrame extends JFrame {
    private String selectedButton = "";
    private ArrayList<Genitore> genitori;
    /**
     * Funzione che costruisce la finestra dei genitori.
     * Imposta: dimensione finestra, visibilità, layout e gestisce vari componenti.
     *
     * @param controllore Controllore che gestisce la logica.
     */
    public GenitoriFrame(Controllore controllore){
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

        //PULSANTE INDIETRO----------------------------------------
        JPanel indietroPanel = new JPanel(new GridLayout(1,1));
        sfondoLabel.add(indietroPanel);
        indietroPanel.setBounds(0,b_height,b_height,b_height);
        indietroPanel.setOpaque(false);

        PulsanteIndietro indietroButton = new PulsanteIndietro(b_height);
        indietroButton.setFont(new Font("Arial", Font.BOLD, width/40));
        indietroButton.setBorder(new EtchedBorder());
        indietroButton.setBackground(Color.WHITE);
        indietroButton.setForeground(Color.DARK_GRAY);
        indietroPanel.add(indietroButton);

        indietroButton.addActionListener(e->{
            new HomeFrame(controllore);
            dispose();
        });
        //PULSANTE INDIETRO---------------------------------------

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/4,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("GENITORI");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------

        //PULSANTI------------------------------------------------------
        JButton crea = new JButton("CREA");
        crea.setFont(new Font("Arial", Font.BOLD, width/40));
        crea.setBorder(new EtchedBorder());
        crea.setBackground(Color.WHITE);
        crea.setForeground(Color.DARK_GRAY);

        crea.addActionListener(e -> {
            new CreaGenitoriFrame(controllore);
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
            if(selectedButton.equals("modifica")){
                modifica.setBackground(Color.WHITE);
                selectedButton = "";
            }
            else{
                modifica.setBackground(Color.GREEN);
                elimina.setBackground(Color.WHITE);
                selectedButton = "modifica";
            }
        });
        elimina.addActionListener(e ->{
            if(selectedButton.equals("elimina")){
                elimina.setBackground(Color.WHITE);
                selectedButton = "";
            }
            else {
                elimina.setBackground(Color.GREEN);
                modifica.setBackground(Color.WHITE);
                selectedButton = "elimina";
            }
        });
        //PULSANTI------------------------------------------------------

        /*//PANEL LISTA NOMI-------------------------------------------------
        JPanel elencoPanel = new JPanel();
        elencoPanel.setLayout(new BoxLayout(elencoPanel,BoxLayout.Y_AXIS));
        sfondoLabel.add(elencoPanel);
        elencoPanel.setBounds(width*2/3,height/2,b_width*5/2,b_height*3);
        elencoPanel.setOpaque(false);

        JLabel listaLabel = new JLabel("SELEZIONA UN GENITORE");
        listaLabel.setFont(new Font("Arial", Font.BOLD, width/68));
        listaLabel.setForeground(Color.WHITE);

        JComboBox<String> listaGenitori = new JComboBox<>();
        //DA PRENDERE ELENCO GENITORI
        ArrayList<Genitore> genitori = controllore.getGenitori();

        listaGenitori.addItem("");
        for (Genitore tmp: genitori) {
            listaGenitori.addItem(tmp.getCF());
        }

        //pulsanti
        JButton conferma = new JButton("CONFERMA");
        conferma.addActionListener(e ->{
            if(Objects.requireNonNull(listaGenitori.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona un genitore");
            }
            else{
                if(!selectedButton.isEmpty()){
                    if(selectedButton.equals("modifica")){
                        //prendo il genitore e lo elimino dalla lista
                        String cf = Objects.requireNonNull(listaGenitori.getSelectedItem()).toString();
                        for (Genitore tmp: genitori) {
                            if(tmp.getCF().equals(cf)){
                                new ModificaGenitoreFrame(controllore,tmp);
                                dispose();
                            }
                        }
                    }
                    else if(selectedButton.equals("elimina")){
                        String cf = Objects.requireNonNull(listaGenitori.getSelectedItem()).toString();
                        for (Genitore tmp: genitori) {
                            if(tmp.getCF().equals(cf)){
                                controllore.eliminaGenitore(tmp);
                                JOptionPane.showMessageDialog(null,"Genitore eliminato");
                                new GenitoriFrame(controllore);
                                dispose();
                            }
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Seleziona un'azione da compiere sul genitore");
                }
            }
        });

        elencoPanel.add(listaLabel);
        elencoPanel.add(Box.createVerticalStrut(60));
        elencoPanel.add(listaGenitori);
        elencoPanel.add(Box.createVerticalStrut(60));
        elencoPanel.add(conferma);
        //PANEL LISTA NOMI-------------------------------------------------*/

        //PANEL LISTA NOMI-------------------------------------------------
        JPanel elencoPanel = new JPanel();
        elencoPanel.setLayout(new BoxLayout(elencoPanel,BoxLayout.Y_AXIS));
        sfondoLabel.add(elencoPanel);
        elencoPanel.setBounds(width*2/3,height/2,b_width*5/2,b_height*7/2);
        elencoPanel.setOpaque(false);

        JLabel listaLabel = new JLabel("SELEZIONA UN GENITORE");
        listaLabel.setFont(new Font("Arial", Font.BOLD, width/68));
        listaLabel.setForeground(Color.WHITE);

        //nome cognome cerca------------------------------------------
        JPanel cercaPanel = new JPanel();
        cercaPanel.setLayout(new BoxLayout(cercaPanel,BoxLayout.X_AXIS));
        cercaPanel.setBounds(width*2/3,height/2+b_height,b_width*5/2,b_height/2);
        cercaPanel.setOpaque(false);

        JLabel nome = new JLabel("NOME: ");
        JLabel cognome = new JLabel("COGNOME: ");
        nome.setFont(new Font("Arial", Font.BOLD, height/60));
        nome.setForeground(Color.WHITE);
        cognome.setFont(new Font("Arial", Font.BOLD, height/60));
        cognome.setForeground(Color.WHITE);

        JTextField nomeField = new JTextField();
        JTextField cognomeField = new JTextField();

        JComboBox<String> listaGenitori = new JComboBox<>();
        listaGenitori.addItem("");
        genitori = controllore.getGenitori();
        for (Genitore g: genitori) {
            listaGenitori.addItem(g.getNome() + " " + g.getCognome() + " " + g.getCF());
        }

        cercaPanel.add(nome);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(nomeField);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(cognome);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(cognomeField);
        //nome cognome cerca------------------------------------------

        //cerca
        JPanel confermaPanel = new JPanel();
        confermaPanel.setLayout(new BoxLayout(confermaPanel,BoxLayout.X_AXIS));
        confermaPanel.setBounds(width*2/3,height*5/6,b_width*5/2,b_height);
        confermaPanel.setOpaque(false);
        JButton conferma = new JButton("CERCA");
        conferma.addActionListener(e ->{
            listaGenitori.removeAllItems();
            listaGenitori.addItem("");
            if(nomeField.getText().isEmpty() && cognomeField.getText().isEmpty()){
                genitori = controllore.getGenitori();
            }
            else{
                genitori = controllore.cercaGenitore(nomeField.getText(), cognomeField.getText());
            }
            for (Genitore g: genitori) {
                listaGenitori.addItem(g.getNome() + " " + g.getCognome() + " " + g.getCF());
            }
        });
        confermaPanel.add(conferma);
        //conferma

        elencoPanel.add(listaLabel);
        elencoPanel.add(Box.createVerticalStrut(50));
        elencoPanel.add(cercaPanel);
        elencoPanel.add(Box.createVerticalStrut(50));
        elencoPanel.add(confermaPanel);
        elencoPanel.add(Box.createVerticalStrut(50));
        elencoPanel.add(listaGenitori);
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
