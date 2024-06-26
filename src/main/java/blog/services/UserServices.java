package blog.services;
import blog.data.model.User;
import blog.dto.requests.*;
import blog.dto.response.ViewPostResponse;
import org.springframework.stereotype.Service;
@Service
public interface UserServices{
    void createUser(RegisterRequest registerRequest);
    long countNumberOfUsers();
    void commentOnPostWith(CommentRequest commentRequest);
    void deleteAll();
    long countViewsOnPostWith(ViewsCountRequest viewCountRequest);
    long countNumberOfComments();
    User findUserBy(String posterUsername);
    ViewPostResponse viewPost(ViewRequest viewRequest);
    void savePost(PostRequest postRequest);
    long countPosts();
    void updatePost(UpdatePostRequest updatePostRequest);
    long countNumberOfCommentsOnPost(CommentDetailsRequest commentDetailsRequest);
    void deletePostWith(DeletePostRequest deletePostRequest);

    long countViews();
}
