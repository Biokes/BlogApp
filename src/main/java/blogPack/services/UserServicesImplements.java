package blogPack.services;

import blogPack.data.model.Post;
import blogPack.data.model.User;
import blogPack.data.repositories.UserRepository;
import blogPack.dto.CommentRequest;
import blogPack.dto.RegisterRequest;
import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import blogPack.exception.InvalidUsernameException;
import blogPack.exception.NoPostMatchException;
import blogPack.exception.PostDoesNotExistException;
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
    public void createUser(RegisterRequest registerRequest){
        User user = new User();
        userRepository.save(Mappers.mapRegister(user, registerRequest));
   }
    public long countNumberOfUsers(){
        return userRepository.count( );
    }
    public void addCommentToPost(CommentRequest commentRequest){
        Post post = postServices.findPostBy(commentRequest.getPostTitle());
        saveComment(commentRequest);
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
    public long countViewsOnPostWith(ViewsCountRequest viewCountRequest){
        postServices.findPostBy(viewCountRequest.getPostTitle( ));
        return viewService.countViewsWith(viewCountRequest);
    }
    public long countNumberOfComments(){
        return commentServices.countNumberOfComments();
    }
    public User findUserBy(String posterUsername){
        return userRepository.findUserByUserName(posterUsername);
    }
    public void viewWith(ViewRequest viewRequest){
        User userGotten = findUserBy(viewRequest.getPosterUsername());
        if(userGotten == null){
            throw new NoPostMatchException();
        }
        viewService.viewWith(viewRequest, userGotten);
    }
    private void validateComment(CommentRequest commentRequest){
        User commenter = findUserBy(commentRequest.getCommenterUsername( ));
        if(commenter == null) throw new InvalidUsernameException();
    }
    private void saveComment(CommentRequest commentRequest){
        validateComment(commentRequest);
        User commenter = findUserBy(commentRequest.getCommenterUsername( ));
        commentRequest.setCommenter(commenter);
        commentServices.save(commentRequest);
    }
}
