package com.example.calculator;

import com.example.calculator.dto.ResultDto;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.service.CallbackClient;
import com.example.calculator.web.CalculatorRestController;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorRestController.class)
public class StandAloneControllerTests {
    @MockBean
    private CallbackClient callbackClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddRestEndpoint() throws Exception {
        Mockito.when(callbackClient.sendExpressionAsynchronouslyWithCallback(2.0, 2.0, '+')).thenReturn(new ResultDto(4.0));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/v1/calculator/add?a=2&b=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result", Matchers.is(4.0)));
    }

    @Test
    public void testSubtractRestEndpoint() throws Exception {
        Mockito.when(callbackClient.sendExpressionAsynchronouslyWithCallback(2.0, 2.0, '-')).thenReturn(new ResultDto(0.0));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/v1/calculator/subtract?a=2&b=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result", Matchers.is(0.0)));
    }

    @Test
    public void testMultiplierRestEndpoint() throws Exception {
        Mockito.when(callbackClient.sendExpressionAsynchronouslyWithCallback(2.0, 2.0, '*')).thenReturn(new ResultDto(4.0));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/v1/calculator/multiply?a=2&b=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result", Matchers.is(4.0)));
    }

    @Test
    public void testDivideRestEndpoint() throws Exception {
        Mockito.when(callbackClient.sendExpressionAsynchronouslyWithCallback(2.0, 2.0, '/')).thenReturn(new ResultDto(1.0));
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/v1/calculator/divide?a=2&b=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("result", Matchers.is(1.0)));
    }
}
