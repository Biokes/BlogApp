package blogPack.dto;

import blogPack.data.model.User;
import lombok.Data;

@Data
public class CommentRequest{
    private String commentBody;
    private User commenter;
    private String commenterUsername;
    private String postTitle;
    private String posterName;
}
