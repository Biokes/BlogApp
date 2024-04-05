package blog.data.repositories;

import blog.data.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepositpory extends MongoRepository<Post, String>{
    List<Post> findPostsByPoster(String posterName);

    Optional<Post> findPostByTitle(String postTitle);

}
