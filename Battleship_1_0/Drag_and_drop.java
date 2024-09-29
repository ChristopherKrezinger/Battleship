package Battleship_1_0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


//handels inputs for ship placement
public class Drag_and_drop implements MouseListener, MouseMotionListener {
    Point startingPoint;
    private final JButton[][] buttons;  // Das gesamte Panel mit den Buttons
    private final JPanel gridPanel;

    public Drag_and_drop(JButton[][] buttons, JPanel gridPanel) {
        this.buttons = buttons; // Instanzieren mit dem Panel
        this.gridPanel = gridPanel;
    }


    public int get_x() {
        return startingPoint.x;
    }

    public int get_y() {
        return startingPoint.y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //gets pressed Jlabel and sets startingPoint
        JLabel label = (JLabel) e.getSource();
        startingPoint = SwingUtilities.convertPoint(label, e.getPoint(), label.getParent());
        label.setFocusable(true);
        label.requestFocusInWindow();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel label = (JLabel) e.getSource();
        startingPoint = null;
        label.setFocusable(false);

        //gets the current point of the label inside gridPanel
        Point labelLocation = SwingUtilities.convertPoint(label.getParent(), label.getLocation(), gridPanel);
        Dimension labelSize = label.getSize(); // Größe des Schiffs

        //calculates the center of the ship at current point
        int centerX = labelLocation.x + labelSize.width / 2;
        int centerY = labelLocation.y + labelSize.height / 2;


        //calculates the Jbutton in the middle of the ship
        JButton centerButton = getButtonAtPosition(centerX, centerY);

        //Ensures in bound placement
        if (inBound(label, gridPanel)) {

            //currently used for ship with size % 2 = 1
            if (centerButton != null) {

                //gets location of center button
                Point centerLocation = centerButton.getLocation();

                //currently used for ship with size % 2 = 1
                if ((int) label.getClientProperty("size") % 2 != 0) {



                    //locks ship in place unfortunately no more precise way found for calculation
                    label.setLocation(centerLocation.x + 98 - label.getWidth() / 2,
                            centerLocation.y + 248 - label.getHeight() / 2);

                    //highlights the buttons under locked ship
                    highlightButtonsUnderLabel(label);
                }
            }
            else {

                //will be used for ship with size % 2 = 0
                        highlightButtonsUnderLabel(label);

                }
            }
        }



    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //gets pressed Jlabel and sets startingPoint
        JLabel label = (JLabel) e.getSource();

        //checks whether startingPoint is set
        if (startingPoint != null) {

            //converts the mouse event point from the label coordinate space to the parent container coordinate space.
            Point location = SwingUtilities.convertPoint(label, e.getPoint(), label.getParent());

            //checks if the new point is within the bounds of the label parent container.
            if (label.getParent().getBounds().contains(location)) {

                //retrieves the current location of the label.
                Point newLocation = label.getLocation();

                //moves the label position by the difference between the new location and the startingPoint.
                newLocation.translate(location.x - startingPoint.x, location.y - startingPoint.y);

                //ensures label does not move outside the boundaries of the parent container
                newLocation.x = Math.max(newLocation.x, 0);
                newLocation.y = Math.max(newLocation.y, 0);
                newLocation.x = Math.min(newLocation.x, label.getParent().getWidth() - label.getWidth());
                newLocation.y = Math.min(newLocation.y, label.getParent().getHeight() - label.getHeight());
                label.setLocation(newLocation);

                //sets new startingPoint for label
                startingPoint = location;  // Aktualisieren von startingPoint


            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    /**
     * highlights the button under the placed ship
     * @param label used label for placement
     */
    private void highlightButtonsUnderLabel(JLabel label) {

        //calculates position relative to gridPanel
        Point labelLocation = SwingUtilities.convertPoint(label.getParent(), label.getLocation(), gridPanel);

        //creates rectangle at position corresponding to the size of the label
        Rectangle labelBounds = new Rectangle(labelLocation, label.getSize());

        //iterates through all components of the gridPanel
        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;

                //calculates the position of the button and creates rectangle at location
                Point buttonLocation = button.getLocation();
                Rectangle buttonBounds = new Rectangle(buttonLocation, button.getSize());



                    //checks whether label and button intersect
                    if (labelBounds.intersects(buttonBounds)) {
                        //sets button green
                        button.setBackground(Color.GREEN);
                    } else {
                        //if not intersecting, resets color
                        button.setBackground(null);
                    }
            }
        }
    }


    /**
     * returns the Jbutton at the point
     * @param x x-coordinate of position to calculate
     * @param y y-coordinate of position to calculate
     * @return returns desired Jbutton
     */
    private JButton getButtonAtPosition(int x, int y) {
        //iterates through all Jbuttons on field
        for (Component comp : gridPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;

                //calculates the position of the button and creates rectangle at location
                Point buttonLocation = button.getLocation();
                Dimension buttonSize = button.getSize();

                //checks if point is within the bounds of the Jbutton
                if (x >= buttonLocation.x && x <= buttonLocation.x + buttonSize.width &&
                        y >= buttonLocation.y && y <= buttonLocation.y + buttonSize.height) {
                    return button;
                }
            }
        }
        return null;
    }
    //private void placement() ... if get ButtonAt Position == null


    /**
     * checks if label is within bounds of panel. Used, so user can't place parts of the ship outside the field
     * @param label used label
     * @param panel panel in use
     * @return if the label is within the bounds of the panel
     */
    private boolean inBound(JLabel label, JPanel panel) {

        //gets the boundaries of the label and panel
        Rectangle labelBounds = label.getBounds();
        Rectangle panelBounds = panel.getBounds();

        //checks if label is completely inside the panel
        return panelBounds.contains(labelBounds.x, labelBounds.y) &&
                panelBounds.contains(labelBounds.x + labelBounds.width, labelBounds.y) &&
                panelBounds.contains(labelBounds.x, labelBounds.y + labelBounds.height) &&
                panelBounds.contains(labelBounds.x + labelBounds.width, labelBounds.y + labelBounds.height);
    }
}



