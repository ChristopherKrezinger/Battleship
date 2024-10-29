package Battleship_1_0;

import java.util.*;

public class AI {


    //Random Number object
    private final Random rand = new Random();

    //List for runnable methods which places ships
    public final ArrayList<Runnable> methods = new ArrayList<>();




    /**
     * Random Numbers between  min and max(exclusive) to accesses field of board
     *
     * @param max maximum, exclusive
     * @param min minimum, inclusive
     * @return returns a random number between min and max
     */
    public int aiRandomNumber_ranged(int max, int min) {
        return rand.nextInt(max - min + 1) + min;
    }


    /**
     * Helps to randomize the orientation of the ship for the AI
     *
     * @return returns right, or down
     */
    private String aiRandomPlacement() {
        int i = aiRandomNumber_ranged(3, 0);
        if (i >= 2) {
            return str1;
        }
        else {
            return str2;
        }
    }

/*
    public void AI_turn() {
        boolean valid_input = false;
        //int i = 0;
        while (!valid_input) {
            try {
                int input_x = aiRandomNumber_ranged(10, 0);
                int input_y = aiRandomNumber_ranged(10, 0);
                valid_input = true;
                Board.AIField.hit_or_miss(input_x, input_y, Board.PlayerField);
                if (Board.AIField.hit_checker(input_x, input_y, Board.PlayerField)) {
                    player.playSound(sound_ai_hit);
                    if (!(AIfield.end_game_checker(Control.Playerfield))) {
                        //int hit_x = input_x;
                        //int hit_y = input_y;
                        //i = 1;
                        GUI.gui_player();
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
    }*/
           /* if(i == 1){
              //  try {
                    int j = search_mode(hit_x, hit_y);
                    if (search_mode_helper(j, hit_x, hit_y)) {

                        if (!(AIfield.end_game_checker(Control.Playerfield))) {
                            i = 2;
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
                    break;
                case 2:

                    break;

            }
        }
    }

    public boolean search_mode_helper(int i, int input_x, int input_y){
        switch(i){
            case 1 -> AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield);
            case 2 -> AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield);
            case 3 -> AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield);
            case 4 -> AIfield.hit_checker(input_x, input_y - 1, Control.Playerfield);
            case 0 -> {
                return false;
            }
        }
        return false;
    }

    //searches for nearby ships if AI hits a ship
   public int search_mode(int input_x, int input_y){
        //If hit is in the left upper corner
        if(input_x == 0 && input_y == 0 ){
            if(Control.Playerfield.field[input_y ][input_x + 1] == 0) {
                AIfield.hit_or_miss(input_x + 1, input_y, Control.Playerfield);
                if(AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield )){
                    return 1;
                }
            }
            else if(Control.Playerfield.field[input_y + 1 ][input_x] == 0) {
                AIfield.hit_or_miss(input_x, input_y + 1, Control.Playerfield);
                if (AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield)) {
                    return 3;
                }
            }
        }
       //If hit is in the right upper corner
       if(input_x == 9 && input_y == 0 ){
           if(Control.Playerfield.field[input_y][input_x - 1] == 0) {
               AIfield.hit_or_miss(input_x - 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield )) {
                   return 2;
               }
           }
           else if(Control.Playerfield.field[input_y + 1 ][input_x] == 0) {
               AIfield.hit_or_miss(input_x, input_y + 1, Control.Playerfield);
               if (AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield)) {
                   return 3;
               }
           }

       }
       //If hit is in the left lower corner
       if(input_x == 0 && input_y == 9 ){
           if(Control.Playerfield.field[input_y][input_x + 1] == 0) {
               AIfield.hit_or_miss(input_x + 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield )){
                   return 1;
               }
           }
           else if(Control.Playerfield.field[input_y - 1 ][input_x] == 0) {
               AIfield.hit_or_miss(input_x, input_y - 1, Control.Playerfield);
               if(AIfield.hit_checker(input_x , input_y - 1, Control.Playerfield )){
                   return 4;
               }
           }

       }
       //If hit is in the right lower corner
       if(input_x == 9 && input_y == 9 ){
           if(Control.Playerfield.field[input_y][input_x - 1] == 0) {
               AIfield.hit_or_miss(input_x - 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield )) {
                   return 2;
               }
           }
           else if(Control.Playerfield.field[input_y - 1 ][input_x] == 0) {
               AIfield.hit_or_miss(input_x, input_y - 1, Control.Playerfield);
               if(AIfield.hit_checker(input_x , input_y - 1, Control.Playerfield )){
                   return 4;
               }
           }

       }
        //if hit is at the left edge
       if(input_x == 0){
            if(Control.Playerfield.field[input_y][input_x + 1] == 0) {
                AIfield.hit_or_miss(input_x + 1, input_y, Control.Playerfield);
                if(AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield )){
                    return 1;
                }
            }
            else if(Control.Playerfield.field[input_y - 1 ][input_x] == 0) {
                AIfield.hit_or_miss(input_x, input_y - 1, Control.Playerfield);
                if(AIfield.hit_checker(input_x , input_y - 1, Control.Playerfield )){
                    return 4;
                }
            }
            else if(Control.Playerfield.field[input_y + 1 ][input_x] == 0) {
                AIfield.hit_or_miss(input_x, input_y + 1, Control.Playerfield);
                if (AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield)) {
                    return 3;
                }
            }
        }
       //if hit is at the right edge
        if(input_x == 9){
            if(Control.Playerfield.field[input_y][input_x - 1] == 0) {
                AIfield.hit_or_miss(input_x - 1, input_y, Control.Playerfield);
                if(AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield )) {
                    return 2;
                }
            }
            else if(Control.Playerfield.field[input_y - 1][input_x] == 0) {
                AIfield.hit_or_miss(input_x, input_y - 1, Control.Playerfield);
                if(AIfield.hit_checker(input_x , input_y - 1, Control.Playerfield )){
                    return 4;
                }
            }
            else if(Control.Playerfield.field[input_y + 1][input_x] == 0) {
                AIfield.hit_or_miss(input_x, input_y + 1, Control.Playerfield);
                if (AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield)) {
                    return 3;
                }
            }
        }
       //if hit is at the upper edge
        if(input_y == 0){
            if(Control.Playerfield.field[input_y][input_x -1] == 0) {
                AIfield.hit_or_miss(input_x - 1, input_y, Control.Playerfield);
                if(AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield )) {
                    return 2;
                }
            }
            else if(Control.Playerfield.field[input_y][input_x + 1] == 0) {
                AIfield.hit_or_miss(input_x + 1, input_y, Control.Playerfield);
                if(AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield )){
                    return 1;
                }
            }
            else if(Control.Playerfield.field[input_y + 1 ][input_x] == 0) {
                AIfield.hit_or_miss(input_x, input_y + 1, Control.Playerfield);
                if (AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield)) {
                    return 3;
                }
            }
        }
       //if hit is at the lower edge
       if(input_y == 9){
           if(Control.Playerfield.field[input_y][input_x - 1] == 0) {
               AIfield.hit_or_miss(input_x - 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield )) {
                   return 2;
               }
           }
           else if(Control.Playerfield.field[input_y][input_x + 1] == 0) {
               AIfield.hit_or_miss(input_x + 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield )){
                   return 1;
               }
           }
           else if(Control.Playerfield.field[input_y - 1 ][input_x] == 0) {
               AIfield.hit_or_miss(input_x, input_y - 1, Control.Playerfield);
               if(AIfield.hit_checker(input_x , input_y - 1, Control.Playerfield )){
                   return 4;
               }
           }
       }
       //if hit is elsewhere
       else {
           if(Control.Playerfield.field[input_y][input_x - 1] == 0) {
               AIfield.hit_or_miss(input_x - 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x - 1, input_y, Control.Playerfield )) {
                   return 2;
               }
           }
           else if(Control.Playerfield.field[input_y ][input_x + 1] == 0) {
               AIfield.hit_or_miss(input_x + 1, input_y, Control.Playerfield);
               if(AIfield.hit_checker(input_x + 1, input_y, Control.Playerfield )){
                   return 1;
               }
           }
           else if(Control.Playerfield.field[input_y - 1 ][input_x] == 0) {
               AIfield.hit_or_miss(input_x, input_y - 1, Control.Playerfield);
               if(AIfield.hit_checker(input_x , input_y - 1, Control.Playerfield )){
                   return 4;
               }
           }
           else if(Control.Playerfield.field[input_y + 1 ][input_x] == 0) {
               AIfield.hit_or_miss(input_x, input_y + 1, Control.Playerfield);
               if (AIfield.hit_checker(input_x, input_y + 1, Control.Playerfield)) {
                   return 3;
               }
           }
       }
       return 0;
   }
*/

