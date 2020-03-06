package vn.tripath.backend_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "demo.dev")
@Configuration
public class DevConfig {
    private String runAsToken;
    private Boolean sendEmail;
}
