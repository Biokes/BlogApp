package blog.dto.requests;

import lombok.Data;

@Data
public class CommentDetailsRequest{
    private String postTitle;
    private String posterUsername;
}
