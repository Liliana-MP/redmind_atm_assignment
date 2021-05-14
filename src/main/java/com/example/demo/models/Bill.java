package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private Long id;
    private int value;

    public Bill() {
    }

    public Bill(int value) {
        this.value = value;
    }

    public Bill(int value, Long id) {
        this.value = value;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", number=" + value +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int number) {
        this.value = number;
    }
}
