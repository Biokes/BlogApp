package blogPack.exception;

public class InvalidInputException extends BlogExceptions{
    public InvalidInputException(){
        super("Input Fields cannot be empty");
    }
}
