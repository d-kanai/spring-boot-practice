package com.example.restservice.dodRecord;

import javax.persistence.*;

//@IMPROVE: use DB Migration tool
@Entity
// create table dod_records (id bigint auto_increment, dod_id bigint, date datetime not null, value varchar(255) not null, comment varchar(255), primary key (id), foreign key (dod_id) references dods(id) on delete cascade);
@Table(name = "dod_records")

public class DoDRecordEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    public String date;
    @Column(nullable = false)
    public int value;
    @Column()
    public String comment;
    @Column()
    public int dodId;

    public DoDRecordEntity() {
    }

    public DoDRecordEntity(int dodId, String date, int value, String comment) {
        this.dodId = dodId;
        this.date = date;
        this.value = value;
        this.comment = comment;
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

    public int getId() {
        return id;
    }
}
