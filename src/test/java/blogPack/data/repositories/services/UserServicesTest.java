package blogPack.data.repositories.services;

import blogPack.data.model.Comment;
import blogPack.data.model.Post;
import blogPack.data.model.User;
import blogPack.dto.CommentRequest;
import blogPack.dto.DeletePostRequest;
import blogPack.dto.PostRequest;
import blogPack.dto.RegisterRequest;
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
    @Test
    void createUser_testUserIsCreated(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        assertEquals(1,userServices.count());
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
        assertEquals(1, postServices.count());
        Comment comment = new Comment();
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        comment.setCommenter("userName");
        Mappers.mapComment(comment, commentRequest);
        User user = new User();
    }
}
