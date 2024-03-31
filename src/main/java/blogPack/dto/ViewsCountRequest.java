package blogPack.dto;

import lombok.Data;

@Data
public class ViewsCountRequest{
    private String postTitle;
    private String posterUsername;

}
