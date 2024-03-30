package blogPack.data.repositories.repositories;

import blogPack.data.model.Comment;
import blogPack.data.repositories.CommentRepository;
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
    }
}
