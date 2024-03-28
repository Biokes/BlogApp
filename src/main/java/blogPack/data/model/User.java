package blogPack.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Users")
public class User{
    private String firstName;
    private String lastName;
    @DBRef
    private List<Post> post;
    private String userName;
    private String password;
    @Id
    private String id;
}
