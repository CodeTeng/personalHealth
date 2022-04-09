package com.lt.health.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Swagger配置类
 * @author: 狂小腾
 * @date: 2022/3/31 23:03
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     * 多人开发可以设置多个Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                // 配置扫描接口的方式，基于包去扫描
                .apis(RequestHandlerSelectors.basePackage("com.lt.health.controller"))
                // paths()过滤什么路径
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(this.securitySchemes())
                .securityContexts(this.securityContexts());
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://localhost:8000/swagger-ui.html
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("大学生饮食、运动健康管理平台接口文档")
                .termsOfServiceUrl("https://github.com/CodeTeng/personalHealth")
                .version("1.3.1")
                .contact(new Contact("狂小腾", "http://localhost:8000/doc.html", "liteng2002823@outlook.com"))
                .build();
    }

    /**
     * 设置请求的信息
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList<>();
        ApiKey key = new ApiKey("Authorization", "Authorization", "Header");
        list.add(key);
        return list;
    }

    /**
     * 配置security对swagger测试的权限 过滤不需要进行验证的页面
     */
    public List<SecurityContext> securityContexts() {
        List<SecurityContext> list = new ArrayList<>();
        list.add(getSecurityContext());
        return list;
    }

    /**
     * 得到授权路径
     */
    private SecurityContext getSecurityContext() {
        return SecurityContext
                .builder()
                .securityReferences(securityReferences())
                // 带有auth的页面将不用提供token即可访问
//                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .forPaths(PathSelectors.regex("hello/.*"))
                .build();
    }

    /**
     * 给授权swagger，可以进行接口测试 全局的token配置
     */
    private List<SecurityReference> securityReferences() {
        List<SecurityReference> list = new ArrayList<>();
        //授权范围 全局
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = scope;
        list.add(new SecurityReference("Authorization", scopes));
        return list;
    }
}
