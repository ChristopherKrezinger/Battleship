package Battleship_1_0;

import javax.swing.*;
import java.awt.*;

//frame of the Game
public class Frame_Manager extends JFrame {
    static CardLayout cardLayout = new CardLayout();
    static JPanel cardPanel = new JPanel(cardLayout);

    ImageIcon ICON = new ImageIcon("resources/Icon.jpg");

    static GameFlow gameFlow;


    //constructor
    Frame_Manager(){
        gameFlow = new GameFlow();
        JPanel MENU = new Menu();
        cardPanel.add(MENU,"Menu");
        JPanel CONTROLS = new Controls_Menu();
        cardPanel.add(CONTROLS, "Controls");
        JPanel CREDITS = new Credits_Menu();
        cardPanel.add(CREDITS, "Credits");
        JPanel MAIN_GAME = new Main_Game();
        cardPanel.add(MAIN_GAME, "Main_Game");




        //properties of frame
        this.add(cardPanel);
        this.setIconImage(ICON.getImage()); // Icon of this Window
        this.setResizable(false); // not resizable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing of window
        this.setTitle("Battleship"); // title
        //this.setUndecorated(true);
        this.setSize(1280, 720);
        this.setVisible(true); // visibility of the instance of Frame
        this.setLocationRelativeTo(null);

        cardLayout.show(cardPanel, "Menu");
    }
}
