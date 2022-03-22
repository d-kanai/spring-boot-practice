package com.example.restservice.dod;

import com.example.restservice.dod.domain.DoD;
import com.example.restservice.dod.infra.DoDEntity;
import com.example.restservice.dod.infra.DoDRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoDController {

    @Autowired
    DoDRepository dodRepository;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/dods")
    public DoDListResponse findAll() {
        return new DoDListResponse(dodRepository.findAllWithDoDRecord());
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping("/dods")
    public DoDEntity create(@RequestBody DoDEntity params) {
        DoDEntity dod = dodRepository.save(params);
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
