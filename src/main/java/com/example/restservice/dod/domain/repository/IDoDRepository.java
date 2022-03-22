package com.example.restservice.dod.domain.repository;

import com.example.restservice.dod.domain.DoD;

import java.util.List;

public interface IDoDRepository {

    List<DoD> findAllWithDoDRecord();

    DoD create(DoD dod);
}
