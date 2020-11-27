package com.asi.sda.sample.demo.model;

import java.util.Objects;

public class Single {
    private Integer id;
    private String text;

    public Single() {
    }

    public Single(String text) {
        this.text = text;
    }

    public Single(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Single single = (Single) o;
        return Objects.equals(text, single.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Single{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
