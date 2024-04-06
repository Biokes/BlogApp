package blog.data.repositories.repositories;

import blog.data.model.Post;
import blog.data.repositories.PostRepositpory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRepositoryTest{
    @Autowired
    private PostRepositpory postRepositpory;
    @BeforeEach
    void wipe(){
        postRepositpory.deleteAll();
    }
    @Test
    void createPost_testPostIsCreated(){
        Post post = new Post();
        postRepositpory.save(post);
        assertEquals(1,postRepositpory.count());
    }

}
