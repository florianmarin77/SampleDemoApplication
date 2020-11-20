package com.asi.sda.sample.exception;

public class SampleNotSavedException extends RuntimeException {
    public SampleNotSavedException(String errorMessage) {
        super(errorMessage);
    }
}
