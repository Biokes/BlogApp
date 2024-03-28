package blogPack.data.repositories;

import blogPack.data.model.Views;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends MongoRepository<Views, String>{
}