package Battleship;

import java.util.*;

public class AI {

    //AI board used as object field used as board
    public static Board AIfield = new Board();

    //Random Number object
    private final Random rand = new Random();

    //List for runnable methods which places ships
    public final ArrayList<Runnable> methods = new ArrayList<>();

    //object for sound for AI hit
    static Sound player = new Sound();

    //filepath for AI hit
    String sound_ai_hit = "resources/alert.wav";

    //text if player ship got hit
    String[] texts = {
            "Treffer! Unser Schiff hat Schaden genommen!",
            "Alarm! Ein feindlicher Treffer auf unserem Schiff!",
            "Direkttreffer! Feindliches Feuer hat uns erwischt!",
            "Kapitän, ein feindlicher Treffer wurde auf unserem Schiff bestätigt.",
            "Ein feindlicher Treffer wurde registriert. Schaden wird begutachtet.",
            "Ein feindliches Geschoss hat uns getroffen. Unsere Verteidigung wird auf die Probe gestellt!",
            "Kapitän, wir haben einen Treffer kassiert. Der Feind zeigt keine Gnade!"
    };



    /**
     * Random Numbers between  min and max(exclusive) to accesses field of board
     *
     * @param max maximum, exclusive
     * @param min minimum, inclusive
     * @return returns a random number between min and max - 1
     */
    public int aiRandomNumber_ranged(int max, int min) {
        return rand.nextInt(max - min) + min;
    }


    /**
     * Helps to randomize the orientation of the ship for the AI
     *
     * @return returns right, or down
     */
    private String aiRandomPlacement() {
        int i = aiRandomNumber_ranged(2, 0);
        if (i == 0) {
            return "rechts";
        }
        else {
            return "unten";
        }
    }


    public void AI_turn() {
        boolean valid_input = false;
        //int i = 0;
        while (!valid_input) {
            try {
                int input_x = aiRandomNumber_ranged(10, 0);
                int input_y = aiRandomNumber_ranged(10, 0);
                valid_input = true;
                AIfield.hit_or_miss(input_x, input_y, Control.Playerfield);
                if (AIfield.hit_checker(input_x, input_y, Control.Playerfield)) {
                    if (!(AIfield.end_game_checker(Control.Playerfield))) {
                        //int hit_x = input_x;
                        //int hit_y = input_y;
                        //i = 1;
                        GUI.gui_player();
                        player.playSound(sound_ai_hit);
                        System.out.println(texts[aiRandomNumber_ranged(7, 0)]);
                        Thread.sleep(3000);
                        AI_turn();
                    }
                }
            }
            catch (Exception e) {
                valid_input = false;
            }

        }
    }


    /**
     * checks whether there are ships on surrounding tiles
     *
     * @param ran1      random x coordinate of ship setter
     * @param ran2      random y coordinate of ship setter
     * @param size      size of ship
     * @param placement placement, either right or down
     * @return returns true if there is no  ship surrounding on none of the tile surrounding the ship
     */
    public boolean setenhancer(int ran1, int ran2, int size, String placement) {
        int r = aiRandomNumber_ranged(11, 0);
        //By testing, the best results I get, if I let this method slide once every 10 time
        if (r < 1) {
            return true;
        }

        //If user wants to set horizontal and a tile to the left is existing
        if (placement.equals("rechts")) {
            //checks if tile to the left is existing
            if (ran1 != 1) {
                //checks if ships are set to the left or right
                if (AIfield.field[ran2 - 1][ran1 + size - 1] != 1 && AIfield.field[ran2 - 1][ran1 - 2] != 1) {
                    //if ship will be placed on the bottom row
                    if (ran2 == 10) {
                        Ship.checker(ran1, ran2 - 1, size, "rechts", AIfield);
                    }
                    //if ship will be placed on the top row
                    if (ran2 == 1) {
                        Ship.checker(ran1, ran2 + 1, size, "rechts", AIfield);
                    }
                    //if ship is placed elsewhere
                    return Ship.checker(ran1, ran2 - 1, size, "rechts", AIfield) &&
                            Ship.checker(ran1, ran2 + 1, size, "rechts", AIfield);

                    }
                }
            //checks if ships are set to the right, used if tile to the left isn't existing
            else if (AIfield.field[ran2 - 1][ran1 + size - 1] != 1) {
                //if ship will be placed on the bottom row
                if (ran2 == 10) {
                    Ship.checker(ran1, ran2 - 1, size, "rechts", AIfield);
                }
                //if ship will be placed on the top row
                if (ran2 == 1) {
                    Ship.checker(ran1, ran2 + 1, size, "rechts", AIfield);
                }
                //if ship is placed elsewhere
                return Ship.checker(ran1, ran2 - 1, size, "rechts", AIfield) &&
                        Ship.checker(ran1, ran2 + 1, size, "rechts", AIfield);
            }
        }

        //If user wants to set vertical and a tile above is existing
        if (placement.equals("unten")) {
            if (ran2 != 1) {
                //checks if ships are set under or above
                if (AIfield.field[ran2 + size - 1][ran1 - 1] != 1 && AIfield.field[ran2 - 2][ran1 - 1] != 1) {
                    //if ship will be to the right edge
                    if (ran1 == 10) {
                        Ship.checker(ran1 - 1, ran2, size, "unten", AIfield);
                    }
                    //if ship will be placed to the left edge
                    if (ran1 == 1) {
                        Ship.checker(ran1 + 1, ran2, size, "unten", AIfield);
                    }
                    //if ship is placed elsewhere
                    return Ship.checker(ran1 - 1, ran2, size, "unten", AIfield) &&
                            Ship.checker(ran1 + 1, ran2, size, "unten", AIfield);
                }
            }
            //checks if ships are set under
            else if (AIfield.field[ran2 + size - 1][ran1 - 1] != 1) {
                //if ship will be placed on the right edge
                if (ran1 == 10) {
                    Ship.checker(ran1 - 1, ran2, size, "unten", AIfield);
                }
                //if ship will be placed on the left edge
                if (ran1 == 1) {
                    Ship.checker(ran1 + 1, ran2, size, "unten", AIfield);
                }
                //if ship is placed elsewhere
                else return Ship.checker(ran1 - 1, ran2, size, "unten", AIfield) &&
                        Ship.checker(ran1 + 1, ran2, size, "unten", AIfield);
            }
        }
        return false;
    }


