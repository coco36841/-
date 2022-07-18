package com.cmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * ClassName:SwaggerConfig
 * Description:
 *
 * @Date:2021/10/9 14:57
 * @Author:cmt
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment){

        Profiles profiles = Profiles.of("dev","test");
        Boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cmt.controller"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("cmt", "zwt.life", "2634360124@qq.com");

        return new ApiInfo("cmt的Api文档",
                "安静",
                "v1.0",
                "zwt.life",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
