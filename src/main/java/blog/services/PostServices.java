package blog.services;

import blog.data.model.Post;
import blog.dto.requests.DeletePostRequest;
import blog.dto.requests.PostRequest;
import blog.dto.requests.UpdatePostRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PostServices{
    long countNumberOfPosts();
    void save(Post post);
    void deleteAll();
    void deletePost(DeletePostRequest deletePostRequest);
    Optional<Post> findPostBy(String postTitle);
    void createPost(PostRequest postRequest);
    void updatePost(UpdatePostRequest updatePostRequest);
}
