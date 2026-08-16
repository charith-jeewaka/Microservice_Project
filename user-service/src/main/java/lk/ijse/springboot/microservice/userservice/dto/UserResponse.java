package lk.ijse.springboot.microservice.userservice.dto;

import lk.ijse.springboot.microservice.userservice.entity.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;
}
