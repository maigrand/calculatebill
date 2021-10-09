package com.maigrand.calculatebill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({
        springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class,
        springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class
})
public class SwaggerConfig {


}
