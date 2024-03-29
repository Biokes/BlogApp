package blogPack.data.repositories.repositories;


import blogPack.data.model.User;
import blogPack.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class UserRepositoryTest{
    @Autowired
    private  UserRepository userRepository ;
    @Test
    public void saveUser_testUserIsSaved(){
        User user = new User();
        User user2 = new User();
        userRepository.save(user);
        userRepository.save(user2);
        assertEquals(2,userRepository.count());
    }
}