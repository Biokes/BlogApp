package data.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Post{
    private String title;
    private String content;
    private LocalDate dateCreated;
    private List<Comments> postComments;
    private List<Views> views;
}
