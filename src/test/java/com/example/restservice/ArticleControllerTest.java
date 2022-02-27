package com.example.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ArticleRepository articleRepository;

    @BeforeEach
    private void setup() {
        articleRepository.deleteAll();
    }

    @Nested
    class CreateAPI {
        @Nested
        class Success {
            @Test
            public void createArticle() throws Exception {
                //given
                Map<String, String> params = validParams();
                //when
                ResultActions response = post("/articles", params);
                //then
                response.andExpect(jsonPath("$.status").value("success"));
                assertEquals(1, articleRepository.count());
                assertEquals("I love beer.", articleRepository.findAll().get(0).getTitle());
                assertEquals("it's great.", articleRepository.findAll().get(0).getBody());
            }
        }

        @Nested
        class Invalid {
            @Test
            public void titleTooLong() throws Exception {
                //given
                Map<String, String> params = validParams();
                params.put("title", "a".repeat(21));
                //when
                ResultActions response = post("/articles", params);
                //then
                response.andExpect(jsonPath("$.status").value("error"));
                assertEquals(0, articleRepository.count());
            }

            @Test
            public void bodyTooLong() throws Exception {
                //given
                Map<String, String> params = validParams();
                params.put("body", "a".repeat(101));
                //when
                ResultActions response = post("/articles", params);
                //then
                response.andExpect(jsonPath("$.status").value("error"));
                assertEquals(0, articleRepository.count());
            }
        }
    }

    @Nested
    class SearchAPI {
        @Test
        public void searchAll() throws Exception {
            //given
            articleRepository.save(new Article("I love beer.", "It's great."));
            //when
            ResultActions response = get("/articles");
            //then
            response.andExpect(jsonPath("$.[0].title").value("I love beer."));
            response.andExpect(jsonPath("$.[0].body").value("It's great."));
        }
    }

    private Map<String, String> validParams() {
        Map<String, String> params = new HashMap<>() {{
            put("title", "I love beer.");
            put("body", "it's great.");
        }};
        return params;
    }

    private ResultActions get(String url) throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(print());
        resultActions.andExpect(status().isOk());
        return resultActions;
    }

    private ResultActions post(String url, Map<String, String> params) throws Exception {
        var objectMapper = new ObjectMapper();
        ResultActions perform = this.mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(objectMapper.writeValueAsString(params))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
        perform.andExpect(status().isOk());
        return perform;
    }


}
