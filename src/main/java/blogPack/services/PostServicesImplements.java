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
    public void deletePost(DeletePostRequest deletePostRequest){
        List<Post> allPost = postRepositpory.findAll();
        for(Post post: allPost){
            if(post.getTitle().equalsIgnoreCase(deletePostRequest.getPostTitle( )))
                if(post.getPoster().equalsIgnoreCase(deletePostRequest.getUserName())){
                    postRepositpory.delete(post);
                    return;
                }

        }
    }
}
