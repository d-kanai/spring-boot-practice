package com.example.restservice;

import javax.persistence.*;

@Entity
// create table articles (id bigint auto_increment ,title varchar(255) not null, body varchar(255) not null, primary key (id));
@Table(name = "articles")
public class Article {

    public Article() {
    }

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
