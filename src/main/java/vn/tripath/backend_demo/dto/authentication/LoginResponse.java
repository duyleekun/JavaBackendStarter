package vn.tripath.backend_demo.dto.authentication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    String accessToken;
}
