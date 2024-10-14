package Battleship_1_0;

import javax.swing.*;
import java.awt.*;


//Controls screen
public class Controls_Menu extends JPanel {
    JLayeredPane layeredPane = new JLayeredPane();
    JTextArea how_to_play = new JTextArea();
    JLabel background = new JLabel();


    //Constructor
    Controls_Menu() {
        Buttons_Menu back = new Buttons_Menu(Menu.x_position_button, Menu.y_position_button + 200, "Back", (e) -> {
            Frame_Manager.cardLayout.show(Frame_Manager.cardPanel, "Menu");
        });

        //properties of background image
        background.setIcon(Menu.BACKGROUND);
        background.setVisible(true);
        background.setBounds(0, 0, 1280, 720);


        //properties of text displaying controls
        how_to_play.setText("aslökdjalöksjdlökasdjlökasjdlajdlsköjdsalköjdöjas/n" +
                "äöaklsjdöälaksdöälaksdöläakdöläaksdöälaskdöläkasdölkad" +
                "öäalskdöäalskdaöäsldkaslöädkasöäldkasäöldkasäödlksdäölkdöälaskdöäladköalksdöälkasd" +
                "äa#ölskd#äöasldä#öasldä#öasldäöasldäöasdlasäöd#lasdäöasldä#öasldasäöd#laäsdöldäöa#ld#ä" +
                "asäöldkaöäldkasölädkaölädkasöäldkaölädkadlöskasldökdlöäakdlösakdlökalödskalsödkalsöädklödsklö");
        how_to_play.setLineWrap(true);
        how_to_play.setWrapStyleWord(true);
        how_to_play.setEditable(false);
        how_to_play.setBounds((1280 - 1150) /2 ,50, 1150, 300);
        how_to_play.setFocusable(false);
        how_to_play.setOpaque(false);
        how_to_play.setForeground(new Color(31, 201, 8));
        how_to_play.setFont(Buttons_Menu.font.getQTMilitary(25f));





        //Layoutmanager
        layeredPane.setBounds(0, 0, 1280, 720);
        layeredPane.add(back.button, JLayeredPane.DRAG_LAYER);
        layeredPane.add(how_to_play, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);


        //Settings for panel
        this.add(layeredPane);
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setVisible(true);

    }
}
