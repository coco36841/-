package com.cmt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:HelloController
 * Description:
 *
 * @Date:2021/10/9 14:53
 * @Author:cmt
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello world";
    }
}
