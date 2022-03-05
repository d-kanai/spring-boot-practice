package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DoDRepository dodRepository;

    @CrossOrigin(origins = {"*"})
    @PostMapping("/test/reset")
    public Response deleteAll() {
        dodRepository.deleteAll();
        return new Response("success");
    }


    @CrossOrigin(origins = {"*"})
    @PostMapping("/test/dod")
    public Response createDoD(@RequestBody DoD dod) {
        dodRepository.save(dod);
        return new Response("success");
    }

}
