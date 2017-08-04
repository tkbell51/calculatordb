package com.tbell.calculatordb.models;

import javax.persistence.*;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String first_number;
    private String second_number;
    private String operator;
    private Double solution;

    @ManyToOne
    @JoinColumn(name = "operationuser_id")
    private OperationUser operationUser;


    public Operation() {}

    public Operation(String first_number, String second_number, String operator, Double solution, OperationUser operationUser) {
        this.first_number = first_number;
        this.second_number = second_number;
        this.operator = operator;
        this.solution = solution;
        this.operationUser = operationUser;
    }


    public OperationUser getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(OperationUser operationUser) {
        this.operationUser = operationUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getSolution() {
        return solution;
    }

    public void setSolution(Double solution) {
        this.solution = solution;
    }

    public void setFirst_number(String first_number) {
        this.first_number = first_number;
    }

    public void setSecond_number(String second_number) {
        this.second_number = second_number;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFirst_number() {
        return first_number;
    }

    public String getSecond_number() {
        return second_number;
    }

    public String getOperator() {
        return operator;
    }

    public static Double doMath (String number1, String number2, String operator){
        double num1 =  Double.parseDouble(number1);
        double num2=  Double.parseDouble(number2);

        double result = 0;
        switch(operator){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "X":
                result = num1 * num2;
                break;
            case "/":
                result = num1/num2;
                break;
            case "%":
                result = num1 % num2;
                break;
            case "^":
                result = Math.pow(num1,num2);
                break;
            case "left":
                result = (int)num1 << (int)num2;
                break;
            case "right":
                result = (int)num1 >> (int)num2;
                break;
            case "XOR":
                result = (int)num1 ^ (int)num2;
                break;
        }
        return result;
    }
}
