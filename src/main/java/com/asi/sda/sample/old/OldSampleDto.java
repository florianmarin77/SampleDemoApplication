package com.asi.sda.sample.old;

import java.util.Objects;

public class OldSampleDto {

    private int id;
    private String text;

    // getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        OldSampleDto oldSampleDto = (OldSampleDto) object;
        return Objects.equals(text, oldSampleDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "SampleDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
