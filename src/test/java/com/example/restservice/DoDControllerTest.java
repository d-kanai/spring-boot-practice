package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DoDControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private MockMvcWrapper http = new MockMvcWrapper();

    @Autowired
    DoDRepository dodRepository;

    @BeforeEach
    private void setup() {
        dodRepository.deleteAll();
    }

    @Nested
    class ListAPI {
        @Test
        public void success() throws Exception {
            //given
            dodRepository.save(new DoD("Long Method"));
            //when
            ResultActions response = http.get(mockMvc, "/dods");
            //then
            response.andExpect(jsonPath("$.[0].name").value("Long Method"));
        }
    }

}
