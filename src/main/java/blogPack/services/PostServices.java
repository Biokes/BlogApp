package blogPack.services;

import blogPack.data.model.Post;
import blogPack.dto.CommentRequest;
import blogPack.dto.DeletePostRequest;
import org.springframework.stereotype.Service;

@Service
public interface PostServices{
    long countNumberOfPosts();

    void save(Post post);

    void deleteAll();

    void deletePost(DeletePostRequest deletePostRequest);

    void addCommentToPost(CommentRequest commentRequest);

//    void deletePost(DeletePostRequest deletePostRequest);
}
