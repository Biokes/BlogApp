package blog.contoller;
import blog.dto.requests.*;
import blog.dto.response.ViewPostResponse;
import blog.exception.BlogExceptions;
import blog.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController{
    private UserServices userServices;
    @PostMapping("/Blog-Register-user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try{
           userServices.createUser(registerRequest);
        }catch(BlogExceptions exceptions){
            return new ResponseEntity<>(exceptions.getMessage(), BAD_REQUEST);
        }
        return new ResponseEntity<>( "successfully created", CREATED);
    }
    @DeleteMapping("/delete-Post")
    public ResponseEntity<?> deletePost(@RequestBody DeletePostRequest deletePostRequest){
        try{
            userServices.deletePostWith(deletePostRequest);
            return new ResponseEntity<>("deleted successfully", OK);
        }catch(BlogExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
    @GetMapping("/view-post")
    public ResponseEntity<?> viewPost(@RequestBody ViewRequest viewRequest){
        try{
            ViewPostResponse viewPostResponse = userServices.viewPost(viewRequest);
            return new ResponseEntity<>(viewPostResponse.toString(), OK);
        }catch(BlogExceptions error){
            return new ResponseEntity<>(error.getMessage(),BAD_REQUEST);
        }
    }
    @PatchMapping("/comment-on-post")
    public ResponseEntity<?> commentOnPost(@RequestBody CommentRequest commentRequest){
        try{
            userServices.commentOnPostWith(commentRequest);
            return new ResponseEntity<>("commment added to post", OK);
        }catch(BlogExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
    @RequestMapping("/update-post")
    public ResponseEntity<?> updatePostRequest(@RequestBody UpdatePostRequest updatePostRequest){
        try{
            userServices.updatePost(updatePostRequest);
            return new ResponseEntity<>("updated successfully.",OK);
        }
        catch(BlogExceptions error){
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        }
    }
    @RequestMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){
        try{
            userServices.savePost(postRequest);
            return new ResponseEntity<>("post saved", OK);
        }
        catch(BlogExceptions error){
            return new ResponseEntity<>(error.getMessage(),BAD_REQUEST);
        }
    }
//    @DeleteMapping("/delete-comment")
//    public String deleteComment(@RequestBody DeleteCommentRequest deleteComent){
//        try{
//            userServices.deleteComment(deleteComent);
//            return "deleted successfully";
//        }
//        catch(BlogExceptions error){
//            return error.getMessage();
//
//    }
}

