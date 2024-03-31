package blogPack.services;

import blogPack.data.model.User;
import blogPack.data.repositories.UserRepository;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import blogPack.exception.UserNotFoundException;
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
    @Autowired
    private ViewService viewService;
    @Override
    public User createUser(RegisterRequest registerRequest){
        User user = new User();
        userRepository.save(Mappers.mapRegister(user, registerRequest));
        return user;
    }
    public long countNumberOfUsers(){
        return userRepository.count( );
    }
    public void addCommentToPost(CommentRequest commentRequest){
        postServices.addCommentToPost(commentRequest);
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
    public long countViewsOnPostWith(ViewsCountRequest viewCountRequest){
        return postServices.countViews(viewCountRequest);
    }

    @Override
    public long countNumberOfComments(){
        return commentServices.countNumberOfComments();
    }

    @Override
    public User findUserBy(String posterUsername){
        return userRepository.findUserByUserName(posterUsername);
    }

    @Override
    public void viewWith(ViewRequest viewRequest){
        User userGotten = findUserBy(viewRequest.getPosterUsername());
        if(userGotten == null){
            throw new UserNotFoundException();
        }
        viewService.viewWith(viewRequest, userGotten);
    }
}
