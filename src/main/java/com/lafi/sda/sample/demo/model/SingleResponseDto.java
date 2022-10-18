package com.lafi.sda.sample.demo.model;

import java.util.Objects;

public class SingleResponseDto {

    private Integer id;

    private String text;

    // getters & setters

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

    // special methods

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SingleResponseDto that = (SingleResponseDto) object;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "SingleResponseDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
