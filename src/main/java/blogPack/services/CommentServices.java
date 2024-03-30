package blogPack.services;

import blogPack.data.model.Comment;
import blogPack.dto.CommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentServices{

    long countNumberOfComments();

    void save(CommentRequest commentRequest);
    void deleteAll();
}
