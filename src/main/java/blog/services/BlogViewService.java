package blog.services;

import blog.data.model.User;
import blog.data.model.Views;
import blog.data.repositories.ViewRepository;
import blog.dto.requests.DeleteViewRequest;
import blog.dto.requests.ViewRequest;
import blog.dto.requests.ViewsCountRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import blog.utilities.Mappers;

import java.util.ArrayList;
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
        List<Views> allViews = findpostViews(deleteViewRequest);
            viewRepository.deleteAll(allViews);
    }
    public List<Views> getViewsWith(ViewRequest viewRequest){
        List<Views> viewsList = new ArrayList<>();
        for(Views view: viewRepository.findAll()){
            if(view.getPostTitle().equalsIgnoreCase(viewRequest.getPostTitle( ))
            &&
            view.getPosterUsername().equalsIgnoreCase(viewRequest.getPosterUsername( ))){
//                view.set
                viewsList.add(view);
            }
        }
        return viewsList;
    }
    public void deleteAll(){
        viewRepository.deleteAll();
    }
    private List<Views> findpostViews(DeleteViewRequest deleteViewRequest){
        List<Views> allViews= viewRepository.findAll();
        List<Views> postViews = new ArrayList<>();
        for(Views view : allViews){
            if(view.getPosterUsername().equals(deleteViewRequest.getPosterUsername())
                       &&
                       view.getPostTitle().equalsIgnoreCase(deleteViewRequest.getPostTitle( ))){
                postViews.add(view);
            }
        }
        return postViews;
    }
    private ViewRepository viewRepository;
}
