package cn.lastlysly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2018-06-25 14:53
 **/
@RestController
public class HelloController {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World";
    }
}