    /**
     * checks whether there are ships on surrounding tiles
     *
     * @param ranX      random x coordinate of ship setter
     * @param ranY      random y coordinate of ship setter
     * @param size      size of ship
     * @param placement placement, either right or down
     * @return returns true if there is no  ship surrounding on none of the tile surrounding the ship
     */
    public boolean setenhancer(int ranX, int ranY, int size, String placement) {
        int r = aiRandomNumber_ranged(50, 0);
        //By testing, the best results I get, if I let this method slide once every 50 time
        if (r < 1) {
            return true;

        }

        //If user wants to set horizontal
        if (placement.equals(str1)) {
            //checks if tile to the left or right is existing
            if (ranX != 0 && ranX + size <= 9) {
                //checks if ships are set to the left or right
                if (Main_Game.AIField_logic.field[ranX + size][ranY] != 1 && Main_Game.AIField_logic.field[ranX - 1][ranY] != 1) {
                    //if ship will be placed on the bottom row
                    if (ranY == 9) {
                        return checker(ranX, ranY - 1, size, str1);
                    }
                    //if ship will be placed on the top row
                    if (ranY == 0) {
                        return checker(ranX, ranY + 1, size, str1);
                    }
                    //if ship is placed elsewhere
                    return checker(ranX, ranY - 1, size, str1) &&
                            checker(ranX, ranY + 1, size, str1);

                }
            }
            //checks if ships are set to the right, used if tile to the left isn't existing
            if (ranX == 0) {
                if (Main_Game.AIField_logic.field[ranX + size][ranY] != 1) {
                    //if ship will be placed on the bottom row
                    if (ranY == 9) {
                        return checker(ranX, ranY - 1, size, str1);
                    }
                    //if ship will be placed on the top row
                    if (ranY == 0) {
                        return checker(ranX, ranY + 1, size, str1);
                    }
                    //if ship is placed elsewhere
                    return checker(ranX, ranY - 1, size, str1) &&
                            checker(ranX, ranY + 1, size, str1);
                }
            }
            //checks if ships are ste to the left, used if tile to the right isn't existing
            if (ranX + size > 9) {
                if (Main_Game.AIField_logic.field[ranX - 1][ranY] != 1) {
                    //if ship will be placed on the bottom row
                    if (ranY == 9) {
                        return checker(ranX, ranY - 1, size, str1);
                    }
                    //if ship will be placed on the top row
                    if (ranY == 0) {
                        return checker(ranX, ranY + 1, size, str1);
                    }
                    //if ship is placed elsewhere
                    return checker(ranX, ranY - 1, size, str1) &&
                            checker(ranX, ranY + 1, size, str1);
                }
            }
        }

        //If user wants to set vertical and a tile above is existing
        if (placement.equals(str2)) {
            if (ranY != 0 && ranY + size <= 9) {
                //checks if ships are set under or above
                if (Main_Game.AIField_logic.field[ranX][ranY - 1] != 1 && Main_Game.AIField_logic.field[ranX][ranY + size] != 1) {
                    //if ship will be to the right edge
                    if (ranX == 9) {
                        return checker(ranX - 1, ranY, size, str2);
                    }
                    //if ship will be placed to the left edge
                    if (ranX == 0) {
                        return checker(ranX + 1, ranY, size, str2);
                    }
                    //if ship is placed elsewhere
                    return checker(ranX - 1, ranY, size, str2) &&
                            checker(ranX + 1, ranY, size, str2);
                }
            }
            if (ranY == 0) {
                if (Main_Game.AIField_logic.field[ranX][ranY + size] != 1) {
                    //if ship will be placed on the bottom row
                    if (ranX == 9) {
                        return checker(ranX - 1, ranY, size, str1);
                    }
                    //if ship will be placed on the top row
                    if (ranX == 0) {
                        return checker(ranX + 1, ranY, size, str1);
                    }
                    //if ship is placed elsewhere
                    return checker(ranX - 1 , ranY, size, str1) &&
                            checker(ranX + 1, ranY, size, str1);
                }
            }
            //checks if ships are ste to the left, used if tile to the right isn't existing
            if (ranY + size > 9) {
                if (Main_Game.AIField_logic.field[ranX][ranY - 1] != 1) {
                    //if ship will be placed on the bottom row
                    if (ranX == 9) {
                        return checker(ranX - 1, ranY, size, str1);
                    }
                    //if ship will be placed on the top row
                    if (ranX == 0) {
                        return checker(ranX + 1, ranY, size, str1);
                    }
                    //if ship is placed elsewhere
                    return checker(ranX - 1, ranY, size, str1) &&
                            checker(ranX + 1, ranY, size, str1);
                }
            }
        }
        return false;
    }


