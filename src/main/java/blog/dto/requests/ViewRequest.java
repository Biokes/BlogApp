package blog.dto.requests;

import lombok.Data;

@Data
public class ViewRequest{
    private String posterUsername;
    private String postTitle;
    private String viewerUsername;
}
