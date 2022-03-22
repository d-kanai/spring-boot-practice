package com.example.restservice.dodRecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoDRecordController {

    @Autowired
    DoDRecordJpaRepository dodRecordRepository;

    @CrossOrigin(origins = {"*"})
    @GetMapping("/dods/{dodId}/records")
    public DoDRecordListResponse list(@PathVariable int dodId) {
        List<DoDRecordEntity> dodRecord = dodRecordRepository.findByDodId(dodId);
        return new DoDRecordListResponse(dodRecord);
    }
    @CrossOrigin(origins = {"*"})
    @PostMapping("/dodRecords")
    public DoDRecordEntity create(@RequestBody DoDRecordEntity params) {
        DoDRecordEntity dodRecordEntity = dodRecordRepository.save(params);
        return dodRecordEntity;
    }

    private class DoDRecordListResponse {
        @JsonProperty("items")
        private List<DoDRecordEntity> dodRecordList;

        public DoDRecordListResponse(List<DoDRecordEntity> dodRecordList) {
            this.dodRecordList = dodRecordList;
        }
    }
}
