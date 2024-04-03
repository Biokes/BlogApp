package blogPack.data.repositories.services;

import blogPack.data.model.Post;
import blogPack.dto.requests.*;
import blogPack.dto.response.ViewPostResponse;
import blogPack.exception.IncorrectPasswordException;
import blogPack.exception.NoPostMatchException;
import blogPack.exception.UsernameAlreadyExistException;
import blogPack.services.CommentServices;
import blogPack.services.PostServices;
import blogPack.services.UserServices;
import blogPack.services.ViewService;
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
    @Autowired
    private ViewService viewService;
    @BeforeEach void wipe(){
        viewService.deleteAll();
        postServices.deleteAll( );
        userServices.deleteAll();
        commentServices.deleteAll();
    }
    @Test void createUser_testUserIsCreated(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        assertEquals(1,userServices.countNumberOfUsers());
    }
    @Test void commentOnPost_testPostIsCommentedOn(){
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
    @Test void commentOnPostThatIsNotCreated_testExceptionIsThrown(){
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
    @Test void viewPost_testPostIsViewed(){
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
    @Test void viewPostWithWrongUsername_testExceptionIsThrown(){
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
    @Test void updatePost_testPostIsUpdated(){
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
        assertEquals(1, userServices.countPosts());
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("new");
        viewRequest.setPostTitle("post title.");
        viewRequest.setViewerUsername("new");
        userServices.viewPost(viewRequest);
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("new");
        commentRequest.setPostTitle("post title.");
        commentRequest.setPosterName("new");
        assertEquals(0,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
        commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice \ni ❤️ dis");
        commentRequest.setCommenterUsername("new");
        commentRequest.setPostTitle("post title.");
        commentRequest.setPosterName("new");
        assertEquals(1,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
        assertEquals(2,userServices.countNumberOfComments());
        DeletePostRequest deletePostRequest = new DeletePostRequest();
        deletePostRequest.setPostTitle("post title.");
        deletePostRequest.setPosterUserName("new");
        deletePostRequest.setPassword("pass101");
        userServices.deletePostWith(deletePostRequest);
        assertEquals(0, userServices.countPosts());
    }
    @Test void deletePostWithIncorrectPassword_testExceptionIsThrown(){
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
        assertEquals(1, userServices.countPosts());
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("new");
        viewRequest.setPostTitle("post title.");
        viewRequest.setViewerUsername("new");
        userServices.viewPost(viewRequest);
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("new");
        commentRequest.setPostTitle("post title.");
        commentRequest.setPosterName("new");
        assertEquals(0,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
        commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice \ni ❤️ dis");
        commentRequest.setCommenterUsername("new");
        commentRequest.setPostTitle("post title.");
        commentRequest.setPosterName("new");
        assertEquals(1,userServices.countNumberOfComments());
        userServices.commentOnPostWith(commentRequest);
        assertEquals(2,userServices.countNumberOfComments());
        DeletePostRequest deletePostRequest = new DeletePostRequest();
        deletePostRequest.setPostTitle("post title.");
        deletePostRequest.setPosterUserName("new");
        deletePostRequest.setPassword("pass101up");
        assertThrows(IncorrectPasswordException.class,()->userServices.deletePostWith(deletePostRequest));
    }
    @Test void deletePost_testAllCommentsAndViewsOnPostAreNotAccesibleAgain(){
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new");
        request.setLastName("newLastName");
        request.setPassword("pass101");
        userServices.createUser(request);
        request= new RegisterRequest();
        request.setFirstName("newUser");
        request.setUserName("new012");
        request.setLastName("newLastName");
        request.setPassword("pass1056");
        userServices.createUser(request);
        PostRequest postRequest = new PostRequest();
        postRequest.setPosterUserName("new");
        postRequest.setTitle("error");
        postRequest.setContent("new  post to test the code");
        postRequest.setTitle(postRequest.getTitle());
        postRequest.setContent(postRequest.getContent( ));
        userServices.savePost(postRequest);
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("new");
        viewRequest.setPostTitle("error");
        viewRequest.setViewerUsername("new012");
        userServices.viewPost(viewRequest);
        ViewsCountRequest countRequest = new ViewsCountRequest();
        countRequest.setPosterUsername("new");
        countRequest.setPostTitle("error");
        countRequest.setViewerUsername("new012");
        assertEquals(1, userServices.countPosts());
        assertEquals(1,userServices.countViewsOnPostWith(countRequest));
        userServices.viewPost(viewRequest);
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setCommentBody("nice");
        commentRequest.setCommenterUsername("new012");
        commentRequest.setPostTitle("error");
        commentRequest.setPosterName("new");
        userServices.commentOnPostWith(commentRequest);
        CommentDetailsRequest detailsRequest = new CommentDetailsRequest();
        detailsRequest.setPosterUsername("new");
        detailsRequest.setPostTitle("error");
        assertEquals(1, userServices.countNumberOfCommentsOnPost(detailsRequest));
        DeletePostRequest deletePostRequest = new DeletePostRequest();
        deletePostRequest.setPostTitle("error");
        deletePostRequest.setPosterUserName("new");
        deletePostRequest.setPassword("pass101");
        userServices.deletePostWith(deletePostRequest);
        assertEquals(0, userServices.countPosts());
        assertThrows(PostDoesNotExistException.class, ()->userServices.countNumberOfCommentsOnPost(detailsRequest));
    }
    @Test void viewPost_postIsSeenWithCommentsAndNumberOfViews(){
        RegisterRequest request= new RegisterRequest();
        request.setFirstName("maya");
        request.setUserName("maya");
        request.setLastName("neville");
        request.setPassword("1234567");
        userServices.createUser(request);
        request= new RegisterRequest();
        request.setFirstName("james");
        request.setUserName("jamesHarden");
        request.setLastName("harden");
        request.setPassword("12345");
        userServices.createUser(request);
        PostRequest postRequest = new PostRequest();
        postRequest.setPosterUserName("maya");
        postRequest.setTitle("post1");
        postRequest.setContent("contents of post 1");
        userServices.savePost(postRequest);
        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setPosterUsername("maya");
        viewRequest.setPostTitle("post1");
        viewRequest.setViewerUsername("jamesHarden");
        ViewPostResponse response = userServices.viewPost(viewRequest);
        System.out.println(response);
        assertEquals(1, response.getViewersCount());
    }
    @Test void createUserWithExistingUsername_testExceptionIsThrown(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        assertEquals(1,userServices.countNumberOfUsers());
        request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        RegisterRequest finalRequest = request;
        assertThrows(UsernameAlreadyExistException.class, ()->userServices.createUser(finalRequest));
        assertEquals(1,userServices.countNumberOfUsers());
    }
    }
