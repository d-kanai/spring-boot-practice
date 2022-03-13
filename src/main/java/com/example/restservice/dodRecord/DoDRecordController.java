package com.example.restservice.dodRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoDRecordController {

    @Autowired
    DoDRecordRepository dodRecordRepository;

    @CrossOrigin(origins = {"*"})
    @PostMapping("/dodRecords")
    public DoDRecord create(@RequestBody DoDRecord params) {
        DoDRecord dodRecord = dodRecordRepository.save(params);
        return dodRecord;
    }
}
