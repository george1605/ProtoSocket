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

    private final PacketRepository packetRepository;
    public HelloController(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @GetMapping("/")
    public String index() {
       return "<h1>Hello!</h1>";
    }

    @GetMapping("/api/{path}")
    public Map<String, Object> getapi(@PathVariable("path") String path) {
        Map<String, Object> res = new HashMap<String, Object>();
        switch(path)
        {
            case "messages":
                res.put("message", "OK");
                res.put("code", 200);
                res.put("response", packetRepository.findAll());
                return res;
            default:
                res.put("message", "Unknown Path");
                return res;
        }
    }

    @PostMapping("/api/{path}")
    public Map<String, Object> api(@PathVariable("path") String path, @RequestBody Object obj) {
        Map<String, Object> res = new HashMap<String, Object>();
        return res;
    }
}