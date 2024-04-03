package blogPack.services;

import blogPack.dto.requests.CommentRequest;
import blogPack.dto.requests.DeleteCommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentServices{
    long countNumberOfComments();
    void save(CommentRequest commentRequest);
    void deleteAll();
    long countCommentsOnPost(String postTitle, String posterUserName);
    void deleteCommentsOnPost(DeleteCommentRequest deleteCommentRequest);
}
