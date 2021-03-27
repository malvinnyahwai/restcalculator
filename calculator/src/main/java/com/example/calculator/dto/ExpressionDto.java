package com.example.calculator.dto;

public class ExpressionDto {
    private Double a;
    private Double b;
    private Character operator;

    public ExpressionDto() {

    }

    public ExpressionDto(Double a, Double b, Character operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Character getOperator() {
        return operator;
    }

    public void setOperator(Character operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "ExpressionDto{" +
                "a=" + a +
                ", b=" + b +
                ", operator=" + operator +
                '}';
    }
}
