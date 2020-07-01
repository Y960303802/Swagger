package com.xizi.Swagger.config;


import com.xizi.Swagger.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger

public class SwaggerConfig {


    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    //配置Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //获取项目的环境  判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("戏子")
                //是否启动Swagger
                .enable(flag)
                .select()

                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any() :扫描全部
                //none():不扫描
                //withClassAnnotation()：扫描类上的注解  参数是一个注解的反射对象
                //withMethodAnnotation :扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.xizi.Swagger.controller"))
                //paths过滤的路径
//                .paths(PathSelectors.ant("/xizi/**"))
                .build();
    }

    //配置Swagger信息的apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact contact = new Contact("戏子", "https://www.yinpeng.fun/", "960303802@qq.com");
        return new ApiInfo("戏子的SwaggerAPI文档",
                "未来可期",
                "v1.0",
                "https://www.yinpeng.fun/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
