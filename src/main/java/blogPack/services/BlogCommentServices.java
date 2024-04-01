package blogPack.services;

import blogPack.data.model.Comment;
import blogPack.data.repositories.CommentRepository;
import blogPack.dto.CommentRequest;
import blogPack.dto.DeleteCommentRequest;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import utilities.Mappers;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogCommentServices implements CommentServices{
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
        long count = 0;
        for(Comment comment : commentRepository.findAll()){
            if(comment.getPosterUsername().equalsIgnoreCase(posterUsername)
                       && comment.getPostTitle().equalsIgnoreCase(postTitle))
                count++;
        }
        return count;
    }

    @Override
    public void deleteCommentsOnPost(DeleteCommentRequest deleteCommentRequest){
        List<Comment> commentsFound = commentRepository.findAll();
        for(Comment comment : commentsFound){
            if(comment.getPosterUsername().equalsIgnoreCase(deleteCommentRequest.getPosterUsername())
                       &&
            comment.getPostTitle().equalsIgnoreCase(deleteCommentRequest.getPostTitle())){
                commentRepository.delete(comment);
                commentsFound = commentRepository.findAll();
            }
        }
    }
}
