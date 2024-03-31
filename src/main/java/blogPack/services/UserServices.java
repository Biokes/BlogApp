package blogPack.services;

import blogPack.data.model.User;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import blogPack.dto.ViewsCountRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserServices{
    User createUser(RegisterRequest registerRequest);
    long countNumberOfUsers();
    void addCommentToPost(CommentRequest commentRequest);
    void deleteAll();

    long countViewsOnPostWith(ViewsCountRequest viewCountRequest);

    long countNumberOfComments();

    User findUserBy(String posterUsername);
}
