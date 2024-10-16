package Battleship_1_0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

//keyListener for rotating the ship
public class Rotation_KeyListener implements KeyListener {
    public static boolean toggle = true;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //action for pressing Space
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            JLabel label = (JLabel) e.getSource();
            boolean toggle = (boolean) label.getClientProperty("toggle");
            ImageIcon icon = (ImageIcon) label.getIcon();
            if (toggle) {
                //if Space is not already pressed, rotate image by 90 degrees
                ImageIcon rotatedIcon = rotateImage(icon, 90);
                label.setIcon(rotatedIcon);
                label.setBounds(label.getX(), label.getY(), icon.getIconHeight(), icon.getIconWidth());

            } else {
                //if Space already pressed rotate image back by -90 degrees
                ImageIcon rotatedIcon = rotateImage(icon, - 90);
                label.setIcon(rotatedIcon);
                label.setBounds(label.getX(), label.getY(), icon.getIconHeight(), icon.getIconWidth());

            }
            //updates toggle status
            label.putClientProperty("toggle", !toggle);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



    public ImageIcon rotateImage(ImageIcon icon, int angle) {
        //convert the ImageIcon into a BufferedImage
        BufferedImage bufferedImage = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        //create a new empty BufferedImage for the rotated image
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage rotatedImage = new BufferedImage(h, w, bufferedImage.getType());

        //transformation setup for rotation
        AffineTransform transform = new AffineTransform();
        transform.translate(h / 2.0, w / 2.0); //move the origin to the center of the image
        transform.rotate(Math.toRadians(angle)); //rotate by the specified angle (in degrees)
        transform.translate(-w / 2.0, -h / 2.0); //move the origin back after rotation

        //apply the rotation
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        op.filter(bufferedImage, rotatedImage);

        //create a new ImageIcon from the rotated image
        return new ImageIcon(rotatedImage);
    }
}
