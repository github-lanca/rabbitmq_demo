package com.example.rabbitmq_demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author meihongliang
 * @since 2020/5/11 14:29
 */
@Configurable
@EnableSwagger2
public class Swagger2 {
    /**
     * swagger2的配置文件,配置基本内容,比如扫描的包
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.rabbitmq_demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 挨批文档的详细信息函数,注意这里的注解引用
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MeiHongliang's 消息队列测试")
                .contact(new Contact("lanca", "lanca.io", "13752935158@163.COM"))
                .version("1.0.0")
                .description("消息队列,swagger-ui")
                .build();
    }
}
