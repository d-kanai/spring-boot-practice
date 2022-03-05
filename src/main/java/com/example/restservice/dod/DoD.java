package com.example.restservice.dod;

import javax.persistence.*;

//@IMPROVE: use DB Migration tool
@Entity
// create table dods (id bigint auto_increment, name varchar(255) not null, primary key (id));
@Table(name = "dods")
public class DoD {


    public DoD() {
    }

    public DoD(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

}
