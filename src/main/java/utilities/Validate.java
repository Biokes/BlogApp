package utilities;

import blogPack.dto.requests.CommentDetailsRequest;
import blogPack.dto.requests.CommentRequest;
import blogPack.dto.requests.DeletePostRequest;
import blogPack.dto.requests.ViewRequest;
import blogPack.exception.InvalidInputException;

public class Validate{
    public static void validateViewRequest(ViewRequest viewRequest){
        validate(viewRequest.getPosterUsername());
        validate(viewRequest.getPostTitle());
        validate(viewRequest.getViewerUsername());
    }
    public static void validateCommentDetailsRequest(CommentDetailsRequest commentDetails){
        validate(commentDetails.getPostTitle());
        validate(commentDetails.getPosterUsername());
    }
    public static void validateDeletePostRequest(DeletePostRequest deletePostRequest){
        validate(deletePostRequest.getPosterUserName());
        validate(deletePostRequest.getPostTitle());
        validate(deletePostRequest.getPassword());
    }
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
