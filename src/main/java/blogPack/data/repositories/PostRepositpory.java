package blogPack.data.repositories;

import blogPack.data.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositpory extends MongoRepository<Post, String>{
}
