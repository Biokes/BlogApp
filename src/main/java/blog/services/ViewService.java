package blog.services;

import blog.data.model.User;
import blog.data.model.Views;
import blog.dto.requests.DeleteViewRequest;
import blog.dto.requests.ViewRequest;
import blog.dto.requests.ViewsCountRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViewService{
    long countViewsWith(ViewsCountRequest viewCountRequest);
    void viewWith(ViewRequest viewRequest, User userGiven);
    long count();
    void deleteViewsWith(DeleteViewRequest deleteViewRequest);
    List<Views> getViewsWith(ViewRequest viewRequest);
    void deleteAll();
}
