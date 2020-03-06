package vn.tripath.backend_demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.tripath.attribute_resolver.CurrentUserAttributeResolver;

import java.util.List;

@Configuration
@Import({CurrentUserAttributeResolver.class})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    CurrentUserAttributeResolver currentUserAttributeResolver;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserAttributeResolver);
    }
}
