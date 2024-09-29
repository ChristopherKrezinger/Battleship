package Battleship_1_0;

import javax.swing.*;

import javax.swing.border.LineBorder;
import java.awt.*;

//main game Panel
public class Main_Game extends JPanel {
    final ImageIcon GAME = new ImageIcon("resources/Main_Game.png");

    //Image of ships
    static final ImageIcon BATTLESHIP = new ImageIcon("resources/Battleship.png");
    static final ImageIcon CARRIER = new ImageIcon("resources/Carrier.png");
    static final ImageIcon CRUISER = new ImageIcon("resources/Cruiser.png");
    static final ImageIcon DESTROYER = new ImageIcon("resources/Destroyer.png");
    static final ImageIcon SUBMARINE = new ImageIcon("resources/SubMarine.png");


    //Resize Battleship
    ImageIcon imageIcon_bs = new ImageIcon("resources/Battleship.png");
    Image image_bs = imageIcon_bs.getImage(); // transform it
    Image resizedBATTLESHIP = image_bs.getScaledInstance(BATTLESHIP.getIconWidth(), BATTLESHIP.getIconHeight() - 40, java.awt.Image.SCALE_SMOOTH); // scale it

    //Resize Carrier
    ImageIcon imageIcon_ca = new ImageIcon("resources/Carrier.png");
    Image image_ca = imageIcon_ca.getImage(); // transform it
    Image resizedCARRIER = image_ca.getScaledInstance(CARRIER.getIconWidth() - 25, CARRIER.getIconHeight() - 50, java.awt.Image.SCALE_SMOOTH); // scale it

    //Resize cruiser
    ImageIcon imageIcon_cr = new ImageIcon("resources/Cruiser.png");
    Image image_cr = imageIcon_cr.getImage(); // transform it
    Image resizedCRUISER = image_cr.getScaledInstance(CRUISER.getIconWidth(), CRUISER.getIconHeight() - 40, java.awt.Image.SCALE_SMOOTH); // scale it

    //Resize submarine
    ImageIcon imageIcon_sm = new ImageIcon("resources/SubMarine.png");
    Image image_sm = imageIcon_sm.getImage(); // transform it
    Image resizedSUBMARINE = image_sm.getScaledInstance(SUBMARINE.getIconWidth(), SUBMARINE.getIconHeight() - 40, java.awt.Image.SCALE_SMOOTH); // scale it

    //Resize destroyer
    ImageIcon imageIcon_ds = new ImageIcon("resources/Destroyer.png");
    Image image_ds = imageIcon_ds.getImage(); // transform it
    Image resizedDESTROYER = image_ds.getScaledInstance(DESTROYER.getIconWidth(), DESTROYER.getIconHeight() - 30, java.awt.Image.SCALE_SMOOTH); // scale it


    //sound object
    static Sound player = new Sound();
    //Filepath of background music
    static String background_music = "resources/submarine.wav";

    //ships
    JLabel game = new JLabel();
    JLabel battleship = new JLabel();
    JLabel carrier = new JLabel();
    JLabel cruiser = new JLabel();
    JLabel submarine = new JLabel();
    JLabel destroyer = new JLabel();
    JLabel outline = new JLabel();

    //popup text
    JTextArea popup = new JTextArea();

    //player and ai field
    JButton[][] Playerfield = new JButton[10][10];
    JButton[][] AIfield = new JButton[10][10];

    //layout for field
    JPanel gridPanel = new JPanel(new GridBagLayout());


    Drag_and_drop dandd = new Drag_and_drop(Playerfield, gridPanel);
    Rotation_KeyListener keylistener = new Rotation_KeyListener();

