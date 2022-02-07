/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setup() {
        ArticleRepository.items = new ArrayList<>();
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
                assertEquals(1, ArticleRepository.items.size());
                assertEquals("I love beer.", ArticleRepository.items.get(0).title());
                assertEquals("it's great.", ArticleRepository.items.get(0).body());
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
                assertEquals(0, ArticleRepository.items.size());
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
                assertEquals(0, ArticleRepository.items.size());
            }
        }
    }

    @Nested
    class SearchAPI {
        @Test
        public void searchAll() throws Exception {
            //given
            ArticleRepository.items = new ArrayList<>();
            ArticleRepository.items.add(new Article("I love beer.", "It's great."));
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