    /**
     * Following method places the carrier on random location
     * takes a random number between 1 - 10 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    //Following methods places the ships on random locations
    private void setAIcarrier() {
        int x = aiRandomNumber_ranged(11, 1);
        int y = aiRandomNumber_ranged(11, 1);
        String placement = aiRandomPlacement();
        if (Ship.checker(x, y, 5, placement, AIfield)) {
            if (setenhancer(x, y, 5, placement)) {
                Ship.setship(x, y, "Schlachtschiff", placement, AIfield);
            }
            else {
                setAIcarrier();
            }
        }
        else{
            setAIcarrier();
        }
    }

    /**
     * Following method places the battleship on random location
     * takes a random number between 1 - 10 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    private void setAIbattleship() {
        int x = aiRandomNumber_ranged(11, 1);
        int y = aiRandomNumber_ranged(11, 1);
        String placement = aiRandomPlacement();
        if (Ship.checker(x, y, 4, placement, AIfield)) {
            if (setenhancer(x, y, 4, placement)) {
                Ship.setship(x, y, "Kreuzer", placement, AIfield);
            }
            else {
                setAIbattleship();
            }
        }
        else {
            setAIbattleship();
        }
    }

    /**
     * Following method places the cruiser/s on random location
     * takes a random number between 1 - 10 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    private void setAIcruiser() {
        int x = aiRandomNumber_ranged(11, 1);
        int y = aiRandomNumber_ranged(11, 1);
        String placement = aiRandomPlacement();
        if (Ship.checker(x, y, 3, placement, AIfield)) {
            if (setenhancer(x, y, 3, placement)) {
                Ship.setship(x, y, "Zerstörer", placement, AIfield);
            } else {
                setAIcruiser();
            }
        }
        else {
            setAIcruiser();
        }
    }

    /**
     * Following method places the destroyer on random location
     * takes a random number between 1 - 10 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    private void setAIdestroyer() {
        int x = aiRandomNumber_ranged(11, 1);
        int y = aiRandomNumber_ranged(11, 1);
        String placement = aiRandomPlacement();
        if (Ship.checker(x, y, 2, placement, AIfield)) {
            if (setenhancer(x, y, 2, placement)) {
                Ship.setship(x, y, "U-Boot", placement, AIfield);
            }
            else {
                setAIdestroyer();
            }
        }
        else {
            setAIdestroyer();
        }
    }

    /**
     * uses all setAIship  methods. Methods will be put in an arraylist and they will be called randomly
     * to further enhance randomness of placed ships
     */
    public void AIShipPlacement() {
        methods.add(this::setAIcarrier);
        methods.add(this::setAIbattleship);
        methods.add(this::setAIcruiser);
        methods.add(this::setAIcruiser);
        methods.add(this::setAIdestroyer);


        //shuffle the list of methods
        Collections.shuffle(methods);

        // Executes each method
        for (Runnable method : methods) {
            method.run();
        }
    }
}


