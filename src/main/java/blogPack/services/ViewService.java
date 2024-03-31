package blogPack.services;

import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import org.springframework.stereotype.Service;

@Service
public interface ViewService{

    long countViewsWith(ViewsCountRequest viewCountRequest);

    void viewWith(ViewRequest viewRequest);
}
