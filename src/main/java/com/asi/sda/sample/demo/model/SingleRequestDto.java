package com.asi.sda.sample.demo.model;

import java.util.Objects;

public class SingleRequestDto {

    private String text;

    // getters & setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // special methods

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SingleRequestDto that = (SingleRequestDto) object;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "SingleRequestDto{" +
                "text='" + text + '\'' +
                '}';
    }
}
