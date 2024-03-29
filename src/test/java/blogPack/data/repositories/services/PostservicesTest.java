package blogPack.data.repositories.services;

import blogPack.data.model.Post;
import blogPack.dto.PostRequest;
import blogPack.services.PostServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostservicesTest{
    @Autowired
    private PostServices postServices;
    @BeforeEach
    void wipe(){
        postServices.deleteAll();
    }
    @Test
    void createPost_testPostIsCreated(){
            Post post = new Post();
            PostRequest postRequest = new PostRequest();
            post.setTitle(postRequest.getTitle());
            postRequest.setContent(postRequest.getContent());
            postServices.save(post);
            assertEquals(1, postServices.count());
    }
    @Test
    void createMultiplePosts_testPostsAreCreated(){
        Post post = new Post();
        Post post1 = new Post();
        Post post2 = new Post();
        PostRequest postRequest = new PostRequest();
        post.setTitle(postRequest.getTitle( ));
        postRequest.setContent(postRequest.getContent( ));
        postServices.save(post);
        assertEquals(1, postServices.count());
        postServices.save(post1);
        assertEquals(2, postServices.count());
        postServices.save(post2);
        assertEquals(3, postServices.count());
    }
    //    @Test
//    void deletePost_testPostIsDeleted(){
//        Post post = new Post();
//        PostRequest postRequest = new PostRequest();
//        postRequest.setTitle("post Title.");
//        post.setTitle(postRequest.getTitle( ));
//        postRequest.setContent(postRequest.getContent( ));
//        postServices.save(post);
//        assertEquals(1, postServices.count());
//        DeletePostRequest deletePostRequest = new DeletePostRequest();
//        deletePostRequest.setPostTitle("post Title.");
//        postServices.deletePost();
//    }
}
