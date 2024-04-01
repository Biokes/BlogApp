package blogPack.services;

import blogPack.data.model.User;
import blogPack.dto.DeleteViewRequest;
import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import org.springframework.stereotype.Service;

@Service
public interface ViewService{
    long countViewsWith(ViewsCountRequest viewCountRequest);
    void viewWith(ViewRequest viewRequest, User userGiven);

    long count();

    void deleteViewsWith(DeleteViewRequest deleteViewRequest);
}