    //constructor
    Main_Game() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1280, 720);



        //Background
        game.setIcon(GAME);
        game.setVisible(true);
        game.setBounds(0, 0, 1280, 720);

        //Battleship
        imageIcon_bs = new ImageIcon(resizedBATTLESHIP);
        battleship.setIcon(imageIcon_bs);
        battleship.setVisible(true);
        battleship.setBounds(560, 310, imageIcon_bs.getIconWidth(), imageIcon_bs.getIconHeight());
        battleship.putClientProperty("toggle", true);
        battleship.putClientProperty("size",5);
        battleship.addMouseListener(dandd);
        battleship.addMouseMotionListener(dandd);
        battleship.addKeyListener(keylistener);
        battleship.setBorder(new LineBorder(Color.RED));

        //Carrier
        imageIcon_ca = new ImageIcon(resizedCARRIER);
        carrier.setIcon(imageIcon_ca);
        carrier.setVisible(true);
        carrier.setBounds(500, 290, imageIcon_ca.getIconWidth(), imageIcon_ca.getIconHeight());
        carrier.putClientProperty("toggle", true);
        carrier.putClientProperty("size",4);
        carrier.addMouseListener(dandd);
        carrier.addMouseMotionListener(dandd);
        carrier.addKeyListener(keylistener);
        carrier.setBorder(new LineBorder(Color.RED));

        //Cruiser
        imageIcon_cr = new ImageIcon(resizedCRUISER);
        cruiser.setIcon(imageIcon_cr);
        cruiser.setVisible(true);
        cruiser.setBounds(610, 330, imageIcon_cr.getIconWidth(), imageIcon_cr.getIconHeight());
        cruiser.putClientProperty("toggle", true);
        cruiser.putClientProperty("size",3);
        cruiser.addMouseListener(dandd);
        cruiser.addMouseMotionListener(dandd);
        cruiser.addKeyListener(keylistener);

        //Submarine
        imageIcon_sm = new ImageIcon(resizedSUBMARINE);
        submarine.setIcon(imageIcon_sm);
        submarine.setVisible(true);
        submarine.setBounds(650, 330, imageIcon_sm.getIconWidth(), imageIcon_sm.getIconHeight());
        submarine.putClientProperty("toggle", true);
        submarine.putClientProperty("size",3);
        submarine.addMouseListener(dandd);
        submarine.addMouseMotionListener(dandd);
        submarine.addKeyListener(keylistener);

        //Destroyer
        imageIcon_ds = new ImageIcon(resizedDESTROYER);
        destroyer.setIcon(imageIcon_ds);
        destroyer.setVisible(true);
        destroyer.setBounds(700, 350, imageIcon_ds.getIconWidth(), imageIcon_ds.getIconHeight());
        destroyer.putClientProperty("toggle", true);
        destroyer.putClientProperty("size",2);
        destroyer.addMouseListener(dandd);
        destroyer.addMouseMotionListener(dandd);
        destroyer.addKeyListener(keylistener);
        destroyer.setBorder(new LineBorder(Color.RED));

        //Ship selection
        outline.setBounds(491, 281, 300, 200);
        outline.setBorder(new LineBorder(Color.WHITE,4));

        //Notification Popup
        popup.setBounds((game.getWidth() - 200) /2, (game.getHeight() - 50) /2, 200, 50);
        popup.setText("Ihr Schiff liegt außerhalb des Feldes!!");
        popup.setLineWrap(true);
        popup.setWrapStyleWord(true);
        popup.setEditable(false);
        popup.setFocusable(false);
        popup.setOpaque(true);
        popup.setBackground(Color.WHITE);
        popup.setForeground(Color.RED);
        popup.setFont(Buttons_Menu.font.getQTMilitary(14f));

        //Player field
        GridBagConstraints gbc = new GridBagConstraints();
        gridPanel.setVisible(true);
        gridPanel.setOpaque(false);
        gridPanel.setBorder(new LineBorder(Color.RED));
        gridPanel.setBounds(85,233,390,388);
       // gridPanel.setOpaque(false);

        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                Playerfield[i][n] = new JButton();
                Playerfield[i][n].setPreferredSize(new Dimension(30,30));
                Playerfield[i][n].setBorder(new LineBorder(Color.RED));
                gbc.gridx = n;
                gbc.gridy = i;

                gbc.fill = GridBagConstraints.RELATIVE;


                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                gridPanel.add(Playerfield[i][n], gbc);
            }
        }

        //AIfield
        JPanel gridPanel_ai = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_ai = new GridBagConstraints();
        gridPanel_ai.setVisible(true);
        gridPanel_ai.setOpaque(false);
        gridPanel_ai.setBounds(845,233,390,388);
        // gridPanel.setOpaque(false);

        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                AIfield[i][n] = new JButton();
                AIfield[i][n].setPreferredSize(new Dimension(30,30));
                gbc_ai.gridx = n;
                gbc_ai.gridy = i;

                gbc_ai.fill = GridBagConstraints.RELATIVE;

                gbc_ai.weightx = 1.0;
                gbc_ai.weighty = 1.0;

                gridPanel_ai.add(AIfield[i][n], gbc_ai);
            }
        }


        //adds content to layout
        layeredPane.add(game, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(battleship, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(carrier, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(cruiser, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(submarine, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(destroyer, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(outline, JLayeredPane.MODAL_LAYER);
        layeredPane.add(popup, JLayeredPane.POPUP_LAYER);
        layeredPane.add(gridPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(gridPanel_ai, JLayeredPane.PALETTE_LAYER);




        //Settings for frame
        this.add(layeredPane);
        this.setLayout(null); // Disable layout manager
        this.setSize(1280, 720);
        this.setVisible(true); // visibility of the instance of Frame

       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing of window



    }

}
