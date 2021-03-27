package com.example.calculator.dto;

public class ResultDto {
    private Double result;

    public ResultDto() {

    }

    public ResultDto(Double result) {
        this.result = result;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
