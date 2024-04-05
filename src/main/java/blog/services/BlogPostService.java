package blog.services;
import blog.data.model.Post;
import blog.data.repositories.PostRepositpory;
import blog.dto.requests.DeletePostRequest;
import blog.dto.requests.PostRequest;
import blog.dto.requests.UpdatePostRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import blog.exception.PostDoesNotExistException;
import utilities.Mappers;

import java.util.List;
@Service
@AllArgsConstructor
public class BlogPostService implements PostServices{
    public long countNumberOfPosts(){
        return postRepositpory.count();
    }
    public void save(Post post){
        postRepositpory.save(post);
    }
    public void deleteAll(){
        postRepositpory.deleteAll();
    }
    public void deletePost(DeletePostRequest deletePostRequest){
        Post postGotten = findPostBy(deletePostRequest.getPostTitle());
        postRepositpory.delete(postGotten);
        }
    public Post findPostBy(String postTitle){
        Post foundPost = new Post();
        List<Post> postList = postRepositpory.findAll();
        for(Post post : postList) if(post.getTitle().equalsIgnoreCase(postTitle)){
                foundPost = post;
                break;
            }
        if(foundPost.getTitle() == null) throw new PostDoesNotExistException();
     return foundPost;
    }
    public void createPost(PostRequest postRequest){
        Post post = new Post();
        Mappers.mapPost(postRequest, post);
        save(post);
    }
    public void updatePost(UpdatePostRequest updatePostRequest){
        Post postFound = findPostBy(updatePostRequest.getPostTitle());
        postFound.setContent(updatePostRequest.getPostBody( ));
        save(postFound);
    }
    private PostRepositpory postRepositpory;
}