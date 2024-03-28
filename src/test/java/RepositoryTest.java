
import data.model.User;
import data.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.Assert.assertEquals;

@DataMongoTest
public class RepositoryTest{
    @Autowired
    private UserRepository userRepository;
    @Test
    public void saveUser_testUserIsSaved(){
        User nweUser = new User();
        userRepository.save(nweUser);
        assertEquals(1, userRepository.count());
    }

}