package blogPack.data.repositories.services;

import blogPack.data.model.Post;
import blogPack.dto.PostRequest;
import blogPack.dto.RegisterRequest;
import blogPack.services.PostServices;
import blogPack.services.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void createPost_testPostIsCreated(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("userName");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setPassword("my password");
        userServices.createUser(request);
        Post post = new Post();
        assertEquals(1,userServices.count());
        PostRequest postRequest = new PostRequest();
        post.setTitle(postRequest.getTitle( ));
        postRequest.setContent(postRequest.getContent( ));
        assertEquals(1, postServices.count());
    }
}
