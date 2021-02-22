package com.aurrix.phonebook.global.conf;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
public class SwaggerConf {
  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
  }

  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Phonebook service")
            .description("Simple storage of phone numbers per person record")
            .contact(new Contact("Alisher Urunov","https://www.linkedin.com/in/alisher-urunov/","arurunov@gmail.com"))
            .license("©All Rights Reserved")
            .version("1.00")
            .build();
  }
}
