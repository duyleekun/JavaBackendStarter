package vn.tripath.backend_demo.dto.authentication;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
