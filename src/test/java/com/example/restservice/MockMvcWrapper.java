package com.example.restservice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MockMvcWrapper {


    public ResultActions get(MockMvc mockMvc, String url) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(print());
        resultActions.andExpect(status().isOk());
        return resultActions;
    }

    public ResultActions post(MockMvc mockMvc, String url, Map<String, String> params) throws Exception {
        var objectMapper = new ObjectMapper();
        ResultActions perform = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                        .content(objectMapper.writeValueAsString(params))
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
        perform.andExpect(status().isOk());
        return perform;
    }

}
