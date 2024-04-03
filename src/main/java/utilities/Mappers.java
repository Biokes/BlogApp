package utilities;

import blogPack.data.model.Comment;
import blogPack.data.model.Post;
import blogPack.data.model.User;
import blogPack.data.model.Views;
import blogPack.dto.requests.CommentRequest;
import blogPack.dto.requests.PostRequest;
import blogPack.dto.requests.RegisterRequest;
import blogPack.dto.requests.ViewRequest;
import blogPack.dto.response.ViewPostResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
        comment.setPosterUsername(commentRequest.getPosterName( ));
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
        response.setPostbody(foundPost.getContent());
        response.setPostTitle(foundPost.getTitle());
        response.setDateCreated(foundPost.getDateCreated());
        return response;
    }
    public static ViewPostResponse mapViewsWithResponse(List<Views> viewsList, ViewPostResponse response){
        StringBuilder viewDetails = new StringBuilder();
        int count =0;
        if(!viewsList.isEmpty()){
        for(Views views : viewsList){
            viewDetails.append(String.format("Viewer %s : %s\nTime of View : %s\n\n",++count,
                    views.getViewer().getUserName(),
                    views.getTimeViewed()));
        }
            response.setViews(viewDetails.toString());
            response.setViewersCount(viewsList.size());
            return response;
        }
        response.setViews("no views recorded yet");
        response.setViewersCount(0);
        return response;
    }
}
