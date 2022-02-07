package com.example.restservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @PostMapping("/articles")
    public String create(@RequestBody Article article) {
        new ArticleRepository().create(article);
        return "hello";
    }

}
