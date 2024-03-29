package blogPack.data.repositories.services;

import blogPack.services.PostServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostservicesTest{
    @Autowired
    private PostServices postServices;
    @Test
    void createPost_testPostIsCreated(){

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
