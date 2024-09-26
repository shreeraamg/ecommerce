package com.example.ecommerce.controller;

import com.example.ecommerce.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class ApiController {

    private final List<String> data;

    public ApiController() {
        data = new ArrayList<>();
        data.add("Java 17");                // 1
        data.add("Spring Boot 3");          // 2
        data.add("Spring Framework 6");     // 3
    }

    @GetMapping
    public ResponseEntity<List<String>> getData() {
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getDataById(@PathVariable int id) {
        if (id <= 0 || id > data.size()) {
            throw new DataNotFoundException("No Data found with given Id");
        }
        return ResponseEntity.status(HttpStatus.OK).body(data.get(id - 1));
    }

}
