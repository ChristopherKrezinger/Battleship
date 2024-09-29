package Battleship_1_0;

//to create custom exceptions
public class Illegal_Argument_exception extends RuntimeException {
    public Illegal_Argument_exception(String message) {
        super(message);
    }
}
