package com.example.restservice.dod.domain.repository;

import com.example.restservice.dod.infra.DoD;

import java.util.List;

public interface IDoDRepository {

    List<DoD> findAllWithDoDRecord();

}
