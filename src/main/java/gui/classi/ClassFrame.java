package gui.classi;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;

import Controllore.Controllore;
import Utenti.Classe;
import gui.home.HomeFrame;
import gui.pulsanti.PulsanteExit;
import gui.pulsanti.PulsanteHome;
import gui.pulsanti.PulsanteIndietro;

public class ClassFrame extends JFrame {
    private Controllore controllore;
    private JButton selectedButton;

    /**
     * Funzione che costruisce la finestra delle classi.
     * Imposta: dimensione finestra, visibilità, layout e gestisce vari componenti.
     *
     * @param controllore Controllore che gestisce la logica.
     */
    public ClassFrame(Controllore controllore){
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
            if(modifica.equals(selectedButton)){
                modifica.setBackground(Color.WHITE);
                selectedButton = null;
            }
            else{
                modifica.setBackground(Color.GREEN);
                elimina.setBackground(Color.WHITE);
                selectedButton = modifica;
            }
        });
        elimina.addActionListener(e ->{
            if(elimina.equals(selectedButton)){
                elimina.setBackground(Color.WHITE);
                selectedButton = null;
            }
            else {
                elimina.setBackground(Color.GREEN);
                modifica.setBackground(Color.WHITE);
                selectedButton = elimina;
            }
        });
        //PULSANTI------------------------------------------------------

        //PANEL LISTA NOMI-------------------------------------------------
        JPanel elencoPanel = new JPanel();
        elencoPanel.setLayout(new BoxLayout(elencoPanel,BoxLayout.Y_AXIS));
        sfondoLabel.add(elencoPanel);
        elencoPanel.setBounds(width*2/3,height/2,b_width*5/2,b_height*3);
        elencoPanel.setOpaque(false);

        JLabel listaLabel = new JLabel("SELEZIONA UNA CLASSE");
        listaLabel.setFont(new Font("Arial", Font.BOLD, width/68));
        listaLabel.setForeground(Color.WHITE);

        JComboBox<String> listaClassi = new JComboBox<>();
        //DA PRENDERE ELENCO GENITORI
        ArrayList<Classe> classi = new ArrayList<>();
        classi.add(new Classe(5,"inf",'b'));
        classi.add(new Classe(4,"inf",'b'));

        listaClassi.addItem("");
        for (Classe tmp: classi) {
            listaClassi.addItem(tmp.toString());
        }

        //pulsanti
        JButton conferma = new JButton("CONFERMA");
        conferma.addActionListener(e ->{
            if(Objects.requireNonNull(listaClassi.getSelectedItem()).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Seleziona una classe");
            }
            else{
                try{
                    if(selectedButton.equals(modifica)){
                        //prendo la classe e la elimino dalla lista
                        String s = Objects.requireNonNull(listaClassi.getSelectedItem()).toString();
                        for (Classe tmp: classi) {
                            if(tmp.toString().equals(s)){
                                new ModificaClasseFrame(controllore,tmp);
                                dispose();
                            }
                        }
                    }
                    else if(selectedButton.equals(elimina)){
                        //prendo la classe e la elimino dalla lista anche qua
                    }
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null,"Seleziona un'azione da compiere sulla classe");
                }
            }
        });

        elencoPanel.add(listaLabel);
        elencoPanel.add(Box.createVerticalStrut(60));
        elencoPanel.add(listaClassi);
        elencoPanel.add(Box.createVerticalStrut(60));
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
