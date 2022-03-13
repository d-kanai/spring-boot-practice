package com.example.restservice.dodRecord;

import com.example.restservice.MockMvcWrapper;
import com.example.restservice.dod.DoD;
import com.example.restservice.dod.DoDRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DoDRecordControllerTest {
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
    class CreateApi {
        @Test
        public void success() throws Exception {
            //given
            DoD dod = dodRepository.save(new DoD("Long Method"));
            HashMap params = new HashMap() {{
                put("dodId", dod.getId());
                put("date", "2020-01-01");
                put("value", "30");
                put("comment", "Add new feature");
            }};
            //when
            ResultActions response = http.post(mockMvc, "/dodRecords", params);
            //then
            response.andExpect(jsonPath("$.dodId").value(dod.getId()));
            response.andExpect(jsonPath("$.date").value("2020-01-01"));
            response.andExpect(jsonPath("$.value").value("30"));
            response.andExpect(jsonPath("$.comment").value("Add new feature"));
        }
    }
}
