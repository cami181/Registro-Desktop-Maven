package gui.classi;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;

import Controllore.Controllore;
import Utenti.Classe;
import Utenti.Studente;
import gui.home.HomeFrame;
import gui.pulsanti.PulsanteHome;

public class ClassFrame extends JFrame {
    private ArrayList<Classe> classi;
    /**
     * Funzione che costruisce la finestra delle classi.
     * Imposta: dimensione finestra, visibilità, layout e gestisce vari componenti.
     *
     * @param controllore Controllore che gestisce la logica.
     */
    public ClassFrame(Controllore controllore){
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

        JLabel titoloLabel = new JLabel("CLASSI");
        titoloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titoloLabel.setFont(new Font("Arial", Font.BOLD, width/20));

        titoloLabel.setForeground(Color.white);
        titlePanel.add(titoloLabel);
        //TITOLO-----------------------------------------------------

        //PANEL LISTA CLASSI-------------------------------------------------
        JPanel elencoPanel = new JPanel();
        elencoPanel.setLayout(new BoxLayout(elencoPanel,BoxLayout.Y_AXIS));
        sfondoLabel.add(elencoPanel);
        elencoPanel.setBounds(width*2/3-50,height/2,b_width*5/2,b_height*7/2);
        elencoPanel.setOpaque(false);

        JLabel listaLabel = new JLabel("CLASSE");
        listaLabel.setFont(new Font("Arial", Font.BOLD, width/68));
        listaLabel.setForeground(Color.WHITE);

        //nome cognome cerca------------------------------------------
        JPanel cercaPanel = new JPanel();
        cercaPanel.setLayout(new BoxLayout(cercaPanel,BoxLayout.X_AXIS));
        cercaPanel.setBounds(width*2/3,height/2+b_height,b_width*5/2,b_height/2);
        cercaPanel.setOpaque(false);

        JLabel anno = new JLabel("ANNO: ");
        JLabel indirizzo = new JLabel("INDIRIZZO: ");
        anno.setFont(new Font("Arial", Font.BOLD, height/60));
        anno.setForeground(Color.WHITE);
        indirizzo.setFont(new Font("Arial", Font.BOLD, height/60));
        indirizzo.setForeground(Color.WHITE);

        JComboBox<String> annoCombo = new JComboBox<>();
        annoCombo.addItem("");
        for(int i=1;i<6;i++){
            annoCombo.addItem(String.valueOf(i));
        }
        JComboBox<String> indirizzoCombo = new JComboBox<>();
        indirizzoCombo.addItem("");
        for (String s: controllore.getMaterie()) {
            indirizzoCombo.addItem(s);
        }

        classi = controllore.getClassi();
        JComboBox<String> listaClassi = new JComboBox<>();
        listaClassi.addItem("");

        for (Classe s: classi) {
            listaClassi.addItem(s.toString());
        }

        cercaPanel.add(anno);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(annoCombo);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(indirizzo);
        cercaPanel.add(Box.createHorizontalStrut(20));
        cercaPanel.add(indirizzoCombo);

        //cerca
        JPanel confermaPanel = new JPanel();
        confermaPanel.setLayout(new BoxLayout(confermaPanel,BoxLayout.X_AXIS));
        confermaPanel.setBounds(width*2/3,height*5/6,b_width*5/2,b_height);
        confermaPanel.setOpaque(false);
        JButton conferma = new JButton("CERCA");
        conferma.addActionListener(e ->{
            listaClassi.removeAllItems();
            listaClassi.addItem("");
            if(annoCombo.getSelectedItem().toString().isEmpty() && indirizzoCombo.getSelectedItem().toString().isEmpty()){
                classi = controllore.getClassi();
            }
            else{
                classi = controllore.cercaClasse(Integer.parseInt(annoCombo.getSelectedItem().toString()), indirizzoCombo.getSelectedItem().toString());
            }
            for (Classe s: classi) {
                listaClassi.addItem(s.toString());
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
        elencoPanel.add(listaClassi);
        sfondoLabel.add(elencoPanel);

        //CERCA

        //PULSANTI------------------------------------------------------
        JButton crea = new JButton("CREA");
        crea.setFont(new Font("Arial", Font.BOLD, width/40));
        crea.setBorder(new EtchedBorder());
        crea.setBackground(Color.WHITE);
        crea.setForeground(Color.DARK_GRAY);

        crea.addActionListener(e -> {
            new CreaClasseFrame(controllore);
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
            if(listaClassi.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                String s = Objects.requireNonNull(listaClassi.getSelectedItem()).toString();
                for (Classe tmp: classi) {
                    if(tmp.toString().equals(s)){
                        new ModificaClasseFrame(controllore,tmp);
                        dispose();
                    }
                }
            }
        });

        elimina.addActionListener(e ->{
            if(listaClassi.getSelectedItem().toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                String s = listaClassi.getSelectedItem().toString();
                Classe c = new Classe(Integer.parseInt(String.valueOf(s.charAt(0))),s.substring(2),s.charAt(1));
                controllore.eliminaClasse(c);
                JOptionPane.showMessageDialog(null,"Classe eliminata");
                new ClassFrame(controllore);
                dispose();
            }
        });
        //PULSANTI------------------------------------------------------

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
