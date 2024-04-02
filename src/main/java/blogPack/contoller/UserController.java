package blogPack.contoller;


import blogPack.dto.RegisterRequest;
import blogPack.exception.BlogExceptions;
import blogPack.services.BlogUserService;
import blogPack.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController{
    private UserServices userServices;
    @PostMapping("/Blog-Register_user")
    public String registerUser(@RequestBody RegisterRequest registerRequest){
        try{
           userServices.createUser(registerRequest);
        }catch(BlogExceptions exceptions){
            return exceptions.getMessage();
        }
        return "successfully created";
    }


}

