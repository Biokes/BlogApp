package blogPack.dto;

import lombok.Data;

    @Data
public class RegisterRequest{
    private String firstName;
    private String lastName;
    private String UserName;
    private String password;
}
