package blog.dto.requests;

import lombok.Data;

@Data
public class PostRequest{
    private String title;
    private String content;
    private String posterUserName;
}
