package Battleship_1_0;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitAnimation extends JLabel implements ActionListener {
    //Variables
    private final ImageIcon hitAnimationSheet;
    private final int frameWidth;
    private final int frameHeight;
    private int currentFrame;
    private final int totalFrames;
    private final Timer animationTimer;

    public HitAnimation(){
        hitAnimationSheet = new ImageIcon("resources/Impact_1.png");
        frameWidth = 192;
        frameHeight = 192;
        currentFrame = 1;
        totalFrames = 8;
        animationTimer = new Timer(180, this);
        this.setBorder(new LineBorder(Color.RED));


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Quellkoordinaten: Der Frame, den du zeichnen möchtest
        int sx1 = currentFrame % 5 * frameWidth;
        int sy1 = 0;
        int sx2 = sx1 + frameWidth;           // Ende des aktuellen Frames (rechts)
        int sy2 = sy1 + frameHeight;          // Höhe bleibt konstant
        if(currentFrame > 4) {
            sy1 = frameHeight;
            sy2 = frameHeight * 2;
        }


        g.drawImage(hitAnimationSheet.getImage(), 0, 0, 80, 80, sx1, sy1, sx2, sy2,  this);
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % totalFrames;
        repaint();
        if(currentFrame == 0){
            animationTimer.stop();
            this.setVisible(false);
        }

    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(30,30);
    }
    public void drawAnimation(){
        if (!animationTimer.isRunning()) {
            animationTimer.start();
            this.setVisible(true);
        }
    }
}
