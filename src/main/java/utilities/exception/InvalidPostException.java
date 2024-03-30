package utilities.exception;

public class InvalidPostException extends BlogExceptions{
    public InvalidPostException(){
        super("post does not exist");
    }
}
