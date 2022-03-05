package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoDController {

    @Autowired
    DoDRepository dodRepository;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/dods")
    public List<DoD> findAll() {
        return dodRepository.findAll();
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping("/dods")
    public DoD create(@RequestBody DoD params) {
        DoD dod = dodRepository.save(params);
        return dod;
    }


}
