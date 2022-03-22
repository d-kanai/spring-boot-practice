package com.example.restservice.dod.infra;

import com.example.restservice.dod.domain.repository.IDoDRepository;
import com.example.restservice.dod.infra.DoD;
import com.example.restservice.dod.infra.DoDJpaRepository;
import com.example.restservice.dodRecord.DoDRecord;
import com.example.restservice.dodRecord.DoDRecordJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DoDRepository implements IDoDRepository {

    @Autowired
    private DoDJpaRepository jpaDoD;

    @Autowired
    private DoDRecordJpaRepository dodRecordRepository;

    public List<DoD> findAllWithDoDRecord() {
        List<DoD> dodList = jpaDoD.findAll();
        for (DoD dod : dodList) {
            List<DoDRecord> dodRecords = dodRecordRepository.findByDodId(dod.getId());
            dod.setDoDRecords(dodRecords);
        }
        return dodList;
    }

    public DoD save(DoD dod) {
        return this.jpaDoD.save(dod);
    }
}
