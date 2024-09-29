package Battleship;
import java.util.*;


public class Control {

    //Player board used as object field used as board
    public static Board Playerfield = new Board();

    //New scanner for inputs
    static Scanner scanner = new Scanner(System.in);

    //ai object
    public final AI ai = new AI();

    //Arraylist to place already used ships
    private final List<String> placedShips = new ArrayList<>();

    //String for new game
    private final String START = "Start";
    private final String END = "Ende";

    //sound object to get sound effect for hit
    static Sound player = new Sound();

    //sound filepath
    String sound_filepath = "resources/hit.wav";

    //text if enemy ship has been hit
    String[] texts = {
            "Treffer bestätigt! Ziel erfolgreich getroffen.",
            "Direct hit, Kapitän! Feind in Sicht!",
            "Treffer! Feindkontakt bestätigt!",
            "Ziel erreicht. Der Feind wird den Verlust spüren.",
            "Das Ziel wurde erfolgreich neutralisiert!",
            "Kapitän, wir haben einen Treffer erzielt!",
            "Ziel erfolgreich lokalisiert und neutralisiert."
    };



    /**
     * Used to convert the user input for the y-coordinate to an int, so it can be used to place a ship
     * casts the String to a character and cast the character to the ascii value (int)
     *
     * @param letter A-J, the input of the user for y coordinate
     * @return the ascii value of the character of the String - 64, since A is 65.
     * and a number between 1 - 10 is needed for placing the ship
     */
    public static int converter(String letter) {
        char value = letter.charAt(0);
        return (int) value - 64;
    }


    /**
     * Handles user input, catches exceptions
     */
    public void inputs_ship_placement() {
        //loops until user puts in valid arguments

        //Valid input checker
        boolean valid_input = false;
        while (!valid_input) {
            try {
                System.out.println("Bitte platzieren Sie Ihr Schiff:");
                //arguments of user
                int input_start_x = scanner.nextInt();
                String input_start_y = scanner.next();
                String input_ship = scanner.next();
                String input_placement = scanner.next();

                //ends loop
                valid_input = true;
                //so the "Zerstörer" can be placed twice
                if (input_ship.equals("Zerstörer")) {
                    if (Collections.frequency(placedShips, "Zerstörer") != 2) {
                        Ship.setship(input_start_x, converter(input_start_y), input_ship, input_placement, Playerfield);
                        placedShips.add(input_ship);
                    } else {
                        System.out.println("Sie haben den Zerstörer bereits 2 mal platziert. " + "Sie haben bereits folgende Schiffe benutzt: "
                                + placedShips);
                        valid_input = false;
                    }
                }
                else if (!(placedShips.contains(input_ship))) {
                    Ship.setship(input_start_x, converter(input_start_y), input_ship, input_placement, Playerfield);
                    placedShips.add(input_ship);
                }
                else {
                    System.out.println("\nDas Schiff " + input_ship + " wurde bereits benutzt. Sie haben bereits folgende Schiffe benutzt:\n "
                            + placedShips);
                    valid_input = false;
                }
            } catch (InputMismatchException e) {
                //exception if player inputs wrong sequence of arguments
                System.out.println("Bitte achten Sie auf die korrekte Reihenfolge ihrer Eingabe. Beispielhafte korrekte Eingabe:\n2 B Zerstörer rechts");
                scanner.nextLine();
            }
            //catches other exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                valid_input = false;
            }
        }
        GUI.gui_player();
    }


    /**
     * handles the turn of the player
     */
    public void turn_player() {
        boolean valid_input = false;
        while (!valid_input) {
            try {
                System.out.println("Bitte geben Sie Ihre Koordinaten zum Schießen ein Kapitän: ");
                int input_x = scanner.nextInt() - 1;
                String input_y = scanner.next();

                //ends loop
                valid_input = true;
                Playerfield.hit_or_miss(input_x, converter(input_y) - 1, AI.AIfield);
                if (Playerfield.hit_checker(input_x, converter(input_y) - 1, AI.AIfield)) {
                    if (!(Playerfield.end_game_checker(AI.AIfield))) {
                        player.playSound(sound_filepath);
                        System.out.println(texts[ai.aiRandomNumber_ranged(7, 0)]);
                        Thread.sleep(3000);
                        GUI.gui();
                        turn_player();
                    }
                }
            }
            catch (InputMismatchException e) {
                //exception if player inputs wrong sequence of arguments
                System.out.println("Bitte achten Sie auf die korrekte Reihenfolge ihrer Eingabe. Beispielhafte korrekte Eingabe:\n8 H");
                scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                valid_input = false;
            }
        }
    }


    /**
     * the main game. handles the whole game
     */
    public void game() {
        GUI.howtoplay();
        Main.newgame(Playerfield);
        Main.newgame(AI.AIfield);
        scanner.nextLine();
        ai.AIShipPlacement();
        GUI.gui_player();
        for (int i = 0; i < 5; i++) {
            inputs_ship_placement();
        }
        System.out.println("Alle Mann an Deck! Lasst uns die Schlacht beginnen und die Schiffe versenken!");
        try {
            Thread.sleep(1000);
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1");
            Thread.sleep(1000);
            System.out.println("Los!!!!!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int i = ai.aiRandomNumber_ranged(2, 0);
        if (i == 0) {
            GUI.gui();
            System.out.println("Sie beginnen\n");
            while (!(Playerfield.end_game_checker(Playerfield) || Playerfield.end_game_checker(AI.AIfield))) {
                turn_player();
                if(!Playerfield.end_game_checker(AI.AIfield)) {
                    ai.AI_turn();
                    GUI.gui();
                }
            }
        }
        if (i == 1) {
            System.out.println("\nIhr Gegner beginnt\n");
            while (!(Playerfield.end_game_checker(Playerfield) || Playerfield.end_game_checker(AI.AIfield))) {
                ai.AI_turn();
                GUI.gui();
                if(!Playerfield.end_game_checker(Playerfield)) {
                    turn_player();

                }
            }
        }
        GUI.gui();
    }

    //end game description
    public void end_game() {
        if (Playerfield.end_game_checker(AI.AIfield)) {
            System.out.println("Kapitän, alle gegnerischen Schiffe sind versenkt. Dieser Sieg geht in die Geschichte ein!");
        }
        if (Playerfield.end_game_checker(Playerfield)) {
            System.out.println("Leider sind Sie während der Schlacht gefallen. Es sind keine Schiffe unter Ihrem Kommando übrig.");
        }
        System.out.println("Geben Sie Neues Spiel ein, um ein neues Spiel zu starten. Geben Sie Ende ein, um das Spiel zu beenden");

        boolean valid_input = false;

        while (!valid_input) {
            try {
                String input_new_game_or_exit = scanner.next();
                if (input_new_game_or_exit.equals(START)) {
                    valid_input = true;
                    placedShips.clear();
                    ai.methods.clear();
                    game();
                }

                if (input_new_game_or_exit.equals(END)) {
                    valid_input = true;

                }
                else {
                    System.out.println("Geben Sie Neues Spiel ein, um ein neues Spiel zu starten. Geben Sie Ende ein, um das Spiel zu beenden");
                    scanner.nextLine();
                }
            }
            catch (InputMismatchException e) {
                //exception if player inputs wrong sequence of arguments
                System.out.println("Geben Sie Neues Spiel ein, um ein neues Spiel zu starten. Geben Sie Ende ein, um das Spiel zu beenden");
                scanner.nextLine();
            }
            //catches other exceptions
            catch (Exception e) {
                System.out.println(e.getMessage());
                valid_input = false;

            }
        }
    }
}





