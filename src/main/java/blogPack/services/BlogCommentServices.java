package blogPack.services;

import blogPack.data.model.Comment;
import blogPack.data.repositories.CommentRepository;
import blogPack.dto.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.Mappers;

@Service
public class BlogCommentServices implements CommentServices{
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public long countNumberOfComments(){
        return commentRepository.count();
    }
    public void save(CommentRequest commentRequest){
        Comment comment = new Comment();
        Mappers.mapComment(comment, commentRequest);
        commentRepository.save(comment);
    }
    @Override
    public void deleteAll(){
        commentRepository.deleteAll();
    }

    public long countCommentsOnPost(String postTitle, String posterUsername){
        long count = 0l;
        for(Comment comment : commentRepository.findAll()){
            if(comment.getPosterUsername().equalsIgnoreCase(posterUsername) && comment.getPostTitle().equalsIgnoreCase(postTitle))
                count++;
        }
        return count;
    }
}
