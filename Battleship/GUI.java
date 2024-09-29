package Battleship;
public class GUI {
    //If there isn't a real GUI implemented, I ran out of time so for now it is just a simple
    //presentation of the game in the command line

    /**
     * prints the board to visualize player board and the coordinates
     * empty water tile = ░, ship = ■, miss = Ø, hit = ☼
     * even shows the fields
     * @param board corresponding board to design will be changed to player
     */
    public static void design_of_board(Board board) {
        int a = 1;
        char letter = 'A';
        for (int i = 0; i <= 9; i++) {
            System.out.print("\n");
            for (int j = 0; j <= 9; j++) {
                if (board.field[i][j] == 0) {
                    System.out.print("░" + " ");
                }
                if (board.field[i][j] == 1) {
                    System.out.print("■" + " ");
                }
                if (board.field[i][j] == 2) {
                    System.out.print("Ø" + " ");
                }
                if (board.field[i][j] == 3) {
                    System.out.print("҉" + " ");
                }

            }
            System.out.print(letter);
            letter++;
        }
        System.out.print("\n");
        for (int i = 0; i <= 9; i++) {
            System.out.print(a + " ");
            a++;

        }
    }
    public static void design_of_board_ai(Board board) {
        int a = 1;
        char letter = 'A';
        for (int i = 0; i <= 9; i++) {
            System.out.print("\n");
            for (int j = 0; j <= 9; j++) {
                if (board.field[i][j] == 0 || board.field[i][j] == 1) {
                    System.out.print("░" + " ");
                }
                if (board.field[i][j] == 2) {
                    System.out.print("Ø" + " ");
                }
                if (board.field[i][j] == 3) {
                    System.out.print("҉" + " ");
                }

            }
            System.out.print(letter);
            letter++;
        }
        System.out.print("\n");
        for (int i = 0; i <= 9; i++) {
            System.out.print(a + " ");
            a++;

        }
    }



    /**
     * Displays the whole game
     */
        public static void gui(){
            System.out.println("\n" + "Spieler:");
            GUI.design_of_board(Control.Playerfield);
            System.out.println("\n" + "Gegner:");
            GUI.design_of_board_ai(AI.AIfield);
            System.out.println("\n\n");
        }


        public static void gui_player(){
            System.out.println("\n" + "Spieler:");
            GUI.design_of_board(Control.Playerfield);
            System.out.println("\n\n");
        }



    public static void howtoplay(){
        System.out.println(
                """

                        \s
                        Vielen Dank, dass Sie sich dafür entschieden haben, meine Version von Schiffe versenken zu spielen\s
                        Das Spielprinzip ist simpel. Sie versuchen alle gegnerische Schiffe \
                        zu versenken. Es wird abwechselnd geschossen. Ziel ist es alle gegnerischen Schiffe zu versenken.\s
                        Treffen Sie ein gegnerisches Schiff sind allerdings \
                        wieder Sie dran. Das selbe gilt für ihren Gegner. Trifft dieser eines Ihrer Schiffe, ist er wieder dran.
                        \s
                        Es gibt 4 verschiedene Schiffe:
                        \s
                        Schlachtschiff  -  5 Felder
                        Kreuzer         -  4 Felder
                        2x Zerstörer    -  3 Felder
                        U-Boot          -  2 Felder
                        \s
                        Bitte achten Sie auf korrekte Eingaben. Das Feld besteht aus 10 Zeilen und 10 Reihen, beginnend von links oben.
                        Steuerung:
                        Um die Schiffe zu platzieren geben Sie als erstes einen Zahl zwischen 1 - 10 für die Zeile, einen Buchstaben von A-J für die Reihe ein.\s
                        Geben Sie nun das Schiff an, welches Sie platzieren wollen. Denken Sie dran, dass Sie jedes Schiff einmal platzieren können, den Zerstörer allerdings zwei mal.\s
                        Nun geben Sie Ihre Ausrichtung des Schiffes an. Beachten Sie, dass ihr Schiff immer von "links nach rechts" oder von "oben nach unten" platziert wird.\s
                        Geben Sie für die Platzierung \
                        horizontal rechts ein und für die vertikale Platzierung unten ein.\s
                        \s
                        Eine korrekte Eingabe könnte wie folgt aussehen:\s
                        5 D Schlachtschiff unten.\s
                        \s
                        Nun kann das Spiel beginnen.\s
                        Um ein neues Spiel zu starten, drücken Sie die Enter-taste
                        Sie sehen oben Ihr eigenes Feld mit den Platzierten Schiffen. Darunter das gegnerische Feld, natürlich haben Sie keine Sicht auf die gegnerischen Schiffe. \
                        Wasser wird als ░ dargestellt.\s
                        Schießen Sie ins Leere, wird dies mit Ø gekennzeichnet. Ihre Schiffe sehen Sie auf ihrem Feld gekennzeichnet mit ■. Treffen Sie ein gegnerisches Schiff, wird dies mit ҉ gekennzeichnet.\s
                        Zum schießen geben Sie wie beim Platzieren der Schiffe einfach zuerst eine Zahl zwischen 1-10 ein, gefolgt von einem Buchstaben zwischen A-J ein.\s
                        Sie haben gewonnen, sobald Sie alle Schiffe versenkt haben. \
                        Verloren haben Sie, falls alle Ihre Schiffe versenkt wurden.
                        Es wird per Zufall entschieden, wer anfängt. \s
                        \s
                        Viel Spaß beim Spielen :)""");
    }

}


