package blog.dto.requests;

import lombok.Data;

@Data
public class DeleteCommentRequest{
    private String posterUsername;
    private String postTitle;
}
