package data.model;

import lombok.Data;
import org.springframework.data.annotation.id;
@Data
public class Comments{
    private String id;
    private User commenter;
}
