package blogPack.data.repositories.services;

import blogPack.data.model.Post;
import blogPack.dto.*;
import blogPack.exception.NoPostMatchException;
import blogPack.services.CommentServices;
import blogPack.services.PostServices;
import blogPack.services.UserServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import blogPack.exception.PostDoesNotExistException;

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
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        userServices.createUser(request);
        Post post = new Post();
        PostRequest postRequest = new PostRequest();
        post.setPoster("userName");
        postRequest.setTitle("post Title.");
        postRequest.setPosterUserName("new");
        post.setTitle(postRequest.getTitle( ));
        postRequest.setContent(postRequest.getContent( ));
        postServices.save(post);
        assertEquals(1, postServices.countNumberOfPosts());
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("new");
        commentRequest.setPostTitle("post Title.");
        commentRequest.setPosterName("userName");
        RegisterRequest request1= new RegisterRequest();
        request1.setUserName("userName");
        request1.setFirstName("FirstName");
        request1.setLastName("LastName");
        request1.setPassword("my password");
        userServices.createUser(request1);
        assertEquals(0, userServices.countNumberOfComments());
        userServices.addCommentToPost(commentRequest);
        assertEquals(1, commentServices.countNumberOfComments());
    }
    @Test
    void commentOnPostThatIsNotCreated_testExceptionIsThrown(){
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        userServices.createUser(request);
        assertEquals(1, userServices.countNumberOfUsers());
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("new");
        commentRequest.setPostTitle("post Title.");
        commentRequest.setPosterName("userName");
        assertEquals(0, postServices.countNumberOfPosts());
        assertThrows(PostDoesNotExistException.class, ()->userServices.addCommentToPost(commentRequest));
    }
    @Test
    void viewPost_testPostIsViewed(){
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        userServices.createUser(request);
        Post post = new Post();
        PostRequest postRequest = new PostRequest();
        postRequest.setPosterUserName("new");
        postRequest.setTitle("post Title.");
        postRequest.setContent("new  post to test the code");
        post.setTitle(postRequest.getTitle());
        postRequest.setContent(postRequest.getContent( ));
        userServices.savePost(postRequest);
        assertEquals(1, postServices.countNumberOfPosts());
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("new");
        viewRequest.setPostTitle("post title.");
        viewRequest.setViewerUsername("new");
        userServices.viewPost(viewRequest);
        ViewsCountRequest viewCountRequest = new ViewsCountRequest();
        viewCountRequest.setPostTitle("post title.");
        viewCountRequest.setPosterUsername("new");
        assertEquals(1, userServices.countViewsOnPostWith(viewCountRequest));
    }
    @Test
    void viewPostWithWrongUsername_testExceptionIsThrown(){
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        userServices.createUser(request);
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("userName");
        viewRequest.setPostTitle("post title");
        viewRequest.setViewerUsername("new");
        assertThrows(NoPostMatchException.class,()->userServices.viewPost(viewRequest));
        ViewsCountRequest viewCountRequest = new ViewsCountRequest();
        viewCountRequest.setPostTitle("post title");
        viewCountRequest.setPosterUsername("userName");
        assertThrows(PostDoesNotExistException.class,()-> userServices.countViewsOnPostWith(viewCountRequest));
    }
    @Test
    void updatePost_testPostIsUpdated(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);

    }
    //update post
    //delete post
    //view post that does not exist
    //update post that does not exist
    //find user that does not exist
    //delete all user post with incorrect password
}
