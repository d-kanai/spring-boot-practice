package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    public static List<Article> items = new ArrayList<>();

    public void create(Article article) {
        items.add(article);
    }

    public List<Article> search() {
        return items;
    }
}
