
import data.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class RepositoryTest{
    @Autowired
    private UserRepository userRepository;
    @Test
    public void saveUser_testUserIsSaved(){

    }

}