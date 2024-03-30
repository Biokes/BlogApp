package blogPack.data.repositories;

import blogPack.data.model.Post;
import blogPack.dto.CommentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepositpory extends MongoRepository<Post, String>{
    List<Post> findAllPostByUserName(String posterName);
}
