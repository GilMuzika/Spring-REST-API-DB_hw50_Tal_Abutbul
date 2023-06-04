package com.example.demo.service;

import com.example.demo.model.RandomUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//The "random user" didn't work for me,
//so I tried with "dummy-json" ,
//I did everything else using random.

@FeignClient(name = "dummy-json", url = "${dummy-json.url}")
public interface UserServiceClient {
    @GetMapping("/users/{id}")
    RandomUserResponse getShow(@PathVariable Integer id);
    @GetMapping("/randomuser")
    RandomUserResponse getShow();
}
