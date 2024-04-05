package blog.exception;

public class InvalidUsernameException extends BlogExceptions{
    public InvalidUsernameException(){
        super("Invalid username provided");

    }
}
