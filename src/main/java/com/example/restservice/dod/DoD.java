package com.example.restservice.dod;

import com.example.restservice.dodRecord.DoDRecord;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

//@IMPROVE: use DB Migration tool
@Entity
// create table dods (id bigint auto_increment, name varchar(255) not null, primary key (id));
@Table(name = "dods")
public class DoD {

    public DoD() {
    }

    @Transient
    @JsonProperty("data")
    private List<DoDRecord> dodRecords;

    public DoD(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setDoDRecords(List<DoDRecord> dodRecords) {
        this.dodRecords = dodRecords;
    }
}
