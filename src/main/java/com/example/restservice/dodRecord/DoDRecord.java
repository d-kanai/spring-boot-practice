package com.example.restservice.dodRecord;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DoDRecord {

    private final int id;
    private final String date;
    private final int value;
    private final String comment;

    public DoDRecord(int id, String date, int value, String comment) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.comment = comment;
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
}
