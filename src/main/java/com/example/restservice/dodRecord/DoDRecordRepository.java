package com.example.restservice.dodRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoDRecordRepository extends JpaRepository<DoDRecord, Integer> {

    List<DoDRecord> findByDodId(int dodId);
}
