package com.example.protosocket;

import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {

    private final RestTemplateBuilder restTemplateBuilder;

    public HelloController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @GetMapping("/")
    public String index() {
       return "<h1>Hello!</h1>";
    }

    @PostMapping("/api/{path}")
    public Map<String, Object> api(@PathVariable("path") String path, @RequestBody Object obj) {
        Map<String, Object> res = new HashMap<String, Object>();
        switch(path)
        {
            case "adduser":
                res.put("message", "OK");
                res.put("code", 200);
                res.put("userid", UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE);
                return res;
            case "getuser":
                res.put("message", "OK");

                return res;
            default:
                res.put("message", "Unknown ");
                return res;
        }
    }
}