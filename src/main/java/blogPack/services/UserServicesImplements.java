package blogPack.services;

import blogPack.data.model.User;
import blogPack.data.repositories.UserRepository;
import blogPack.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.Mappers;

@Service
public class UserServicesImplements implements UserServices{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void createUser(RegisterRequest registerRequest){
        User user = new User();
        userRepository.save(Mappers.mapRegister(user, registerRequest));
    }
    @Override
    public long count(){
        return userRepository.count( );
    }

//    @Override
//    public void deleteUser(String userName){
//        userRepository.deley
//    }
}
