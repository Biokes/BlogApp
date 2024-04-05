package blog.services;

import blog.dto.requests.CommentRequest;
import blog.dto.requests.DeleteCommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentServices{
    long countNumberOfComments();
    void save(CommentRequest commentRequest);
    void deleteAll();
    long countCommentsOnPost(String postTitle, String posterUserName);
    void deleteCommentsOnPost(DeleteCommentRequest deleteCommentRequest);
}
