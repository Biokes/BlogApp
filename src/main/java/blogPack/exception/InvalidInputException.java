package blogPack.exception;

public class InvalidInputException extends BlogExceptions{
    public InvalidInputException(){
        super("Field cannot be empty");
    }
}
