package com.example.restservice.test;

import com.example.restservice.shared.Response;
import com.example.restservice.dod.DoD;
import com.example.restservice.dod.repository.IDoDJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@IMPROVE: use only test env. test db by docker-compose

@RestController
public class TestController {

    @Autowired
    IDoDJpaRepository dodRepository;

    //@IMPROVE: set CORS by config
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
