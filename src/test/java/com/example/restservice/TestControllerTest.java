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
import com.example.restservice.MockMvcWrapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private MockMvcWrapper http = new MockMvcWrapper();

    @Autowired
    DoDRepository doDRepository;

    @BeforeEach
    private void setup() {
        doDRepository.deleteAll();
    }

    @Nested
    class CreateAPI {
        @Nested
        class Success {
            @Test
            public void create() throws Exception {
                //given
                Map<String, String> params = validParams();
                //when
                ResultActions response = http.post(mockMvc, "/test/dod", params);
                //then
                response.andExpect(jsonPath("$.status").value("success"));
                assertEquals(1, doDRepository.count());
            }
        }

        private Map<String, String> validParams() {
            Map<String, String> params = new HashMap<>() {{
                put("name", "Long Method");
            }};
            return params;
        }
    }



}
