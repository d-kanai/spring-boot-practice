package com.example.restservice.dodRecord;

import com.example.restservice.dodRecord.domain.DoDRecord;
import com.example.restservice.dodRecord.domain.repository.IDoDRecordRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoDRecordController {

    @Autowired
    IDoDRecordRepository dodRecordRepository;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/dods/{dodId}/records")
    public DoDRecordListResponse list(@PathVariable int dodId) {
        List<DoDRecord> dodRecord = dodRecordRepository.findByDodId(dodId);
        return new DoDRecordListResponse(dodRecord);
    }

    @CrossOrigin(origins = {"*"})
    @PostMapping("/dodRecords")
    public DoDRecord create(@RequestBody DoDRecord params) {
        DoDRecord dodRecord = dodRecordRepository.create(params);
        return dodRecord;
    }

    private class DoDRecordListResponse {
        @JsonProperty("items")
        private List<DoDRecord> dodRecordList;

        public DoDRecordListResponse(List<DoDRecord> dodRecordList) {
            this.dodRecordList = dodRecordList;
        }
    }
}
