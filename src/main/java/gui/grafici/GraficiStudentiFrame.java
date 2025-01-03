package gui.grafici;

import Controllore.Controllore;
import gui.home.HomeFrame;
import gui.pulsanti.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.Objects;

public class GraficiStudentiFrame extends JFrame {
    private Controllore controllore;
    private JPanel formPanel2;
    public GraficiStudentiFrame(Controllore controllore){
        this.controllore = controllore;
        int width, height, b_height;

        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);

        Container container = this.getContentPane();

        Dimension screenSize = getSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();

        b_height = height / 12;

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
            new HomeFrame(controllore);
            dispose();
        });

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
        titlePanel.setBounds(width/6,height/6,width*2/3,height/5);
        titlePanel.setOpaque(false);
        sfondoLabel.add(titlePanel);
        titlePanel.setBackground(Color.white);

        JLabel titoloLabel = new JLabel("GRAFICI STUDENTI");
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

        //GRAFICO MEDIE MENSILI
        JButton medieMensiliButton = new JButton("MEDIE MENSILI");
        medieMensiliButton.addActionListener(e ->{
            sfondoLabel.remove(formPanel2);
            //ESEMPIO
            double[] f = {6.5,1.5,2,5,4,2,6,7,5.5,10};
            formPanel2 = new GraficoMedieMensiliPanel(f);
            formPanel2.setLayout(new BoxLayout(formPanel2, BoxLayout.Y_AXIS));
            formPanel2.setBounds(width*3/5,height/3+68, width/3, height/4);
            sfondoLabel.add(formPanel2);
            revalidate();
            repaint();
        });
        formPanel1.add(medieMensiliButton);
        //GRAFICO MEDIE X MATERIA
        JButton medieMaterieButton = new JButton("MEDIE X MATERIA");
        medieMaterieButton.addActionListener(e ->{
            sfondoLabel.remove(formPanel2);
            //ESEMPIO
            double[] f = {5,9,4,7};
            formPanel2 = new GraficoMedieMateriePanel(f);
            formPanel2.setLayout(new BoxLayout(formPanel2, BoxLayout.Y_AXIS));
            formPanel2.setBounds(width*3/5,height/3+68, width/3, height/4);
            sfondoLabel.add(formPanel2);
            revalidate();
            repaint();
        });
        formPanel1.add(medieMaterieButton);

        //PANEL FORM 2--------------------------------------------------------------
        formPanel2 = new JPanel();
        formPanel2.setLayout(new BoxLayout(formPanel2, BoxLayout.Y_AXIS));
        formPanel2.setBounds(width*3/5,height/3+68, width/3, height/4);
        formPanel2.setOpaque(false);

        sfondoLabel.add(formPanel1);
        sfondoLabel.add(formPanel2);
        container.add(sfondoPanel);

        revalidate();
        repaint();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
