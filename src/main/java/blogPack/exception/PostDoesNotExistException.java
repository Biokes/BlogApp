package blogPack.exception;

public class PostDoesNotExistException extends BlogExceptions{
    public PostDoesNotExistException(){
        super("post does not exist");
    }
}
