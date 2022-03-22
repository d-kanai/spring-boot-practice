package com.example.restservice.dod.repository;

import com.example.restservice.dod.DoD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoDJpaRepository extends JpaRepository<DoD, Integer> {

}
