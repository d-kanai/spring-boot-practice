package com.example.restservice.article;

import com.example.restservice.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> search() {
        return articleRepository.findAll();
    }

    @PostMapping("/articles")
    public Response create(@RequestBody Article article) {
        if (article.getTitle().length() > 20) {
            return new Response("error");
        }
        if (article.getBody().length() > 100) {
            return new Response("error");
        }
        articleRepository.save(article);
        return new Response("success");
    }

}
