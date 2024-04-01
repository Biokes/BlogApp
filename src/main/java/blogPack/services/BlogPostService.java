package blogPack.services;

import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import blogPack.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blogPack.exception.PostDoesNotExistException;
import utilities.Mappers;

import java.util.List;

@Service

public class BlogPostService implements PostServices{
    @Autowired
    private PostRepositpory postRepositpory;
    public long countNumberOfPosts(){
        return postRepositpory.count( );
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
                if( post.getPoster( ).equalsIgnoreCase(deletePostRequest.getPosterUserName( )) ){
                    postRepositpory.delete(post);
                    return;
                }
            }

        }
    @Override
    public Post findPostBy(String postTitle){
        Post foundPost = new Post();
        List<Post> postList = postRepositpory.findAll();
        for(Post post : postList) if(post.getTitle().equalsIgnoreCase(postTitle)){
                foundPost = post;
                break;
            }
        if(foundPost.getId() == null && foundPost.getDateCreated()== null) throw new PostDoesNotExistException();
     return foundPost;
    }

    @Override
    public void createPost(PostRequest postRequest){
        Post post = new Post();
        Mappers.mapPost(postRequest, post);
    }
}
