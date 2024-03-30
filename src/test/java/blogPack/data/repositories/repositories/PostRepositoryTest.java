package blogPack.data.repositories.repositories;

import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostRepositoryTest{
    @Autowired
    private PostRepositpory postRepositpory;
    @Test
    void createPost_testPostIsCreated(){
        Post post = new Post();
        postRepositpory.save(post);
        assertEquals(1,postRepositpory.count());
    }

}
