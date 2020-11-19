package com.asi.sda.sample;

import java.util.Objects;

public class SampleResponseDto {

    private Integer id;

    private String text;

    // =================> getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // =================> special methods

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SampleResponseDto that = (SampleResponseDto) object;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "SampleResponseDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
