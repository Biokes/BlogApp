package blogPack.dto;

import lombok.Data;

@Data
public class CommentRequest{
    private String commentBody;
    private String commenterName;
}
