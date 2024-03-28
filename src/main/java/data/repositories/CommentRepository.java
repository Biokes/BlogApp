package data.repositories;

import data.model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comments, String>{
}
