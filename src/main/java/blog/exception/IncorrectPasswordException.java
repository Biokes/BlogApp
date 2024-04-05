package blog.exception;

public class IncorrectPasswordException extends BlogExceptions{
    public IncorrectPasswordException(){
        super("Incorrect password.");
    }
}
