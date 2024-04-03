package blogPack.services;
import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import blogPack.dto.requests.DeletePostRequest;
import blogPack.dto.requests.PostRequest;
import blogPack.dto.requests.UpdatePostRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import blogPack.exception.PostDoesNotExistException;
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
        for(Post post : postRepositpory.findAll()){
            if( post.getTitle().equalsIgnoreCase(deletePostRequest.getPostTitle( )) )
                if( post.getPoster().equalsIgnoreCase(deletePostRequest.getPosterUserName( )) ){
                    postRepositpory.delete(post);
                    return;
                }
            }

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