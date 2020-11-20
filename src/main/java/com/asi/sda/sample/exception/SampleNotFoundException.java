package com.asi.sda.sample.exception;

public class SampleNotFoundException extends RuntimeException {
    public SampleNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
