package blogPack.services;

import blogPack.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImplements implements UserServices{

    @Override
    public void createUser(RegisterRequest registerRequest){

    }

    @Override
    public long count(){
        return 0;
    }
}
