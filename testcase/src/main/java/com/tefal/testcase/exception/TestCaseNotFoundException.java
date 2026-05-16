package com.tefal.testcase.exception;

public class TestCaseNotFoundException extends RuntimeException{
    public TestCaseNotFoundException(String message){
        super(message);
    }
}
