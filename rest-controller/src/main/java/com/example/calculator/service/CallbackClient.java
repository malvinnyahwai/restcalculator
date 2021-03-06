package com.example.calculator.service;

import com.example.calculator.dto.ControlClass;
import com.example.calculator.dto.ExpressionDto;
import com.example.calculator.dto.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
public class CallbackClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(CallbackClient.class);

    private final AsyncRabbitTemplate asyncRabbitTemplate;
    private final DirectExchange directExchange;
    private static final String ROUTING_KEY = "expression";

    public CallbackClient(AsyncRabbitTemplate asyncRabbitTemplate, DirectExchange directExchange) {
        this.asyncRabbitTemplate = asyncRabbitTemplate;
        this.directExchange = directExchange;
    }

    public ResultDto sendExpressionAsynchronouslyWithCallback(Double a, Double b, char operator) {
        synchronized (ControlClass.class) {
            LOGGER.info("Sending expression {} {} {} to queue", a, operator, b);

            ControlClass.mdId = MDC.get("UNIQUE_ID");

            ExpressionDto expressionDto = new ExpressionDto(a, b, operator);

            ResultDto resultDto = null;

            AsyncRabbitTemplate.RabbitConverterFuture<ResultDto> rabbitConverterFuture;

            try {
                rabbitConverterFuture = asyncRabbitTemplate.convertSendAndReceiveAsType(
                        directExchange.getName(),
                        ROUTING_KEY,
                        expressionDto,
                        new ParameterizedTypeReference<ResultDto>() {
                        });

                if (MDC.getCopyOfContextMap() == null)
                    MDC.put("UNIQUE_ID", ControlClass.mdId);

                rabbitConverterFuture.addCallback(new ListenableFutureCallback<ResultDto>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        LOGGER.error("Cannot get response for expression {}", expressionDto, ex);
                    }

                    @Override
                    public void onSuccess(ResultDto registrationDto) {
                        LOGGER.info("Expression received {}", expressionDto);
                    }
                });

                resultDto = rabbitConverterFuture.get();

            } catch (InterruptedException | ExecutionException exception) {
                LOGGER.error("Error with sending request to queue, {}", exception.getMessage());
            }

            return resultDto;
        }
    }
}
