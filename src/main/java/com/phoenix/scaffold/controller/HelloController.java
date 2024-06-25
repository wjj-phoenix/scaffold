package com.phoenix.scaffold.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjj-phoenix
 * @since 2024-06-25
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping()
    public String test(@RequestParam String username) {
        return username;
    }
}
