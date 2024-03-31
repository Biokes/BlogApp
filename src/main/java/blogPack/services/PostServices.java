package blogPack.services;

import blogPack.data.model.Post;
import blogPack.dto.CommentRequest;
import blogPack.dto.DeletePostRequest;
import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import org.springframework.stereotype.Service;

@Service
public interface PostServices{
    long countNumberOfPosts();
    void save(Post post);
    void deleteAll();
    void deletePost(DeletePostRequest deletePostRequest);

    long countViews(ViewsCountRequest viewCountRequest);

    Post findPostBy(String postTitle);
//    void viewPostWith(ViewRequest viewRequest);
//    long countViews(ViewsCountRequest viewCountRequest);
}
