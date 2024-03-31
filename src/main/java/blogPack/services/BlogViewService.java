package blogPack.services;

import blogPack.data.model.Views;
import blogPack.data.repositories.UserRepository;
import blogPack.data.repositories.ViewRepository;
import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.Mappers;


@Service
public class BlogViewService implements ViewService{
    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private UserServices userServices;

    @Override
    public long countViewsWith(ViewsCountRequest viewCountRequest){
        long count = 0;
        for( Views view : viewRepository.findViewsBy(viewCountRequest.getPostTitle())){
            if(view.getPosterUsername().equalsIgnoreCase(viewCountRequest.getPosterUsername())){
                count++;
            }
        }
        return count;
    }

    @Override
    public void viewWith(ViewRequest viewRequest){
        Views views = new Views();
        views.setViewer(userServices.findUserBy(viewRequest.getViewerUsername()));
        Mappers.mapView(views, viewRequest);
        viewRepository.save(views);
    }
}