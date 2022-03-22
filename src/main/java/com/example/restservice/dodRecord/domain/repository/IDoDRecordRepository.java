package com.example.restservice.dodRecord.domain.repository;

import com.example.restservice.dodRecord.domain.DoDRecord;

import java.util.List;

public interface IDoDRecordRepository {
    List<DoDRecord> findByDodId(int dodId);

    DoDRecord create(DoDRecord dodRecord);
}
