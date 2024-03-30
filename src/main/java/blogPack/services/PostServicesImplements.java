package blogPack.services;

import blogPack.data.model.Comment;
import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import blogPack.dto.CommentRequest;
import blogPack.dto.DeletePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.Mappers;

import java.util.List;

@Service

public class PostServicesImplements implements PostServices{
    @Autowired
    private PostRepositpory postRepositpory;
    @Autowired
    private CommentServices commentServices;
    @Override
    public long countNumberOfPosts(){
        return postRepositpory.count( );
    }
    @Override
    public void save(Post post){
        postRepositpory.save(post);
    }
    @Override
    public void deleteAll(){
        postRepositpory.deleteAll();
    }
    @Override
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
    public void addCommentToPost(CommentRequest commentRequest){
        commentServices.save(commentRequest);
    }
}
