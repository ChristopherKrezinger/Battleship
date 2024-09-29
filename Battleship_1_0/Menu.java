package Battleship_1_0;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//main menu
public class Menu extends JPanel {

    static final ImageIcon BACKGROUND = new ImageIcon("resources/Background.jpg");
    final ImageIcon TITLE = new ImageIcon("resources/Title.png");



    JLabel background = new JLabel();
    JLabel title = new JLabel();

    static final int buttonWidth = 200;
    static final int buttonHeight = 50;
    static final int x_position_button = (1280 - buttonWidth) / 2;
    static final int y_position_button = (720 - buttonHeight) / 2;

    //constructor
    public Menu(){

        //buttons
        Buttons_Menu new_game = new Buttons_Menu(x_position_button, y_position_button - 100, "New Game", (e) -> {
            fadeOutAnimation("Main_Game");
        });
        Buttons_Menu controls = new Buttons_Menu(x_position_button, y_position_button , "Controls", (e) -> {
            Frame_Manager.cardLayout.show(Frame_Manager.cardpanel, "Controls");});
       Buttons_Menu credits = new Buttons_Menu(x_position_button,y_position_button + 100, "Credits", (e) -> {
           Frame_Manager.cardLayout.show(Frame_Manager.cardpanel, "Credits");});
        Buttons_Menu exit = new Buttons_Menu(x_position_button, y_position_button + 200, "Exit", (e) ->{
            System.exit(0);
        });


        //properties for title
        title.setIcon(TITLE);
        title.setVisible(true);
        title.setBounds((1280- TITLE.getIconWidth())/2 ,-100, 960, 540);

        //properties for background
        background.setIcon(BACKGROUND);
        background.setVisible(true);
        background.setBounds(0, 0, 1280, 720);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0, 1280,720);

        //layout
        layeredPane.add(new_game.button, JLayeredPane.DRAG_LAYER);
        layeredPane.add(controls.button, JLayeredPane.DRAG_LAYER);
        layeredPane.add(credits.button, JLayeredPane.DRAG_LAYER);
        layeredPane.add(exit.button, JLayeredPane.DRAG_LAYER);
        layeredPane.add(title, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);


        //settings for frame
        this.add(layeredPane);
        this.setLayout(null); // Disable layout manager
         this.setBackground(Color.BLACK);
        this.setSize(1280, 720);
        this.setVisible(true); // visibility of the instance of Frame


    }

    // needed for fade out
    float alpha = 1.0f;

    /**
     * plays fade out animation
     * @param nextPanel next panel shown after fade out animation
     */
    public void fadeOutAnimation(String nextPanel) {
        JPanel currentPanel = (JPanel) Frame_Manager.cardpanel.getComponent(0);
        Timer timer = new Timer(50, new ActionListener() {

            //starts timer and repaints with lowering alpha
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.05f; // Reduziere die Transparenz
                if (alpha <= 0.0f) {
                    // alpha reaches 0, stops timer and shows nextPanel
                    Frame_Manager.cardLayout.show(Frame_Manager.cardpanel, nextPanel);
                    ((Timer) e.getSource()).stop();
                } else {
                    currentPanel.repaint();
                }
            }
        });

        timer.start();
    }

    //repaints component and sets alphaComposite over component
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

    }
}
