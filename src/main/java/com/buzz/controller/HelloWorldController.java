package com.buzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/hello/{name}")
    @ResponseBody
    public String index(@PathVariable String name) {
        if (name == null) {
            name = "alice";
        }
        return "hello world " + name;
    }

    @RequestMapping("/ping")
    @ResponseBody
    public String ping() {
        return "ping";
    }
}
