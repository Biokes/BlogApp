package blogPack.dto;

import lombok.Data;

@Data
public class DeleteCommentRequest{
    private String posterUsername;
    private String postTitle;
    public String getPostTitle(){
    }
}
