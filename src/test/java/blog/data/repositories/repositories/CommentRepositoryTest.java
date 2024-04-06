package blog.data.repositories.repositories;

import blog.data.model.Comment;
import blog.data.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommentRepositoryTest{
    @Autowired
    private CommentRepository commentRepository;
    @BeforeEach
    void wipe(){
        commentRepository.deleteAll();
    }
    @Test
    void creatComment_testCommentIsSaved(){
        assertEquals(0, commentRepository.count());
        Comment comment = new Comment();
        commentRepository.save(comment);
        assertEquals(1, commentRepository.count());
    }
    @Test
    void createMultipleComments_testMultipleCommentsAreCreated()   {
        assertEquals(0, commentRepository.count());
        commentRepository.save(new Comment());
        assertEquals(1, commentRepository.count());
        commentRepository.save(new Comment());
        assertEquals(2, commentRepository.count());
    }
}
