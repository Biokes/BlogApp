package blogPack.services;

import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserServices{

    void createUser(RegisterRequest registerRequest);

    long countNumberOfUsers();

    void commentOnPost(CommentRequest commentRequest);


//    void deleteUser(String userName);

}
