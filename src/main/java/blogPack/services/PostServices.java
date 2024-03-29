package blogPack.services;

import blogPack.data.model.Post;
import blogPack.dto.DeletePostRequest;
import org.springframework.stereotype.Service;

@Service
public interface PostServices{
    long count();

    void save(Post post);

//    void deletePost(DeletePostRequest deletePostRequest);
}
