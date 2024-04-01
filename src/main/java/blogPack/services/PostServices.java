package blogPack.services;

import blogPack.data.model.Post;
import blogPack.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface PostServices{
    long countNumberOfPosts();
    void save(Post post);
    void deleteAll();
    void deletePost(DeletePostRequest deletePostRequest);
    Post findPostBy(String postTitle);
    void createPost(PostRequest postRequest);
    void updatePost(UpdatePostRequest updatePostRequest);
}
