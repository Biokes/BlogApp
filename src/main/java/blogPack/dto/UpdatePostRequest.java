package blogPack.dto;

import lombok.Data;

@Data
public class UpdatePostRequest{
    private String postTitle;
    private String posterUserName;
    private String posterPassword;
    private String postBody;

}
