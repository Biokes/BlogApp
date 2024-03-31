package blogPack.data.repositories.services;

import blogPack.data.model.Post;
import blogPack.data.model.User;
import blogPack.dto.CommentRequest;
import blogPack.dto.PostRequest;
import blogPack.dto.RegisterRequest;
import blogPack.services.CommentServices;
import blogPack.services.PostServices;
import blogPack.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import utilities.exception.InvalidPostException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServicesTest{
    @Autowired
    private UserServices userServices;
    @Autowired
    private PostServices postServices;
    @Autowired
    private CommentServices commentServices;
    @BeforeEach
    void wipe(){
        postServices.deleteAll( );
        userServices.deleteAll();
        commentServices.deleteAll();
    }
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
    void commentOnPost_testPostIsCommentedOn(){
        User user;
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        user = userServices.createUser(request);
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
        commentRequest.setCommenter(user);
        commentRequest.setPostTitle("post Title.");
        commentRequest.setPosterName("userName");
        RegisterRequest request1= new RegisterRequest();
        request1.setUserName("userName");
        request1.setFirstName("FirstName");
        request1.setLastName("LastName");
        request1.setPassword("my password");
        userServices.createUser(request1);
        assertEquals(0, commentServices.countNumberOfComments());
        userServices.addCommentToPost(commentRequest);
        assertEquals(1, commentServices.countNumberOfComments());
    }
    @Test
    void commentPostThatIsNotCreated_testExceptionIsThrown(){
        User user;
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        user = userServices.createUser(request);
        assertEquals(1, userServices.countNumberOfUsers());
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenter(user);
        commentRequest.setPostTitle("post Title.");
        commentRequest.setPosterName("userName");
        assertEquals(0, commentServices.countNumberOfComments());
        assertThrows(InvalidPostException.class, ()->userServices.addCommentToPost(commentRequest));
    }
    @Test
    void viewPost_testPostIsViewed(){
        User user;
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        user = userServices.createUser(request);
        Post post = new Post();
        PostRequest postRequest = new PostRequest();
        post.setPoster("userName");
        postRequest.setTitle("post Title.");
        post.setTitle(postRequest.getTitle( ));
        postRequest.setContent(postRequest.getContent( ));
        postServices.save(post);
        assertEquals(1, postServices.countNumberOfPosts());
        postServices.viewPostWith(post);
    }
    //view post that is not created yet to throw exception
    // create a post with userName that does not exist
    //update post
    //delete post
    //update post that does not exist
    //find user that does not exist
    //delete all user post with incorrect password
}
