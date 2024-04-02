package blogPack.exception;

public class UsernameAlreadyExistException extends BlogExceptions{
    public UsernameAlreadyExistException(){
        super("Username already exist");
    }
}
