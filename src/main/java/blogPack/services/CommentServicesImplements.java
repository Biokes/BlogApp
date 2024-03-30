package blogPack.services;

import blogPack.data.model.Comment;
import blogPack.data.repositories.CommentRepository;
import blogPack.dto.CommentRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.MappingException;
import org.springframework.stereotype.Service;
import utilities.Mappers;

@Service
@AllArgsConstructor
public class CommentServicesImplements  implements CommentServices{

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
}
