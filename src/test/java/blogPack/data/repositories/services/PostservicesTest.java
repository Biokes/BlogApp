package blogPack.data.repositories.services;

import blogPack.data.model.Post;
import blogPack.dto.PostRequest;
import blogPack.services.PostServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostservicesTest{
    @Autowired
    private PostServices postServices;
    @Test
    void createPost_testPostIsCreated(){
            Post post = new Post();
            PostRequest postRequest = new PostRequest();
            post.setTitle(postRequest.getTitle( ));
            postRequest.setContent(postRequest.getContent( ));
            postServices.save(post);
            assertEquals(1, postServices.count());
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
