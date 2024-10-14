package Battleship_1_0;

import javax.swing.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Map;

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
    public static JTextArea popup = new JTextArea();

    //start button
    public static JButton start = new JButton();

    //player begins button
    private JButton player_begin = new JButton();

    private JButton AI_begin = new JButton();

    //player and AI field
    JButton[][] Playerfield = new JButton[10][10];
    JButton[][] AIfield = new JButton[10][10];

    //layout for PlayerField
    JPanel gridPanel = new JPanel(new GridBagLayout());

    //layout for AIField
    JPanel gridPanel_ai = new JPanel(new GridBagLayout());

    //color for text background
    Color beige = new Color(242, 235, 150);

    //misc
    Drag_and_drop d_and_d = new Drag_and_drop(Playerfield, gridPanel);
    Rotation_KeyListener keylistener = new Rotation_KeyListener();
    CustomFont font = new CustomFont();
    AI ai = new AI();


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
        battleship.putClientProperty("size", 5);
        battleship.addMouseListener(d_and_d);
        battleship.addMouseMotionListener(d_and_d);
        battleship.addKeyListener(keylistener);

        //Carrier
        imageIcon_ca = new ImageIcon(resizedCARRIER);
        carrier.setIcon(imageIcon_ca);
        carrier.setVisible(true);
        carrier.setBounds(500, 290, imageIcon_ca.getIconWidth(), imageIcon_ca.getIconHeight());
        carrier.putClientProperty("toggle", true);
        carrier.putClientProperty("size", 4);
        carrier.addMouseListener(d_and_d);
        carrier.addMouseMotionListener(d_and_d);
        carrier.addKeyListener(keylistener);


        //Cruiser
        imageIcon_cr = new ImageIcon(resizedCRUISER);
        cruiser.setIcon(imageIcon_cr);
        cruiser.setVisible(true);
        cruiser.setBounds(610, 330, imageIcon_cr.getIconWidth(), imageIcon_cr.getIconHeight());
        cruiser.putClientProperty("toggle", true);
        cruiser.putClientProperty("size", 3);
        cruiser.addMouseListener(d_and_d);
        cruiser.addMouseMotionListener(d_and_d);
        cruiser.addKeyListener(keylistener);

        //Submarine
        imageIcon_sm = new ImageIcon(resizedSUBMARINE);
        submarine.setIcon(imageIcon_sm);
        submarine.setVisible(true);
        submarine.setBounds(650, 330, imageIcon_sm.getIconWidth(), imageIcon_sm.getIconHeight());
        submarine.putClientProperty("toggle", true);
        submarine.putClientProperty("size", 3);
        submarine.addMouseListener(d_and_d);
        submarine.addMouseMotionListener(d_and_d);
        submarine.addKeyListener(keylistener);

        //Destroyer
        imageIcon_ds = new ImageIcon(resizedDESTROYER);
        destroyer.setIcon(imageIcon_ds);
        destroyer.setVisible(true);
        destroyer.setBounds(700, 350, imageIcon_ds.getIconWidth(), imageIcon_ds.getIconHeight());
        destroyer.putClientProperty("toggle", true);
        destroyer.putClientProperty("size", 2);
        destroyer.addMouseListener(d_and_d);
        destroyer.addMouseMotionListener(d_and_d);
        destroyer.addKeyListener(keylistener);

        //Ship selection
        outline.setBounds(491, 281, 300, 200);
        outline.setBorder(new LineBorder(Color.WHITE, 4));

        //Notification Popup
        popup.setBounds((game.getWidth() - 230) / 2, 150, 230, 100);
        popup.setBorder(new BevelBorder(BevelBorder.RAISED));
        popup.setLineWrap(true);
        popup.setWrapStyleWord(true);
        popup.setEditable(false);
        popup.setFocusable(false);
        popup.setOpaque(true);
        popup.setVisible(false);
        popup.setBackground(beige);
        popup.setForeground(Color.RED);
        popup.setFont(Buttons_Menu.font.getQTMilitary(24f));

        //Start
        start.setBounds(popup.getX(), popup.getY() + 110, 200, 50);
        start.setText("Ready");
        start.setFocusable(false);
        start.setFocusPainted(false);
        start.setFont(font.getQTMilitary(25f));
        start.setBackground(beige);
        start.setForeground(Color.RED);
        start.setVisible(false);
        start.addActionListener(e -> {
            Frame_Manager.gameFlow.setState(GameFlow.GameState.PLAYING);
        });
        start.setBorder(new BevelBorder(BevelBorder.RAISED));

        //Player_begins
        player_begin.setBounds(popup.getX() - 30, popup.getY() + 110, 120, 50);
        player_begin.setText("I go first!");
        player_begin.setFocusable(false);
        player_begin.setFocusPainted(false);
        player_begin.setFont(font.getQTMilitary(14f));
        player_begin.setBackground(beige);
        player_begin.setForeground(Color.RED);
        player_begin.setVisible(false);
        player_begin.addActionListener(e -> {
            Frame_Manager.gameFlow.setState(GameFlow.GameState.PLAYER_TURN);
        });
        player_begin.setBorder(new BevelBorder(BevelBorder.RAISED));

        //AI_begins
        AI_begin.setBounds(popup.getX() + 145, popup.getY() + 110, 120, 50);
        AI_begin.setText("You go first!");
        AI_begin.setFocusable(false);
        AI_begin.setFocusPainted(false);
        AI_begin.setFont(font.getQTMilitary(14f));
        AI_begin.setBackground(beige);
        AI_begin.setForeground(Color.RED);
        AI_begin.setVisible(false);
        AI_begin.addActionListener(e -> {
            Frame_Manager.gameFlow.setState(GameFlow.GameState.AI_TURN);
        });
        AI_begin.setBorder(new BevelBorder(BevelBorder.RAISED));

        //Player field
        GridBagConstraints gbc = new GridBagConstraints();
        gridPanel.setVisible(true);
        gridPanel.setOpaque(false);
        gridPanel.setBounds(85, 233, 390, 388);


        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                Playerfield[i][n] = new JButton();
                Playerfield[i][n].setPreferredSize(new Dimension(30, 30));
                Playerfield[i][n].setContentAreaFilled(false);
                Playerfield[i][n].setBorderPainted(false);
                Playerfield[i][n].setFocusable(false);
                gbc.gridx = n;
                gbc.gridy = i;

                gbc.fill = GridBagConstraints.RELATIVE;


                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                gridPanel.add(Playerfield[i][n], gbc);
            }
        }

        //AIfield
        GridBagConstraints gbc_ai = new GridBagConstraints();
        gridPanel_ai.setVisible(true);
        gridPanel_ai.setOpaque(false);
        gridPanel_ai.setBounds(845, 233, 390, 388);

        for (int i = 0; i < 10; i++) {
            for (int n = 0; n < 10; n++) {
                AIfield[i][n] = new JButton();
                AIfield[i][n].setPreferredSize(new Dimension(30, 30));
                gbc_ai.gridx = n;
                gbc_ai.gridy = i;

                gbc_ai.fill = GridBagConstraints.RELATIVE;

                gbc_ai.weightx = 1.0;
                gbc_ai.weighty = 1.0;

                gridPanel_ai.add(AIfield[i][n], gbc_ai);
            }
        }

        //Propertychangelistener changes Gamestate
        Frame_Manager.gameFlow.addPropertyChangeListener(e -> {
            if ("GameState".equals(e.getPropertyName())) {
                if (GameFlow.GameState.PLAYING.equals(e.getNewValue())) {
                    startGame();
                }
                if (GameFlow.GameState.PLAYER_TURN.equals(e.getNewValue())) {
                    playerTurn();
                }
                if (GameFlow.GameState.AI_TURN.equals(e.getNewValue())) {
                    ///
                }
                if (GameFlow.GameState.PLACEMENT.equals(e.getNewValue())) {
                    ///
                }
            }
        });


        //adds content to layout
        layeredPane.add(game, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(battleship, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(carrier, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(cruiser, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(submarine, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(destroyer, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(outline, JLayeredPane.MODAL_LAYER);
        layeredPane.add(popup, JLayeredPane.POPUP_LAYER);
        layeredPane.add(start, JLayeredPane.POPUP_LAYER);
        layeredPane.add(gridPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(gridPanel_ai, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(player_begin, JLayeredPane.POPUP_LAYER);
        layeredPane.add(AI_begin, JLayeredPane.POPUP_LAYER);


        //Settings for frame
        this.add(layeredPane);
        this.setLayout(null); // Disable layout manager
        this.setSize(1280, 720);
        this.setVisible(true); // visibility of the instance of Frame
    }


    //starting phase of game
    private void startGame() {
        //lock ships(with map of release event?.missing
        for (Map.Entry<JLabel, Point> entry : d_and_d.locationMap.entrySet()) {
            JLabel key = entry.getKey();
                key.setLocation(entry.getValue());
        }


        //remove MouseListeners
        battleship.removeMouseListener(d_and_d);
        carrier.removeMouseListener(d_and_d);
        cruiser.removeMouseListener(d_and_d);
        submarine.removeMouseListener(d_and_d);
        destroyer.removeMouseListener(d_and_d);

        //buttons
        start.setVisible(false);
        player_begin.setVisible(true);
        AI_begin.setVisible(true);

        System.out.println(Playerfield.length);

        //combining of logic board and GuiBoard
        for (int i = 0; i < Playerfield.length; i++) {
            for (int j = 0; j < Playerfield[i].length; j++) {
                if (Playerfield[i][j].getBackground().equals(Color.GREEN)) {
                    Board.PlayerField[i][j] = Board.SHIP_ON_AREA_STATE;
                }
            }
        }
        //AI placing ship and sets up board
        ai.AIShipPlacement();
        for (int i = 0; i < Board.AIField.length; i++) {
            for (int j = 0; j < Board.AIField[i].length; j++) {
                if (Board.AIField[i][j] == Board.SHIP_ON_AREA_STATE) {
                    AIfield[i][j].setBackground(Color.red);
                }
            }
        }

    }


    private void playerTurn(){

    }
}

