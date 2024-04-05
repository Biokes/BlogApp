package blog.dto.requests;

import lombok.Data;

@Data
public class DeleteViewRequest{
    private String postTitle;
    private String posterUsername;
}
