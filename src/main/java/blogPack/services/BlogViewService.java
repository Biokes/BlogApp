package blogPack.services;

import blogPack.data.model.User;
import blogPack.data.model.Views;
import blogPack.data.repositories.UserRepository;
import blogPack.data.repositories.ViewRepository;
import blogPack.dto.DeleteViewRequest;
import blogPack.dto.ViewRequest;
import blogPack.dto.ViewsCountRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utilities.Mappers;

import java.util.List;


@Service
@AllArgsConstructor
public class BlogViewService implements ViewService{
    public long countViewsWith(ViewsCountRequest viewCountRequest){
        long count = 0;
        List<Views> viewsList = viewRepository.findViewsBy(viewCountRequest.getPostTitle());
        for( Views view : viewsList){
            if(view.getPosterUsername().equalsIgnoreCase(viewCountRequest.getPosterUsername())){
                count++;
            }
        }
        return count;
    }
    public void viewWith(ViewRequest viewRequest, User userGiven){
        Views views = new Views();
        views.setViewer(userGiven);
        Mappers.mapView(views, viewRequest);
        viewRepository.save(views);
    }
    public long count(){
        return viewRepository.findAll().size();
    }
    public void deleteViewsWith(DeleteViewRequest deleteViewRequest){

    }
    private ViewRepository viewRepository;
}
