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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createArticle() throws Exception {
        //given
        ArticleRepository.items = new ArrayList<>();
        Map<String, String> params = new HashMap<>() {{
            put("title", "I love beer.");
        }};
        //when
        var objectMapper = new ObjectMapper();
        this.mockMvc.perform(
                        post("/articles")
                                .content(objectMapper.writeValueAsString(params))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        //then
        assertEquals(1, ArticleRepository.items.size());
        assertEquals("I love beer.", ArticleRepository.items.get(0).title);
    }

//    @Test
//    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
//        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
//    }

}
