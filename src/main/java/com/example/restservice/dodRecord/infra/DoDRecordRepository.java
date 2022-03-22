package com.example.restservice.dodRecord.infra;

import com.example.restservice.dodRecord.domain.DoDRecord;
import com.example.restservice.dodRecord.domain.repository.IDoDRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DoDRecordRepository implements IDoDRecordRepository {

    @Autowired
    private DoDRecordJpaRepository jpa;

    public List<DoDRecord> findByDodId(int dodId) {
        return this.jpa.findByDodId(dodId);
    }

    public DoDRecord create(DoDRecord dodRecord) {
        DoDRecordEntity dodRecordEntity = this.jpa.save(
                new DoDRecordEntity(
                        dodRecord.getDodId(),
                        dodRecord.getDate(),
                        dodRecord.getValue(),
                        dodRecord.getComment())
        );
        return new DoDRecord(
                dodRecordEntity.getId(),
                dodRecordEntity.getDate(),
                dodRecordEntity.getValue(),
                dodRecordEntity.getComment(),
                dodRecordEntity.getDodId()
        );
    }
}
