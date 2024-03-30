package blogPack.services;

import blogPack.data.model.User;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserServices{
    User createUser(RegisterRequest registerRequest);
    long countNumberOfUsers();
    void addCommentToPost(CommentRequest commentRequest);
    void deleteAll();
}
