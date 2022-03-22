package com.example.restservice.dod.infra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoDJpaRepository extends JpaRepository<DoDEntity, Integer> {

}
