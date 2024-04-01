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
        userServices.commentOnPostWith(commentRequest);
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
        assertThrows(PostDoesNotExistException.class, ()->userServices.commentOnPostWith(commentRequest));
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
        request.setUserName("biokes");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        PostRequest postRequest = new PostRequest();
        postRequest.setPosterUserName("biokes");
        postRequest.setTitle("my post");
        postRequest.setPosterUserName("new");
        postRequest.setContent("postRequest.getContent( )");
        userServices.savePost(postRequest);
        assertEquals(1, userServices.countPosts());
        UpdatePostRequest updatePostRequest = new UpdatePostRequest();
        updatePostRequest.setPostTitle("my post");
        updatePostRequest.setPosterPassword("my password");
        updatePostRequest.setPosterUserName("biokes");
        updatePostRequest.setPostBody("updating my post for a check");
        userServices.updatePost(updatePostRequest);
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPostTitle("my post");
        viewRequest.setPosterUsername("biokes");
        viewRequest.setViewerUsername("biokes");
        ViewPostResponse response = userServices.viewPost(viewRequest);
        assertEquals("my post", response.getPostTitle());
        assertEquals("updating my post for a check", response.getPostbody());
    }
    @Test void updatePost_testCommentsAreNotAffected(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("biokes");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        PostRequest postRequest = new PostRequest();
        postRequest.setPosterUserName("biokes");
        postRequest.setTitle("post101");
        postRequest.setContent("postRequest.getContent( )");
        userServices.savePost(postRequest);
        assertEquals(1, userServices.countPosts());
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("biokes");
        commentRequest.setPostTitle("post101");
        commentRequest.setPosterName("biokes");
        assertEquals(0,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
        assertEquals(1,userServices.countNumberOfComments());
        UpdatePostRequest updatePostRequest = new UpdatePostRequest();
        updatePostRequest.setPostTitle("post101");
        updatePostRequest.setPosterPassword("my password");
        updatePostRequest.setPosterUserName("biokes");
        updatePostRequest.setPostBody("updating my post for a check");
        userServices.updatePost(updatePostRequest);
        assertEquals(1,userServices.countNumberOfComments());
        CommentDetailsRequest details= new CommentDetailsRequest();
        details.setPosterUsername("biokes");
        details.setPostTitle("post101");
        assertEquals(1,userServices.countNumberOfCommentsOnPost(details));
    }
    @Test void deletePost_testPostIsDeleted(){
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
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("new");
        viewRequest.setPostTitle("post title.");
        viewRequest.setViewerUsername("new");
        userServices.viewPost(viewRequest);
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("biokes");
        commentRequest.setPostTitle("post101");
        commentRequest.setPosterName("biokes");
        assertEquals(0,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
         commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice \ni ❤️ dis");
        commentRequest.setCommenterUsername("biokes");
        commentRequest.setPostTitle("post101");
        commentRequest.setPosterName("biokes");
        assertEquals(1,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
        assertEquals(2,userServices.countNumberOfComments());
        DeletePostRequest deletePostRequest = new DeletePostRequest();
        deletePostRequest.setPostTitle("post101");
        deletePostRequest.setPosterUserName("new");
        deletePostRequest.setPassword("pass101");
        userServices.deletePostWith(deletePostRequest);
    }
    //update post that does not exist
    //delete post with incorrect password
    //view post with wrong details
    //delete post, all comments and views on post are all deleted
}
