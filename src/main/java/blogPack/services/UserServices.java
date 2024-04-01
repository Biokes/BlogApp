package blogPack.services;
import blogPack.data.model.User;
import blogPack.dto.*;
import org.springframework.stereotype.Service;
@Service
public interface UserServices{
    void createUser(RegisterRequest registerRequest);
    long countNumberOfUsers();
    void addCommentToPost(CommentRequest commentRequest);
    void deleteAll();
    long countViewsOnPostWith(ViewsCountRequest viewCountRequest);
    long countNumberOfComments();
    User findUserBy(String posterUsername);

    ViewPostResponse viewPost(ViewRequest viewRequest);

    void savePost(PostRequest postRequest);

    long countPosts();

    void updatePost(UpdatePostRequest updatePostRequest);
}
