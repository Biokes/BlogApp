package utilities;

import blogPack.data.model.Comment;
import blogPack.data.model.User;
import blogPack.data.model.Views;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import blogPack.dto.ViewRequest;

import java.time.LocalDateTime;

public class Mappers{
    public static User mapRegister(User userToBeCreated, RegisterRequest registerRequest){
        userToBeCreated.setUserName(registerRequest.getUserName( ));
        userToBeCreated.setPassword(registerRequest.getPassword( ));
        userToBeCreated.setFirstName(registerRequest.getFirstName());
        userToBeCreated.setLastName(registerRequest.getLastName( ));
        return userToBeCreated;
    }
    public static void mapComment(Comment comment, CommentRequest commentRequest){
        comment.setCommenter(commentRequest.getCommenter());
        comment.setCommentBody(commentRequest.getCommentBody( ));
        comment.setTimeOfComment(LocalDateTime.now( ));
        comment.setPostTitle(commentRequest.getPostTitle( ));
    }

    public static void mapView(Views views, ViewRequest viewRequest){
        views.setTimeViewed(LocalDateTime.now());
        views.setPosterUsername(viewRequest.getPosterUsername());
        views.setPostTitle(viewRequest.getPostTitle());
    }
}
