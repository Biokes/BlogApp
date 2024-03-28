package data.model;

import lombok.Data;

import java.util.List;

@Data
public class User{
    private String firstName;
    private String lastName;
    private List<Post> post;
    private String userName;
    private String password;
    private String id;
}
