package blog.data.repositories;

import blog.data.model.Views;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRepository extends MongoRepository<Views, String>{
List<Views> findViewsBy(String postTitle);
}
