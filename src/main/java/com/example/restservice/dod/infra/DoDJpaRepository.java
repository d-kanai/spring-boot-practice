package com.example.restservice.dod.infra;

import com.example.restservice.dod.infra.DoD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoDJpaRepository extends JpaRepository<DoD, Integer> {

}
