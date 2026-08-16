package lk.ijse.springboot.microservice.userservice.dto;

import lk.ijse.springboot.microservice.userservice.entity.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
}
