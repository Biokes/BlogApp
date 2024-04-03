package blogPack.contoller;
import blogPack.dto.requests.*;
import blogPack.dto.response.ViewPostResponse;
import blogPack.exception.BlogExceptions;
import blogPack.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController{
    private UserServices userServices;
    @PostMapping("/Blog-Register-user")
    public String registerUser(@RequestBody RegisterRequest registerRequest){
        try{
           userServices.createUser(registerRequest);
        }catch(BlogExceptions exceptions){
            return exceptions.getMessage();
        }
        return "successfully created";
    }
    @DeleteMapping("/delete-Post")
    public String deletePost(@RequestBody DeletePostRequest deletePostRequest){
        try{
            userServices.deletePostWith(deletePostRequest);
            return "deleted successfully";
        }catch(BlogExceptions error){
            return error.getMessage();
        }
    }
    @GetMapping("/view-post")
    public String viewPost(@RequestBody ViewRequest viewRequest){
        try{
            ViewPostResponse viewPostResponse = userServices.viewPost(viewRequest);
            return viewPostResponse.toString();
        }catch(BlogExceptions error){
            return error.getMessage();
        }
    }
    @PatchMapping("/comment-on-post")
    public String commentOnPost(@RequestBody CommentRequest commentRequest){
        try{
            userServices.commentOnPostWith(commentRequest);
            return "commment added to post";
        }catch(BlogExceptions error){
            return error.getMessage();
        }
    }
    @RequestMapping("/update-post")
    public String updatePostRequest(@RequestBody UpdatePostRequest updatePostRequest){
        try{
            userServices.updatePost(updatePostRequest);
            return "updated successfully.";
        }
        catch(BlogExceptions error){
            return error.getMessage();
        }
    }
    @RequestMapping("/create-post")
    public String createPost(@RequestBody PostRequest postRequest){
        try{
            userServices.savePost(postRequest);
            return "post saved";
        }
        catch(BlogExceptions error){
            return error.getMessage();
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
//        }
//
//    }
}

