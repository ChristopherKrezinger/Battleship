package Battleship_1_0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//JButtons for the menu
public class Buttons_Menu {


    JButton button = new JButton();
    Sound sound = new Sound();
    String hover_over_sound = "resources/Menu_Hover_over.wav";
    String on_click = "resources/Menu_On_click.wav";
    static CustomFont font = new CustomFont();


    /**
     * Constructor for the menu buttons
     * @param x placement on x-coordinate
     * @param y placement on y-coordinate
     * @param text text to display
     * @param e to change specific action on different buttons
     */
    Buttons_Menu(int x, int y, String text, ActionListener e) {


        //settings for the Jbuttons in the menu
        button.setRolloverEnabled(true);

        //Effects of the Jbuttons on MouseEvent
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setFont(font.getQTMilitary(26f));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setFont(font.getQTMilitary(25f));
                sound.playSound(on_click);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.LIGHT_GRAY);
                sound.playSound(hover_over_sound);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });

        //Properties of the Jbuttons
        button.setBounds(x, y, 200, 50);
        button.setText(text);
        button.setFocusable(false);
        button.setFont(font.getQTMilitary(25f));
        button.setForeground(Color.WHITE);
        button.addActionListener(e);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

    }

}
