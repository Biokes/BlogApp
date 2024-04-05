package blog.data.repositories;

import blog.data.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositpory extends MongoRepository<Post, String>{
    List<Post> findPostsByPoster(String posterName);

    Post findPostByTitle(String postTitle);

}
