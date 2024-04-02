package blogPack.services;

import blogPack.data.model.Post;
import blogPack.data.model.User;
import blogPack.data.model.Views;
import blogPack.data.repositories.UserRepository;
import blogPack.dto.*;
import blogPack.exception.IncorrectPasswordException;
import blogPack.exception.InvalidUsernameException;
import blogPack.exception.NoPostMatchException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utilities.Mappers;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogUserService implements UserServices{
    public void createUser(RegisterRequest registerRequest){
        User user = new User();
        userRepository.save(Mappers.mapRegister(user, registerRequest));
   }
    public long countNumberOfUsers(){
        return userRepository.count( );
    }
    public void commentOnPostWith(CommentRequest commentRequest){
        postServices.findPostBy(commentRequest.getPostTitle());
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
    public ViewPostResponse viewPost(ViewRequest viewRequest){
        validate(viewRequest);
        Views views = new Views();
        views.setViewer(findUserBy(viewRequest.getViewerUsername()));
        return getAllViewsOnPost(viewRequest);
    }
    public void savePost(PostRequest postRequest){
        Post post = new Post();
        Mappers.mapPost(postRequest,post);
        postServices.save(post);
    }
    public long countPosts(){
        return postServices.countNumberOfPosts();
    }
    public void updatePost(UpdatePostRequest updatePostRequest){
        confirmUserDetails(updatePostRequest);
        postServices.updatePost(updatePostRequest);
    }
    public long countNumberOfCommentsOnPost(CommentDetailsRequest commentDetailsRequest){
        postServices.findPostBy(commentDetailsRequest.getPostTitle( ));
        return commentServices.countCommentsOnPost(
                commentDetailsRequest.getPostTitle( ),
                commentDetailsRequest.getPosterUsername()
        );
    }
    public void deletePostWith(DeletePostRequest deletePostRequest){
        validatePassword(deletePostRequest);
        postServices.deletePost(deletePostRequest);
        DeleteCommentRequest deleteCommentRequest = new DeleteCommentRequest();
        deleteCommentOnPost(deleteCommentRequest);
        deletViewsForPost(deletePostRequest);
    }
    public long countViews(){
        return viewService.count();
    }
    private ViewPostResponse getAllViewsOnPost(ViewRequest viewRequest){
        Post post = postServices.findPostBy(viewRequest.getPostTitle());
        User viewer = findUserBy(viewRequest.getViewerUsername());
        List<Views> viewsList = viewService.getViewsWith(viewRequest);
        ViewPostResponse response = Mappers.mapPostResponse(post);
        viewService.viewWith(viewRequest, viewer);
        response.setViews(response.getViews());
        return Mappers.mapViewsWithResponse(viewsList, response);
    }
    private void validateViewer(ViewRequest viewRequest){
        findUserBy(viewRequest.getViewerUsername());
    }
    private void validatePassword(DeletePostRequest deletePostRequest){
        String userPassword = findUserBy(deletePostRequest.getPosterUserName())
                                      .getPassword();
        if(!userPassword.equalsIgnoreCase(deletePostRequest.getPassword()))
            throw new IncorrectPasswordException();
    }
    private void deleteCommentOnPost(DeleteCommentRequest deleteCommentRequest){
        commentServices.deleteCommentsOnPost(deleteCommentRequest);
    }
    private void validatePoster(ViewRequest viewRequest){
        User userGotten = findUserBy(viewRequest.getPosterUsername());
        if(userGotten == null){
            throw new NoPostMatchException();
        }
    }
    private void validateComment(CommentRequest commentRequest){
        User commenter = findUserBy(commentRequest.getCommenterUsername( ));
        if(commenter == null) throw new InvalidUsernameException();
    }
    private void validate(ViewRequest viewRequest){
        validatePoster(viewRequest);
        validateViewer(viewRequest);
    }
    private void saveComment(CommentRequest commentRequest){
        validateComment(commentRequest);
        User commenter = findUserBy(commentRequest.getCommenterUsername( ));
        commentRequest.setCommenter(commenter);
        commentServices.save(commentRequest);
    }
    private void deletViewsForPost(DeletePostRequest deletePostRequest){
        DeleteViewRequest deleteViewRequest = new DeleteViewRequest();
        deleteViewRequest.setPostTitle(deletePostRequest.getPostTitle( ));
        deleteViewRequest.setPosterUsername(deletePostRequest.getPosterUserName());
        viewService.deleteViewsWith(deleteViewRequest);
    }
    private void confirmUserDetails(UpdatePostRequest updatePostRequest){
        User userFound = findUserBy(updatePostRequest.getPosterUserName( ));
        if(!userFound.getPassword().equalsIgnoreCase(updatePostRequest.getPosterPassword()))
            throw new IncorrectPasswordException();
    }
    private UserRepository userRepository;
    private PostServices postServices;
    private CommentServices commentServices;
    private ViewService viewService;
}
