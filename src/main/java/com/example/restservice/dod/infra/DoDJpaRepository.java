package com.example.restservice.dod.infra;

import com.example.restservice.dod.domain.DoD;
import com.example.restservice.dodRecord.domain.DoDRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoDJpaRepository extends JpaRepository<DoDEntity, Integer> {

}
