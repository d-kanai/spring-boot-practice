package com.example.restservice.dodRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoDRecordJpaRepository extends JpaRepository<DoDRecordEntity, Integer> {

    List<DoDRecordEntity> findByDodId(int dodId);
}
