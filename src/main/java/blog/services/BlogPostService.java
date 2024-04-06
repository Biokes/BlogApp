package blog.services;
import blog.data.model.Post;
import blog.data.repositories.PostRepositpory;
import blog.dto.requests.DeletePostRequest;
import blog.dto.requests.PostRequest;
import blog.dto.requests.UpdatePostRequest;
import blog.exception.PostDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import blog.utilities.Mappers;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogPostService implements PostServices{
    public long countNumberOfPosts(){
        return postRepository.count();
    }
    public void save(Post post){
        postRepository.save(post);
    }
    public void deleteAll(){
        postRepository.deleteAll();
    }
    public void deletePost(DeletePostRequest deletePostRequest){
        Post postGotten = findPostBy(deletePostRequest.getPostTitle());
            postRepository.delete(postGotten);
        }
    public Post findPostBy(String postTitle){
        if(postRepository.findPostByTitle(postTitle).isPresent())
            return postRepository.findPostByTitle(postTitle).get();
        throw new PostDoesNotExistException();
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
    private PostRepositpory postRepository;
}