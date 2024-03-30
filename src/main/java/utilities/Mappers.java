package utilities;

import blogPack.data.model.Comment;
import blogPack.data.model.User;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;

public class Mappers{
    public static User mapRegister(User userToBeCreated, RegisterRequest registerRequest){
        userToBeCreated.setUserName(registerRequest.getUserName( ));
        userToBeCreated.setPassword(registerRequest.getPassword( ));
        userToBeCreated.setFirstName(registerRequest.getFirstName());
        userToBeCreated.setLastName(registerRequest.getLastName( ));
        return userToBeCreated;
    }
    public static void mapComment(Comment comment, CommentRequest commentRequest){
        comment.setCommenter(commentRequest.getCommenterName( ));
        comment.setCommentBody(commentRequest.getCommentBody( ));
    }
}
