package com.gallegos.vodafone.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gallegos.vodafone.model.ElementsList;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
class MissingNumberControllerTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    void calculate() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculate")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"list\":[4,1,3,6,2]}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("missingNumber:5", content);
    }

    @Test
    void calculateSort() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculate_sort")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"list\":[4,1,3,6,2],\"sort\": \"low_high\"}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertEquals("missingNumber:5,\nlist: {1, 2, 3, 4, 6}", content);
    }

    @Test
    void calculateRandom() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculate_random")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"maxValue\":8}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }

    @Test
    void calculateFibonacci() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculate_fibonacci")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"value\":21}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }

    @Test
    void missingNumbers() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculate")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"list\":[4,1,3,6,2]}")).andReturn();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/missing_numbers")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"lastCalculations\":1}")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }

    private ElementsList generateElementsList(){
        ElementsList elementsList = new ElementsList();
        elementsList.setList(List.of(1,3,2,6,5));
        elementsList.setSort("low_high");
        elementsList.setMaxValue(8);
        elementsList.setValue(21);
        elementsList.setLastCalculations(2);

        return elementsList;
    }
}
