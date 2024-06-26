package blog.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document("Views")
public class Views{
    @Id
    private String id;
    private LocalDateTime timeViewed;
    @DBRef
    private User viewer;
    private String postTitle;
    private String posterUsername;
}
