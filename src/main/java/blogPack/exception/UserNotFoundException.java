package blogPack.exception;

public class UserNotFoundException extends BlogExceptions{
    public UserNotFoundException(){
        super("User not found");
    }
}
