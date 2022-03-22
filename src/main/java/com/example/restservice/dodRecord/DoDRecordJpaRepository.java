package com.example.restservice.dodRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoDRecordJpaRepository extends JpaRepository<DoDRecord, Integer> {

    List<DoDRecord> findByDodId(int dodId);
}
