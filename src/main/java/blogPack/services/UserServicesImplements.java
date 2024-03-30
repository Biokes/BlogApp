package blogPack.services;

import blogPack.data.model.Comment;
import blogPack.data.model.User;
import blogPack.data.repositories.CommentRepository;
import blogPack.data.repositories.PostRepositpory;
import blogPack.data.repositories.UserRepository;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.Mappers;

@Service
public class UserServicesImplements implements UserServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostServices postServices;
    @Autowired
    private CommentServices commentServices;
    @Override
    public void createUser(RegisterRequest registerRequest){
        User user = new User();
        userRepository.save(Mappers.mapRegister(user, registerRequest));
    }
    @Override
    public long countNumberOfUsers(){
        return userRepository.count( );
    }

    @Override
    public void commentOnPost(CommentRequest commentRequest){
        postServices.addCommentToPost(commentRequest);
//        commentServices.save(commentRequest);
    }

    @Override
    public void deleteAll(){
        userRepository.deleteAll();
    }
}
