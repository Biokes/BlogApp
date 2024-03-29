package blogPack.services;

import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
