package utilities;

import blogPack.dto.CommentRequest;
import blogPack.exception.InvalidInputException;

public class Validate{
    public  static void validateCommentRequest(CommentRequest commentRequest){
        validate(commentRequest.getCommenterUsername());
        validate(commentRequest.getCommentBody());
        validate(commentRequest.getPostTitle());
        validate(commentRequest.getPosterName());
    }
    public static void validate(String input){
        if( input.isBlank()){
            throw new InvalidInputException();
        }
    }
}
