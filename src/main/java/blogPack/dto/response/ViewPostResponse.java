package blogPack.dto.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ViewPostResponse{
    private String postTitle;
    private String postbody;
    private LocalDate dateCreated;
    private long viewersCount;
    private String views;
}
