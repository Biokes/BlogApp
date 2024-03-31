package blogPack.dto;

import lombok.Data;

@Data
public class ViewsCountRequest{
    private String postTitle;
    private String viewerUsername;
    private String posterUsername;
}
