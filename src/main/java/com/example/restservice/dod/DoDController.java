package com.example.restservice.dod;

import com.example.restservice.dod.domain.DoD;
import com.example.restservice.dod.domain.repository.IDoDRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoDController {

    @Autowired
    IDoDRepository dodRepository;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/dods")
    public DoDListResponse findAll() {
        return new DoDListResponse(dodRepository.findAllWithDoDRecord());
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping("/dods")
    public DoD create(@RequestBody DoD params) {
        DoD dod = dodRepository.create(params);
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
