package com.asi.sda.sample.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity(name = "Sample")
@Table(name = "sample")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Column(name = "text")
    private String text;

    public Sample() {
        // empty constructor by default
    }

    public Sample(String text) {
        this.text = text;
    }

    public Sample(int id, String text) {
        this.id = id;
        this.text = text;
    }

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
        Sample sample = (Sample) object;
        return Objects.equals(text, sample.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
