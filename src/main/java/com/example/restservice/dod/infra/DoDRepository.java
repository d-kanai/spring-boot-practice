package com.example.restservice.dod.infra;

import com.example.restservice.dod.domain.DoD;
import com.example.restservice.dod.domain.repository.IDoDRepository;
import com.example.restservice.dodRecord.DoDRecord;
import com.example.restservice.dodRecord.DoDRecordEntity;
import com.example.restservice.dodRecord.DoDRecordJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class DoDRepository implements IDoDRepository {

    @Autowired
    private DoDJpaRepository jpaDoD;

    @Autowired
    private DoDRecordJpaRepository dodRecordRepository;

    public List<DoD> findAllWithDoDRecord() {
        List<DoDEntity> dodList = jpaDoD.findAll();
        List<DoD> dods = new ArrayList<>();
        List<DoDRecord> dodRecords = new ArrayList<>();
        for (DoDEntity dodEntity : dodList) {
            for (DoDRecordEntity dodRecordEntity : dodRecordRepository.findByDodId(dodEntity.getId())) {
                dodRecords.add(new DoDRecord(dodRecordEntity.getId(), dodRecordEntity.getDate(), dodRecordEntity.getValue(), dodRecordEntity.getComment()));
            }
            dods.add(new DoD(dodEntity.getId(), dodEntity.getName(), dodRecords));
        }
        return dods;
    }

    public DoDEntity save(DoDEntity dod) {
        return this.jpaDoD.save(dod);
    }
}
