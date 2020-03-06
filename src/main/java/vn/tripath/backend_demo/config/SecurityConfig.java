package vn.tripath.backend_demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.tripath.backend_demo.config.security.MyUserDetailsService;
import vn.tripath.backend_demo.exceptions.CommonErrorResponse;
import vn.tripath.backend_demo.exceptions.ResponseCode;
import vn.tripath.backend_demo.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties({DevConfig.class})
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DevConfig devConfig;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    private UserRepository userRepository;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authentication/login").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll() // We will use SecurityAspect to fine grain Auth
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((this::securityExceptionHandler))
                .accessDeniedHandler(this::securityExceptionHandler)
                .and()
                .headers().frameOptions().sameOrigin();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @SneakyThrows
    private void securityExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception authException) {
//        authException.printStackTrace();
        ObjectMapper objectMapper = new ObjectMapper();
        CommonErrorResponse errorResponse = CommonErrorResponse.builder().code(ResponseCode.ACCESS_DENIED.name()).message(authException.getMessage()).build();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        response.getWriter().close();
    }

}
