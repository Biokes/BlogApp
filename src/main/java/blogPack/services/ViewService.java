package blogPack.services;

import blogPack.dto.ViewsCountRequest;
import org.springframework.stereotype.Service;

@Service
public interface ViewService{

    long countViewsWith(ViewsCountRequest viewCountRequest);
}
