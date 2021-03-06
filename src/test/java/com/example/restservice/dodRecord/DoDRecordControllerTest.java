package com.example.restservice.dodRecord;

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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DoDRecordControllerTest {
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
    class CreateApi {
        @Test
        public void success() throws Exception {
            //given
            DoDEntity dod = dodRepository.save(new DoDEntity("Long Method"));
            HashMap params = new HashMap() {{
                put("dodId", dod.getId());
                put("date", "2020-01-01");
                put("value", "30");
                put("comment", "Add new feature");
            }};
            //when
            ResultActions response = http.post(mockMvc, "/dodRecords", params);
            //then
            response.andExpect(jsonPath("$.dodId").value(dod.getId()))
                    .andExpect(jsonPath("$.date").value("2020-01-01"))
                    .andExpect(jsonPath("$.value").value("30"))
                    .andExpect(jsonPath("$.comment").value("Add new feature"));
        }
    }

    @Nested
    class ListApi {
        @Test
        public void success() throws Exception {
            //given
            //@IMPROVE: extract to DataBuilder
            DoDEntity dod = dodRepository.save(new DoDEntity("Long Method"));
            DoDEntity dod2 = dodRepository.save(new DoDEntity("Long Method"));
            dodRecordRepository.save(new DoDRecordEntity(dod.getId(), "2020-01-01", 20, "new feature"));
            dodRecordRepository.save(new DoDRecordEntity(dod2.getId(), "2020-01-01", 20, "new feature"));
            //when
            ResultActions response = http.get(mockMvc, "/dods/" + dod.getId() + "/records");
            //then
            response.andExpect(jsonPath("$.items", hasSize(1)))
                    .andExpect(jsonPath("$.items.[0].id").isNotEmpty())
                    .andExpect(jsonPath("$.items.[0].value").value(20))
                    .andExpect(jsonPath("$.items.[0].date").value("2020-01-01 00:00:00"));
        }
    }
}
