package com.example.calculator.service;

import com.example.calculator.dto.ControlClass;
import com.example.calculator.dto.ExpressionDto;
import com.example.calculator.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ExpressionConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(ExpressionConsumer.class);

    private final CalculatorService calculatorService;

    public ExpressionConsumer(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RabbitListener(queues = "#{queue.name}", concurrency = "10")
    public ResultDto receive(ExpressionDto expressionDto) {
        if(MDC.getCopyOfContextMap() == null)
            MDC.put("UNIQUE_ID", ControlClass.mdId);

        LOGGER.info("Received an expression {} from queue", expressionDto);

        LOGGER.info("ControlClass mdId value {}", ControlClass.mdId);

        Objects.requireNonNull(expressionDto.getA(), "a in expression cannot be null");
        Objects.requireNonNull(expressionDto.getB(), "b in expression cannot be null");
        Objects.requireNonNull(expressionDto.getOperator(), "operator in expression cannot be null");

        char operator = expressionDto.getOperator();

        Double result = null;

        switch (operator) {
            case '+':
                result = calculatorService.add(expressionDto.getA(), expressionDto.getB());
                break;
            case '-':
                result = calculatorService.subtract(expressionDto.getA(), expressionDto.getB());
                break;
            case '*':
                result = calculatorService.multiply(expressionDto.getA(), expressionDto.getB());
                break;
            case '/':
                result = calculatorService.divide(expressionDto.getA(), expressionDto.getB());
                break;
        }

        LOGGER.info("Evaluated expression {}, and result is {}", expressionDto, result);

        return new ResultDto(result);

    }
}
