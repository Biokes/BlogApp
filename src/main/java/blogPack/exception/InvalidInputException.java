package blogPack.exception;

public class InvalidInputException extends BlogExceptions{
    public InvalidInputException(){
        super("input Fields cannot be empty");
    }
}
