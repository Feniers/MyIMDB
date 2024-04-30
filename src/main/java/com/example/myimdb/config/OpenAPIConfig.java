package com.example.myimdb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyIMDB API文档")
                        .description("实训项目API文档")
//                        .contact(new Contact().name("作者").email("邮箱").url("可以写你的博客地址或不填"))
                        .version("v2.0"));
    }
}
