package com.example.restservice.dodRecord.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DoDRecord {

    private final int id;
    private final String date;
    private final int value;
    private final String comment;
    private int dodId;

    public DoDRecord(int id, String date, int value, String comment, int dodId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.dodId = dodId;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    public int getDodId() {
        return dodId;
    }
}
