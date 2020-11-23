package com.asi.sda.sample.exception;

public class SampleNotDeletedException extends RuntimeException {
    public SampleNotDeletedException(String errorMessage) {
        super(errorMessage);
    }
}
