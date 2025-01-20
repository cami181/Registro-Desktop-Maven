package gui.classi;

import Controllore.Controllore;
import Utenti.Classe;
import gui.home.HomeFrame;
import gui.pulsanti.PulsanteExit;
import gui.pulsanti.PulsanteHome;
import gui.pulsanti.PulsanteIndietro;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Objects;

public class ModificaClasseFrame extends JFrame {
    /**
     * Funzione che crea la finestra per modificare le classi.
     *
     * @param controllore Controllore che gestisce la logica.
     * @param classe Classe che si deve modificare.
     */
    public ModificaClasseFrame(Controllore controllore, Classe classe) {
        Classe c = new Classe(classe.getAnno(),classe.getIndirizzo(),classe.getSezione());
        controllore.eliminaClasse(c);
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
            controllore.registraClasse(classe.getAnno(),classe.getIndirizzo(),classe.getSezione());
            JOptionPane.showMessageDialog(null,"classe salvata come prima");
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
            controllore.registraClasse(classe.getAnno(),classe.getIndirizzo(),classe.getSezione());
            JOptionPane.showMessageDialog(null,"classe salvata come prima");
            new ClassFrame(controllore);
            dispose();
        });

        //TITOLO----------------------------------------------------
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        titlePanel.setBounds(width/6,height/6,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("MODIFICA CLASSE");
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

        // ANNO
        JPanel annoPanel = new JPanel();
        annoPanel.setLayout(new BoxLayout(annoPanel, BoxLayout.X_AXIS));
        annoPanel.setOpaque(false);
        JLabel nomeLabel = new JLabel("Anno:");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        nomeLabel.setForeground(Color.WHITE);
        // ComboBox anno
        JComboBox<String> anno = new JComboBox<>();
        anno.addItem("");
        for (int i = 1; i <= 5; i++) {
            anno.addItem(String.valueOf(i));
        }
        anno.setSelectedIndex(classe.getAnno());
        annoPanel.add(nomeLabel);
        annoPanel.add(Box.createHorizontalStrut(10));
        annoPanel.add(anno);

        // CORSO
        JPanel corsoPanel = new JPanel();
        corsoPanel.setLayout(new BoxLayout(corsoPanel, BoxLayout.X_AXIS));
        corsoPanel.setOpaque(false);
        JLabel corsoLabel = new JLabel("Corso:");
        corsoLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        corsoLabel.setForeground(Color.WHITE);
        // ComboBox corso
        String[] s = {"inf","tur","cat"};
        JComboBox<String> corso = new JComboBox<>();
        corso.addItem("");
        for (String st : s) {
            corso.addItem(st);
        }
        corso.setSelectedItem(classe.getIndirizzo());
        corsoPanel.add(corsoLabel);
        corsoPanel.add(Box.createHorizontalStrut(10));
        corsoPanel.add(corso);

        // SEZIONE
        JPanel sezionePanel = new JPanel();
        sezionePanel.setLayout(new BoxLayout(sezionePanel, BoxLayout.X_AXIS));
        sezionePanel.setOpaque(false);
        JLabel sezioneLabel = new JLabel("Sezione:");
        sezioneLabel.setFont(new Font("Arial", Font.BOLD, height/35));
        sezioneLabel.setForeground(Color.WHITE);
        // ComboBox sezione
        char[] s2 = {'a','b','c'};
        JComboBox<Character> sectionCombo = new JComboBox<>();
        sectionCombo.addItem(' ');
        for (char st : s2) {
            sectionCombo.addItem(st);
        }
        sectionCombo.setSelectedItem(classe.getSezione());
        sezionePanel.add(sezioneLabel);
        sezionePanel.add(Box.createHorizontalStrut(10));
        sezionePanel.add(sectionCombo);

        formPanel1.add(annoPanel);
        formPanel1.add(Box.createVerticalStrut(60));
        formPanel1.add(sezionePanel);
        formPanel1.add(Box.createVerticalStrut(60));
        formPanel1.add(corsoPanel);

        //PULSANTE CONFERMA----------------------------------------------------------------------
        JPanel confermaPanel = new JPanel();
        confermaPanel.setLayout(new GridLayout(1,1));
        confermaPanel.setOpaque(false);
        confermaPanel.setBounds(width*3/4,height*4/5,b_width,b_height);

        JButton conferma = new JButton("CONFERMA");
        conferma.setFont(new Font("Arial", Font.BOLD, width/60));
        conferma.setBorder(new EtchedBorder());
        conferma.setBackground(new Color(189, 255, 136));
        conferma.setForeground(Color.DARK_GRAY);

        //listener x l'invio della classe
        conferma.addActionListener(e ->{
            if(Objects.equals(anno.getSelectedItem(), "") || Objects.equals(corso.getSelectedItem(), "") || Objects.equals(sectionCombo.getSelectedItem(), ' ')){
                JOptionPane.showMessageDialog(null,"Compila tutti i campi");
            }
            else{
                if(controllore.alreadyExistentClass(Integer.parseInt((String) Objects.requireNonNull(anno.getSelectedItem())),
                        Objects.requireNonNull(corso.getSelectedItem()).toString(),
                        Objects.requireNonNull(sectionCombo.getSelectedItem()).toString().charAt(0))){
                    JOptionPane.showMessageDialog(null,"Classe già esistente");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Classe registrata");
                    new ClassFrame(controllore);
                    dispose();
                }
            }
        });

        confermaPanel.add(conferma);

        // Aggiungi i Panel allo sfondo
        sfondoLabel.add(formPanel1);
        sfondoLabel.add(confermaPanel);
        container.add(sfondoPanel);

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
