package blogPack.services;

import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import blogPack.dto.DeletePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostServicesImplements implements PostServices{
    @Autowired
    private PostRepositpory postRepositpory;
    @Override
    public long count(){
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
}
