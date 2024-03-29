package blogPack.dto;

import lombok.Data;

@Data
public class DeletePostRequest{
    private String password;
    private String postTitle;
}
