package com.example.restservice.dod.domain;

import com.example.restservice.dodRecord.DoDRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
public class DoD {
    private final int id;
    private final String name;
    @JsonProperty("data")
    private List<DoDRecord> dodRecords;

    public DoD(int id, String name, List<DoDRecord> dodRecords) {
        this.id = id;
        this.name = name;
        this.dodRecords = dodRecords;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DoDRecord> getDodRecords() {
        return dodRecords;
    }
}
