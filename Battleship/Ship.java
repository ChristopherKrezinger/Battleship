package Battleship;
public class Ship {


  //Boat size, each number corresponds to an area in the field
  private static final int CARRIER_SIZE = 5;
  private static final int BATTLESHIP_SIZE = 4;
  private static final int CRUISER_SUBMARINE_SIZE = 3;
  private static final int DESTROYER_SIZE = 2;
  private static final String str1 = "rechts";
  private static final String str2 = "unten";


  /**
   * Helping method checking viable spot
   * @param start_x x-variable on a 2 D grid valid arguments : 1- 10
   * @param start_y y-variable on a 2 D grid valid arguments : 1- 10
   * @param size size of the ship, it's the amount of fields the ship needs in an array
   * @param placement ship can be set to the right: starting field + size , or down: starting field + size
   * @param board which board is used. Either player or Ai
   * @return returns false, if ship gets out of bounds or ship will be placed on another already placed ship
   */
  public static boolean checker(int start_x, int start_y, int size, String placement, Board board) {
//checks boarders
    if (start_x <= 0 || start_x > board.field.length || start_y <= 0 || start_y > board.field.length ||
            start_x + size <= 0 || start_x + size > board.field.length || start_y + size <= 0 || start_y + size > board.field.length) {
      return false;
    }
    //checks if the spot is already used for horizontal placement
    if(str2.equals(placement)) {
      int i = start_y;
      while (i < start_y + size) {
        if (board.field[i - 1][start_x - 1] != 0) {
          return false;
        }
        i++;
      }
    }
    //checks if the spot is already used for vertical placement
    if(str1.equals(placement)) {
      int j = start_x;
      while (j < start_x + size) {
        if (board.field[start_y - 1][j - 1] != 0) {
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
   * @param board which board is used. Either player or Ai
   */
  private static void ship_placement(int start_x, int start_y, int size, String placement, Board board) {
    if (placement.equals(str2)) {
      for (int i = 0; start_y + i < start_y + size; i++) {
        board.field[start_y - 1 + i][start_x - 1] = Board.SHIP_ON_AREA_STATE;
      }
    }
    if (placement.equals(str1)) {
      for (int i = 0; start_x + i < start_x + size; i++) {
        board.field[start_y - 1][start_x - 1 + i] = Board.SHIP_ON_AREA_STATE;
      }
    }
  }



  /**
   * Places the ship on the fields of the array
   * @param start_x x-variable on a 2 D grid
   * @param start_y y-variable on a 2 D grid
   * @param ship used to determine user/(Ai) input of ship and match it with its corresponding size
   * @param placement ship can be set to the right: starting field + size , or down: starting field + size
   * @param board which board is used. Either player or Ai
   */
  public static void setship(int start_x, int start_y, String ship, String placement, Board board) {
    //Exception, if player has a typo with the placement
    if(!placement.equals(str1) && !placement.equals(str2)){
      throw new Illegal_Argument_exception("Orientierung falsch eingegeben!" + "\n" + "Bitte geben Sie für die Ausrichtung: \"rechts\" , oder \"unten\" ein!" );
    }

    //Matches ship String, sets i to the corresponding size
    int i = switch (ship) {
        case "Schlachtschiff" -> CARRIER_SIZE;
        case "Kreuzer" -> BATTLESHIP_SIZE;
        case "Zerstörer" -> CRUISER_SUBMARINE_SIZE;
        case "U-Boot" -> DESTROYER_SIZE;
        default ->
                //Exception if the player got a typo for ship argument
                throw new Illegal_Argument_exception("""
                        Bitte überprüfen Sie ihre Eingabe des Schiffes!
                        Sie können aus folgenden Schiffen wählen:
                        1x "Schlachtschiff"
                        1x "Kreuzer"
                        2x "Zerstörer"
                        1x "U-Boot\"""");
    };

    //Debugging tool for the checker:
    // System.out.println("Checker Result: " + checker(start_x, start_y, i, placement, board));

    //If the checker returns true, it will set the ship. i = size of the ship
    if (checker(start_x, start_y, i, placement, board)){
      Ship.ship_placement(start_x, start_y, i, placement, board);
    }

    //Exception, if the checker returns false and so the player either inputted a coordinates out of bounds, or ship on ship
    else if(board == Control.Playerfield) {
      throw  new Illegal_Argument_exception("Entweder ihr Schiff ist außerhalb des Spielfeldes, oder ihre Schiffe überkreuzen sich!" + "\n" + "Bitte überprüfen Sie ihre Startkoordinaten!");
    }

  }
}





