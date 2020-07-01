package com.xizi.Swagger.controller;


import com.xizi.Swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }


    //只要我们的接口中，返回值存在实体类，他就会被扫描到swagger中
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }

    @GetMapping("/hello2")
    @ApiOperation("Hello控制类")
    public String hello(@ApiParam("用户名") String username){
        return "hello"+username;
    }

    @PostMapping("/postt")
    @ApiOperation("Post测试")
    public User postt(@ApiParam("用户名") User user){
        return user;
    }
}
