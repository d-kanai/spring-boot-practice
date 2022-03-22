package com.example.restservice.dod.infra;

import javax.persistence.*;

//@IMPROVE: use DB Migration tool
@Entity
// create table dods (id bigint auto_increment, name varchar(255) not null, primary key (id));
@Table(name = "dods")
public class DoDEntity {
    @Id
    @GeneratedValue
    public int id;
    @Column(nullable = false)
    private String name;

    public DoDEntity() {
    }

    public DoDEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
