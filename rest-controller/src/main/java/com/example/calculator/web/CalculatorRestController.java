package com.example.calculator.web;

import com.example.calculator.dto.ResultDto;
import com.example.calculator.service.CallbackClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/calculator")
public class CalculatorRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(CalculatorRestController.class);
    private final CallbackClient callbackClient;

    public CalculatorRestController(CallbackClient callbackClient) {
        this.callbackClient = callbackClient;
    }

    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestParam(name = "a") Double a, @RequestParam(name = "b") Double b) {
        LOGGER.info("Received a {} and b {} for addition", a, b);

        ResultDto resultDto = callbackClient.sendExpressionAsynchronouslyWithCallback(a, b, '+');

        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("/subtract")
    public ResponseEntity<?> subtract(@RequestParam(name = "a") Double a, @RequestParam(name = "b") Double b) {
        LOGGER.info("Received a {} and b {} for subtract", a, b);

        ResultDto resultDto = callbackClient.sendExpressionAsynchronouslyWithCallback(a, b, '-');

        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("/multiply")
    public ResponseEntity<?> multiply(@RequestParam(name = "a") Double a, @RequestParam(name = "b") Double b) {
        LOGGER.info("Received a {} and b {} for multiply", a, b);

        ResultDto resultDto = callbackClient.sendExpressionAsynchronouslyWithCallback(a, b, '*');

        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("/divide")
    public ResponseEntity<?> divide(@RequestParam(name = "a") Double a, @RequestParam(name = "b") Double b) {
        LOGGER.info("Received a {} and b {} for divide", a, b);

        ResultDto resultDto = callbackClient.sendExpressionAsynchronouslyWithCallback(a, b, '/');

        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
