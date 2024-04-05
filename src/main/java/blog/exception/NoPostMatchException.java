package blog.exception;

public class NoPostMatchException extends BlogExceptions{
    public NoPostMatchException(){
        super("User not found");
    }
}
