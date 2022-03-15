package com.example.restservice.dod;

import com.example.restservice.MockMvcWrapper;
import com.example.restservice.dod.DoD;
import com.example.restservice.dod.DoDRepository;
import com.example.restservice.dodRecord.DoDRecord;
import com.example.restservice.dodRecord.DoDRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

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
    @Autowired
    DoDRecordRepository dodRecordRepository;

    @BeforeEach
    private void setup() {
        dodRepository.deleteAll();
    }

    @Nested
    class ListAPI {
        @Test
        public void success() throws Exception {
            //given
            //@IMPROVE: extract to DataBuilder
            DoD dod = dodRepository.save(new DoD("Long Method"));
            dodRecordRepository.save(new DoDRecord(dod.getId(), "2020-01-01", 100, "new feature"));
            //when
            ResultActions response = http.get(mockMvc, "/dods");
            //then
            response.andExpect(jsonPath("$.[0].name").value("Long Method"));
            response.andExpect(jsonPath("$.[0].data.[0].date").value("2020-01-01"));
        }
    }

    @Nested
    class CreateApi {
        @Test
        public void success() throws Exception {
            //given
            HashMap params = new HashMap() {{
                put("name", "Long Method");
            }};
            //when
            ResultActions response = http.post(mockMvc, "/dods", params);
            //then
            response.andExpect(jsonPath("$.name").value("Long Method"));
        }
    }
}