    /**
     * Following method places the carrier on random location
     * takes a random number between 0 - 9 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    //Following methods places the ships on random locations
    private void setAIcarrier() {
        int x = aiRandomNumber_ranged(9, 0);
        int y = aiRandomNumber_ranged(9, 0);
        String placement = aiRandomPlacement();
        if (checker(x, y, 5, placement)) {
            if (setenhancer(x, y, 5, placement)) {
                ship_placement(x, y, 5, placement);
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
     * takes a random number between 0 - 9 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    private void setAIbattleship() {
        int x = aiRandomNumber_ranged(9, 0);
        int y = aiRandomNumber_ranged(9, 0);
        String placement = aiRandomPlacement();
        if (checker(x, y, 4, placement)) {
            if (setenhancer(x, y, 4, placement)) {
                ship_placement(x, y, 4, placement);
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
     * takes a random number between 0 - 9 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    private void setAIcruiser() {
        int x = aiRandomNumber_ranged(9, 0);
        int y = aiRandomNumber_ranged(9, 0);
        String placement = aiRandomPlacement();
        if (checker(x, y, 3, placement)) {
            if (setenhancer(x, y, 3, placement)) {
                ship_placement(x, y, 3, placement);
            }
            else {
                setAIcruiser();
            }
        }
        else {
            setAIcruiser();
        }
    }

    /**
     * Following method places the destroyer on random location
     * takes a random number between 0 - 9 for x,y coordinates
     * takes a random placement of aiRandomPlacement
     * if ship checker returns true (that's why the checker is static) it places the ship,
     * otherwise it retries to place it by call itself
     */
    private void setAIdestroyer() {
        int x = aiRandomNumber_ranged(9, 0);
        int y = aiRandomNumber_ranged(9, 0);
        String placement = aiRandomPlacement();
        if (checker(x, y, 2, placement)) {
            if (setenhancer(x, y, 2, placement)) {
                ship_placement(x, y, 2, placement);
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


    private static final String str1 = "right";
    private static final String str2 = "down";

    /**
     * Helping method checking viable spot
     * @param start_x x-variable on a 2 D grid valid arguments : 1- 10
     * @param start_y y-variable on a 2 D grid valid arguments : 1- 10
     * @param size size of the ship, it's the amount of fields the ship needs in an array
     * @param placement ship can be set to the right: starting field + size , or down: starting field + size
     * @return returns false, if ship gets out of bounds or ship will be placed on another already placed ship
     */
    public static boolean checker(int start_x, int start_y, int size, String placement) {

        //checks if the spot is already used for vertical placement and out of border placement
        if(str2.equals(placement)) {
            if(start_y + size - 1 > 9) {
                return false;
            }
            int i = start_y;
            while (i < start_y + size) {
                if (Main_Game.AIField_logic.field[start_x][i] != 0) {
                    return false;
                }
                i++;
            }
        }
        //checks if the spot is already used for horizontal placement and out of border placement
        if(str1.equals(placement)) {
            if(start_x + size - 1 > 9){
                return false;
            }
            int j = start_x;
            while (j < start_x + size) {
                if (Main_Game.AIField_logic.field[j][start_y] != 0) {
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    /**
     * Places the ship on the fields of the array
     * @param start_x x-variable on a 2 D grid
     * @param start_y y-variable on a 2 D grid
     * @param size size of the ship, it's the amount of fields the ship needs in an array
     * @param placement ship can be set to the right: starting field + size , or down: starting field + size
     */
    private static void ship_placement(int start_x, int start_y, int size, String placement) {
        if (placement.equals(str2)) {
            for (int i = 0; start_y + i < start_y + size; i++) {
                Main_Game.AIField_logic.field[start_x][start_y + i] = Board.SHIP_ON_AREA_STATE;
            }
        }
        if (placement.equals(str1)) {
            for (int i = 0; start_x + i < start_x + size; i++) {
                Main_Game.AIField_logic.field[start_x + i][start_y] = Board.SHIP_ON_AREA_STATE;
            }
        }
    }

    public void AI_searchmode(int row, int col ){
        Main_Game.PlayerField_logic.field[row][col] =2;
    }


}


