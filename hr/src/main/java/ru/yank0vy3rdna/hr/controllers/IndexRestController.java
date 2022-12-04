package ru.yank0vy3rdna.hr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexRestController {
    @GetMapping("hello/{name}")
    public ResponseEntity<String> index(@PathVariable String name) {
        return ResponseEntity.ok("Hello, " + name);
    }
}
