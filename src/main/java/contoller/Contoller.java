package contoller;

import dto.SignUp;
import services.BlogServices;

public class Contoller{
    private BlogServices service;
    private String sigUp(SignUp signUp){
        service.signUp(signUp);
        return "register Successful";
    }
}
