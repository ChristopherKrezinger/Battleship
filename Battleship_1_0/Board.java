package Battleship_1_0;
public class Board {
    //The array used to for the board

    public final int[][] field = new int[10][10];



    //Default state for an area in board
    public static final int DEFAULT_AREA_STATE = 0;
    //Ship on field state
    public static final int SHIP_ON_AREA_STATE = 1;
    //Missed field state
    public static final int MISS_AREA_STATE = 2;
    //Hit field state
    public static final int HIT_AREA_STATE = 3;
    //Maximum number of fields with hit state needed to end the game
    public static int END_GAME_STATE = 17;




    /**
     * Setter to change states on specific area, for hit and misses
     * if case used just to prevent bugs
     * @param row x coordinate of field in array
     * @param col y coordinate of field in array
     * @param state to which state it should be changed, either hit or miss
     */
    public void setArea(int row, int col, int state) {
        if (row >= 0 && row < 10 && col >= 0 && col < 10) {
            this.field[row][col] = state;
        }
    }


    /**
     * Checks if on board is completely destroyed
     * @param board Player or Ai Board
     * @return returns if j equals the maximum number of hits
     */
    public boolean end_game_checker(Board board){
        int j = 0;
        for (int i = 0; i <= 9; i++){
            for(int k = 0; k <= 9; k++){
                if(board.field[i][k] == 3){
                    j++;
                }
            }
        }
        return j == END_GAME_STATE;
    }
    /**
     * changes the field of an array to miss state
     *
     * @param row   number of row in an array
     * @param col   number of column in an array
     * @param board which board should be used
     */
    //sets area to miss
    public static void miss(int row, int col, Board board) {
        board.setArea(row, col, MISS_AREA_STATE);
    }

    /**
     * changes the field of an array to hit state
     *
     * @param row   number of row in an array
     * @param col   number of column in an array
     * @param board which board should be used
     */
    public static void hit(int row, int col, Board board) {
        board.setArea(row, col, HIT_AREA_STATE);
    }

    /**
     * changes field targeted by user or AI to either hit or miss if field wasn't shot already
     */
    public void hit_or_miss(int row, int col, Board board) {
        if (row < 0 || col < 0 || row > 9 || col > 9) {
            throw new Illegal_Argument_exception("Sie wollen außerhalb des gegnerischen Feldes schießen. Bitte geben Sie eine Zahl zwischen 1-10 und einen Buchstaben von A-J ein. Beispielweise: 2 D");
        }
        switch (board.field[col][row]) {
            case DEFAULT_AREA_STATE -> miss(row, col, board);
            case SHIP_ON_AREA_STATE -> hit(row, col, board);
            default -> throw new Illegal_Argument_exception("Dieses Feld wurde bereits einmal ausgewählt. Bitte wählen Sie ein anderes Feld!");
        }
    }



    /**
     * Checks if a ship is being hit used to recursively loop turns
     * @param row row of field
     * @param col col of field
     * @param board board
     * @return true if field just got hit
     */
    public boolean hit_checker(int row, int col, Board board){
        return board.field[col][row] == HIT_AREA_STATE;
    }




}

