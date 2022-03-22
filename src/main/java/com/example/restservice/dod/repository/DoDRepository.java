package com.example.restservice.dod.repository;

import com.example.restservice.dod.DoD;
import com.example.restservice.dod.IDoDRepository;
import com.example.restservice.dodRecord.DoDRecord;
import com.example.restservice.dodRecord.DoDRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DoDRepository implements IDoDRepository {

    @Autowired
    IDoDJpaRepository jpa;

    @Autowired
    DoDRecordRepository dodRecordRepository;

    public List<DoD> findAllWithDoDRecord() {
        List<DoD> dodList = jpa.findAll();
        for (DoD dod : dodList) {
            List<DoDRecord> dodRecords = dodRecordRepository.findByDodId(dod.getId());
            dod.setDoDRecords(dodRecords);
        }
        return dodList;
    }
}
