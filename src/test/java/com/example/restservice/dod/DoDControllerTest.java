package com.example.restservice.dod;

import com.example.restservice.MockMvcWrapper;
import com.example.restservice.dod.infra.DoDEntity;
import com.example.restservice.dod.infra.DoDJpaRepository;
import com.example.restservice.dodRecord.infra.DoDRecordEntity;
import com.example.restservice.dodRecord.infra.DoDRecordJpaRepository;
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
    DoDJpaRepository dodRepository;
    @Autowired
    DoDRecordJpaRepository dodRecordRepository;

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
            DoDEntity dod = dodRepository.save(new DoDEntity("Long Method"));
            dodRecordRepository.save(new DoDRecordEntity(dod.getId(), "2020-01-01", 100, "new feature"));
            //when
            ResultActions response = http.get(mockMvc, "/dods");
            //then
            response.andExpect(jsonPath("$.items.[0].id").isNotEmpty());
            response.andExpect(jsonPath("$.items.[0].name").value("Long Method"));
            response.andExpect(jsonPath("$.items.[0].data.[0].date").value("2020-01-01 00:00:00"));
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
