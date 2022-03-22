package com.example.restservice.dod;

import com.example.restservice.dod.DoD;

import java.util.List;

public interface IDoDRepository {

    List<DoD> findAllWithDoDRecord();

}
