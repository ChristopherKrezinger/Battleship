package Battleship_1_0;

import javax.swing.*;
import java.awt.*;


//Credit screen
public class Credits_Menu extends JPanel {
    JLayeredPane layeredPane = new JLayeredPane();
    JTextArea credits = new JTextArea();
    JLabel background = new JLabel();
    JTextArea credits_background = new JTextArea();



    //Constructor
    Credits_Menu(){
        Buttons_Menu back = new Buttons_Menu(Menu.x_position_button, Menu.y_position_button + 200, "Back", (e) -> {
            Frame_Manager.cardLayout.show(Frame_Manager.cardPanel, "Menu");
        });

        //properties for background
        background.setIcon(Menu.BACKGROUND);
        background.setVisible(true);
        background.setBounds(0, 0, 1280, 720);


        //properties for text displaying credits
        credits.setText("This Game is made by Christopher Tibor Krezinger" +
                "\nEverything is royalty free and some work is edited in Blender" +
                "\nSounds: qubodup, LordSonny. All artist can be found on pixabay.com" +
                "\nBackground Music: Squeeb, SoulTV" +
                "\nTitle: Christopher Tibor Krezinger (with Canva)" +
                "\nImages: (Menu Background) DeepAI " +
                "\nFont: Qualitype" +
                "\nSpecial thanks to SoulTV for creating the main menu theme for this project :)" +
                "\nFor more information, visit: [Website]" +
                "\nCopyright © 2024 Christopher Tibor Krezinger. All rights reserved.");
        credits.setLineWrap(true);
        credits.setWrapStyleWord(true);
        credits.setEditable(false);
        credits.setBounds((1280 - 1150) /2 ,50, 1150, 600);
        credits.setFocusable(false);
        credits.setOpaque(false);
        credits.setForeground(Color.BLACK);
        credits.setFont(Buttons_Menu.font.getQTMilitary(25f));


        //layoutmanager
        layeredPane.setBounds(0, 0, 1280, 720);
        layeredPane.add(back.button, JLayeredPane.DRAG_LAYER);
        layeredPane.add(credits, JLayeredPane.MODAL_LAYER);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(credits_background, JLayeredPane.PALETTE_LAYER);


        //Settings for panel
        this.add(layeredPane);
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setVisible(true);

    }
}
