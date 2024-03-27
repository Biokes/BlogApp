package data.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Post{
    private String title;
    private LocalDate dateCreated;
    private List<Comments>
}
