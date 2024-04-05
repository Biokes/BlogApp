package blog.exception;

public class BlogExceptions extends RuntimeException{
    public BlogExceptions(String message){
        super(message);
    }
    public BlogExceptions(){
        super("Invalid request provided");
    }
}
