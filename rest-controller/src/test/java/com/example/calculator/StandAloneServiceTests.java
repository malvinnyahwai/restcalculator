package com.example.calculator;

import com.example.calculator.dto.ResultDto;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.web.CalculatorRestController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorService.class)
public class StandAloneServiceTests {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void addTests() {
        Double result = calculatorService.add(2.0, 2.0);
        Assertions.assertThat(result).isEqualTo(4.0);
    }

    @Test
    public void subtractTests() {
        Double result = calculatorService.subtract(2.0, 2.0);
        Assertions.assertThat(result).isEqualTo(0.0);
    }

    @Test
    public void multiplyTests() {
        Double result = calculatorService.multiply(2.0, 2.0);
        Assertions.assertThat(result).isEqualTo(4.0);
    }

    @Test
    public void divideTests() {
        Double result = calculatorService.divide(2.0, 2.0);
        Assertions.assertThat(result).isEqualTo(1.0);
    }
}
