package utilities;

import blogPack.data.model.Comment;
import blogPack.data.model.Post;
import blogPack.data.model.User;
import blogPack.data.model.Views;
import blogPack.dto.*;

import java.time.LocalDate;
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
        commentRequest.setPosterName(commentRequest.getPosterName( ));
        comment.setPostTitle(commentRequest.getPostTitle( ));
    }

    public static void mapView(Views views, ViewRequest viewRequest){
        views.setTimeViewed(LocalDateTime.now());
        views.setPosterUsername(viewRequest.getPosterUsername());
        views.setPostTitle(viewRequest.getPostTitle());
    }

    public static void mapPost(PostRequest postRequest, Post post){
        post.setTitle(postRequest.getTitle());
        post.setDateCreated(LocalDate.now());
        post.setPoster(postRequest.getPosterUserName( ));
        post.setContent(postRequest.getContent( ));
    }

    public static ViewPostResponse mapPostResponse(Post foundPost){
        ViewPostResponse response = new ViewPostResponse();
        response.setPostbody(foundPost.getContent( ));
        response.setPostTitle(foundPost.getTitle());
        response.setDateCreated(foundPost.getDateCreated());
        return response;
    }
}
