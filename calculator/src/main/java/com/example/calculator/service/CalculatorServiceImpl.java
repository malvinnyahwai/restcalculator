package com.example.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final Logger LOGGER = LoggerFactory.getLogger(CalculatorServiceImpl.class);
    private final int PRECISION_SIGNED_DECIMALS = 2;

    @Override
    public Double add(Double a, Double b) {
        LOGGER.info("Performing {} + {}", a, b);
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        LOGGER.info("Performing {} - {}", a, b);
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        LOGGER.info("Performing {} * {}", a, b);
        BigDecimal result = BigDecimal.valueOf(a).multiply(BigDecimal.valueOf(b), new MathContext(PRECISION_SIGNED_DECIMALS + 1, RoundingMode.DOWN));
        return result.doubleValue();
    }

    @Override
    public Double divide(Double a, Double b) {
        LOGGER.info("Performing {} / {}", a, b);
        BigDecimal dividend = new BigDecimal(a);
        BigDecimal quotient = dividend.stripTrailingZeros().divide(BigDecimal.valueOf(b), new MathContext(PRECISION_SIGNED_DECIMALS, RoundingMode.DOWN));
        return quotient.doubleValue();
    }
}
