package com.example.restservice.dodRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoDRecordRepository extends JpaRepository<DoDRecord, Integer> {
}
