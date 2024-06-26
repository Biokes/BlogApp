package blog.utilities;

import blog.dto.requests.*;
import blog.exception.InvalidInputException;

public class Validate{
    public static void validateUpdatePostRequest(UpdatePostRequest request){
        validate(request.getPosterPassword());
        validate(request.getPostBody());
        validate(request.getPostTitle());
        validate(request.getPosterPassword());
    }
    public static void validateRegisterRequest(RegisterRequest registerRequest){
        validate(registerRequest.getFirstName());
        validate(registerRequest.getPassword());
        validate(registerRequest.getUserName());
        validate(registerRequest.getLastName());
    }
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
