package com.example.restservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @PostMapping("/articles")
    public Response create(@RequestBody Article article) {
        if (article.title.length() > 20) {
            return new Response("error");
        }
        if (article.body.length() > 100) {
            return new Response("error");
        }
        new ArticleRepository().create(article);
        return new Response("success");
    }

}
