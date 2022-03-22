package com.example.restservice.dod;

import com.example.restservice.dod.repository.DoDRepository;
import com.example.restservice.dod.repository.IDoDJpaRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoDController {

    @Autowired
    DoDRepository dodRepository;

    @Autowired
    IDoDJpaRepository dodJpa;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/dods")
    public DoDListResponse findAll() {
        List<DoD> dodList = dodRepository.findAllWithDoDRecord();
        return new DoDListResponse(dodList);
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping("/dods")
    public DoD create(@RequestBody DoD params) {
        DoD dod = dodJpa.save(params);
        return dod;
    }

    private class DoDListResponse {
        @JsonProperty("items")
        private List<DoD> dodList;

        public DoDListResponse(List<DoD> dodList) {
            this.dodList = dodList;
        }
    }
}
