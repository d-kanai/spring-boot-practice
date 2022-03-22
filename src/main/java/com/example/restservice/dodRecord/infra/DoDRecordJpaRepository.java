package com.example.restservice.dodRecord.infra;

import com.example.restservice.dodRecord.domain.DoDRecord;
import com.example.restservice.dodRecord.infra.DoDRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoDRecordJpaRepository extends JpaRepository<DoDRecordEntity, Integer> {

    List<DoDRecord> findByDodId(int dodId);
}
