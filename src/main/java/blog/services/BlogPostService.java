package blog.services;
import blog.data.model.Post;
import blog.data.repositories.PostRepositpory;
import blog.dto.requests.DeletePostRequest;
import blog.dto.requests.PostRequest;
import blog.dto.requests.UpdatePostRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import utilities.Mappers;

import java.util.Optional;

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
        Optional<Post> postGotten = findPostBy(deletePostRequest.getPostTitle());
        if( postGotten.isPresent()){
            postRepositpory.delete(postGotten.get());
            }
        }
    public Optional<Post> findPostBy(String postTitle){
        return postRepositpory.findPostByTitle(postTitle);
    }
    public void createPost(PostRequest postRequest){
        Post post = new Post();
        Mappers.mapPost(postRequest, post);
        save(post);
    }
    public void updatePost(UpdatePostRequest updatePostRequest){
        Optional<Post> postFound = findPostBy(updatePostRequest.getPostTitle());
        if( postFound.isPresent()){
            Post post = postFound.get();
            post.setContent(updatePostRequest.getPostBody( ));
            save(post);
        }
    }
    private PostRepositpory postRepositpory;
}