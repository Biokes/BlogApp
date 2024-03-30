package blogPack.data.repositories.repositories;

import blogPack.data.model.Comment;
import blogPack.data.model.User;
import blogPack.data.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
        comment.setTimeOfComment(LocalDateTime.now());
        comment.setCommenter(new User());
        comment.setCommentBody("How far");
        comment.setPostTitle("Yolo");
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
