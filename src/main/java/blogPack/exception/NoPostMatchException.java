package blogPack.exception;

public class NoPostMatchException extends BlogExceptions{
    public NoPostMatchException(){
        super("User not found");
    }
}
