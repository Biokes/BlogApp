package blogPack.services;

import blogPack.data.model.Post;
import blogPack.data.repositories.PostRepositpory;
import blogPack.dto.CommentRequest;
import blogPack.dto.DeletePostRequest;
import blogPack.dto.ViewsCountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blogPack.exception.PostDoesNotExistException;

import java.util.List;

@Service

public class BlogPostService implements PostServices{
    @Autowired
    private PostRepositpory postRepositpory;
//    @Autowired
//    private ViewService viewService;
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
//    public void addCommentToPost(CommentRequest commentRequest){
//        List<Post> posterList = postRepositpory.findPostsByPoster(commentRequest.getPosterName());
//        for(Post post : posterList){
//            if (post.getTitle().equalsIgnoreCase(commentRequest.getPostTitle( ))){
//                commentServices.save(commentRequest);
//                return;
//            }
//        }
//        throw new PostDoesNotExistException();
//
//    }
//    public void viewPostWith(ViewRequest viewRequest){
//        viewService.viewWith(viewRequest);
//    }
//    @Override
//    public long countViews(ViewsCountRequest viewCountRequest){
//        return viewService.countViewsWith(viewCountRequest);
//    }

    @Override
    public Post findPostBy(String postTitle){
        Post foundPost = new Post();
        List<Post> postList = postRepositpory.findAll();
        for(Post post : postList)
            if(post.getTitle().equalsIgnoreCase(postTitle)){
                foundPost = post;
                break;
            }
        if(foundPost == null) throw new PostDoesNotExistException();
        return foundPost;
    }
}
