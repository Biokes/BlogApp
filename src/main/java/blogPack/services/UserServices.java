package blogPack.services;

import blogPack.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserServices{

    void createUser(RegisterRequest registerRequest);

    long count();

}
