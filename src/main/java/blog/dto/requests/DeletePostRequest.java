package blog.dto.requests;

import lombok.Data;

@Data
public class DeletePostRequest{
    private String posterUserName;
    private String postTitle;
    private String password;
}
