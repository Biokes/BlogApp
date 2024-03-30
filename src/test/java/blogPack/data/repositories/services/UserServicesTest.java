package blogPack.data.repositories.services;

import blogPack.data.model.Comment;
import blogPack.data.model.Post;
import blogPack.dto.CommentRequest;
import blogPack.dto.PostRequest;
import blogPack.dto.RegisterRequest;
import blogPack.services.CommentServices;
import blogPack.services.PostServices;
import blogPack.services.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utilities.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServicesTest{
    @Autowired
    private UserServices userServices;
    @Autowired
    private PostServices postServices;
    @Autowired
    private CommentServices commentServices;
    @Test
    void createUser_testUserIsCreated(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        assertEquals(1,userServices.countNumberOfUsers());
    }
    @Test
    void commentOnPost_testPostIsCommenetedOn(){
        Post post = new Post();
        PostRequest postRequest = new PostRequest();
        post.setPoster("userName");
        postRequest.setTitle("post Title.");
        post.setTitle(postRequest.getTitle( ));
        postRequest.setContent(postRequest.getContent( ));
        postServices.save(post);
        assertEquals(1, postServices.countNumberOfPosts());
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterName("userName");
        commentRequest.setPostTitle("post Title.");
        RegisterRequest request= new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        userServices.commentOnPost(commentRequest);
        assertEquals(1, commentServices.countNumberOfComments());
    }
}
