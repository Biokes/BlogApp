package blog.services;

import blog.data.model.Comment;
import blog.data.repositories.CommentRepository;
import blog.dto.requests.CommentRequest;
import blog.dto.requests.DeleteCommentRequest;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import blog.utilities.Mappers;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BlogCommentServices implements CommentServices{
    public long countNumberOfComments(){
        return commentRepository.count();
    }
    public void save(CommentRequest commentRequest){
        Comment comment = new Comment();
        Mappers.mapComment(comment, commentRequest);
        commentRepository.save(comment);
    }
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
    public void deleteCommentsOnPost(DeleteCommentRequest deleteCommentRequest){
       List<Comment> commentList = findPostComments(deleteCommentRequest);
       for(Comment comment : commentList){
           commentRepository.delete(comment);
        }
    }
    private List<Comment> findPostComments(DeleteCommentRequest deleteCommentRequest){
        List<Comment> allComments = commentRepository.findAll();
        List<Comment> postComments = new ArrayList<>();
        for(Comment comment : allComments){
            if(comment.getPosterUsername().equalsIgnoreCase(deleteCommentRequest.getPosterUsername())
                && comment.getPostTitle().equalsIgnoreCase(deleteCommentRequest.getPostTitle())){
                postComments.add(comment);
            }
        }
        System.out.println(postComments.size( ));
        return postComments;
    }
    private CommentRepository commentRepository;

}
